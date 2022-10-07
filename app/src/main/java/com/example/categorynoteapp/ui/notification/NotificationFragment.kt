package com.example.categorynoteapp.ui.notification

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
            val newNotification = notification?.let { notificationText ->
                categoryId.let { category_id ->
                    Notification(text = notificationText, category_id = category_id)
                }
            }
            Log.i("notificationLog", notification.toString())
            lifecycleScope.launch {
                if (newNotification != null) {
                    notificationViewModel.saveNotification(newNotification)
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
        (activity as MainActivity).supportActionBar?.title =
            getString(R.string.toolbar_title_notification) + " $categoryId"

        binding.addButton.setOnClickListener {
            val fragment = NotificationCreateOrChangeFragment()
            val args = Bundle()
            fragment.changeFragment(args, parentFragmentManager)
        }
    }
}
