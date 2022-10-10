package com.example.categorynoteapp.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categorynoteapp.MainActivity
import com.example.categorynoteapp.R
import com.example.categorynoteapp.appComponent
import com.example.categorynoteapp.changeFragment
import com.example.categorynoteapp.databinding.FragmentNotificationBinding
import com.example.categorynoteapp.model.Notification
import com.example.categorynoteapp.ui.category.ARG_CATEGORY_ID
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import javax.inject.Inject

const val ARG_NOTIFICATION = "arg_notification"
const val NOTIFICATION_REQUEST_KEY = "requestKey"

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    private lateinit var notificationAdapter: NotificationAdapter
    private var categoryId = 0
    private var notificationIdForUpdate = 0

    @Inject
    lateinit var notificationViewModelFactory: NotificationViewModelFactory
    private val notificationViewModel by viewModels<NotificationViewModel> {
        notificationViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireContext().appComponent.inject(this)

        setFragmentResultListener(NOTIFICATION_REQUEST_KEY) { requestKey, bundle ->
            val notification = bundle.getString(ARG_NOTIFICATION)
            val isNewNotification = bundle.getBoolean(IS_NEW_NOTIFICATION)

            if (notification != null) {
                if (isNewNotification) {
                    lifecycleScope.launch {
                        //single responsibility principle wrote method for saving new notification
                        saveNewNotification(notification)
                        //single responsibility principle wrote method for updating UI after change
                        updateUiAfterChange()
                    }
                } else {
                    lifecycleScope.launch {
                        //single responsibility principle wrote method for updating new notification
                        openFragmentUpdateNotification(notification)
                        updateUiAfterChange()
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        binding.notificationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryId = arguments?.getInt(ARG_CATEGORY_ID)!!
        notificationViewModel.categoryId.value = categoryId

        (activity as MainActivity).supportActionBar?.title = getString(R.string.toolbar_title_notification) + " $categoryId"

        notificationViewModel.notificationListLiveData.observe(viewLifecycleOwner) { notifications ->
          //single responsibility principle created method to setup data
            setupDataOrEmptyListOnUi(notifications)
            binding.progressBar.visibility = View.GONE
        }
        notificationViewModel.loadData()

        binding.addButton.setOnClickListener {
            //single responsibility principle to open NotificationCreateOrChangeFragment
            openNotificationCreateOrChangeFragment()
        }
    }

    //single responsibility principle method to setup data or empty list
    private fun setupDataOrEmptyListOnUi(notifications: List<Notification>) {
        updateUI(notifications)
        setupVisibilityOfEmptyList(notifications)
    }

    //single responsibility principle method to setup visibility of EmptyList
    private fun setupVisibilityOfEmptyList(notifications: List<Notification>) {
        if (notifications.isEmpty()) {
            binding.emptyListText.visibility = View.VISIBLE
        } else {
            binding.emptyListText.visibility = View.INVISIBLE
        }
    }

    //single responsibility principle method updated Ui after change
    private fun updateUiAfterChange() {
        lifecycleScope.launch {
            notificationViewModel.loadData()
            notificationViewModel.notificationListLiveData.value?.let {
                setupDataOrEmptyListOnUi(it)
                binding.progressBar.visibility = View.GONE}
        }
    }

    //single responsibility principle method to save new notification
    private suspend fun saveNewNotification(notificationText: String) {
        val newNotification = Notification(text = notificationText, category_id = categoryId)
        binding.progressBar.visibility = View.VISIBLE
            notificationViewModel.saveNotification(newNotification)
    }

    //single responsibility principle method to update notification
    private suspend fun openFragmentUpdateNotification(notificationText: String) {
        val newNotification = Notification(
            id = notificationIdForUpdate,
            text = notificationText,
            category_id = categoryId
        )
            notificationViewModel.updateNotification(newNotification)
    }

    private fun updateUI(notifications: List<Notification>) {
        notificationAdapter = NotificationAdapter((notifications),
            { notification -> deleteNotification(notification) },
            { notification -> openFragmentUpdateNotification(notification) })
        binding.notificationRecyclerView.adapter = notificationAdapter
    }

    private fun openNotificationCreateOrChangeFragment() {
        val fragment = NotificationCreateOrChangeFragment()
        val args = Bundle()
        fragment.changeFragment(args, parentFragmentManager)
    }

    private fun openFragmentUpdateNotification(notification: Notification) {
        lifecycleScope.launch {
            notificationIdForUpdate = notification.id
            val fragment = NotificationCreateOrChangeFragment()
            val args = Bundle()
            val builder = GsonBuilder()
            val gson = builder.create()
            val result: String = gson.toJson(notification)
            args.putString(ARG_NOTIFICATION, result)
            fragment.changeFragment(args, parentFragmentManager)
        }
    }

    private fun deleteNotification(notification: Notification) {
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            notificationViewModel.deleteNotification(notification)
            notificationViewModel.notificationListLiveData.observe(viewLifecycleOwner) { notifications ->
                setupDataOrEmptyListOnUi(notifications)
                binding.progressBar.visibility = View.GONE
            }
            notificationViewModel.loadData()
        }
    }
}
