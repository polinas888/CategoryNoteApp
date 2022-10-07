package com.example.categorynoteapp.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categorynoteapp.MainActivity
import com.example.categorynoteapp.R
import com.example.categorynoteapp.databinding.FragmentNotificationBinding
import com.example.categorynoteapp.ui.category.ARG_CATEGORY_ID
import javax.inject.Inject

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private lateinit var notificationAdapter: NotificationAdapter
    @Inject
    lateinit var notificationViewModelFactory: NotificationViewModelFactory
    private val notificationViewModel by viewModels<NotificationViewModel> {
        notificationViewModelFactory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        binding.notificationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryId = arguments?.getInt(ARG_CATEGORY_ID)
        (activity as MainActivity).supportActionBar?.title =
            getString(R.string.toolbar_title_notification) + " $categoryId"
    }
}
