package com.example.categorynoteapp.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categorynoteapp.MainActivity
import com.example.categorynoteapp.R
import com.example.categorynoteapp.changeFragment
import com.example.categorynoteapp.databinding.FragmentCategoryBinding
import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.ui.notification.NotificationFragment
import com.google.gson.GsonBuilder

const val ARG_CATEGORY: String = "CATEGORY"
class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private val locationViewModel by viewModels<CategoryViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.toolbar_title_category)

        binding.apply {
            categoryAdapter = CategoryAdapter { category -> adapterOnClick(category) }
            categoryRecyclerView.adapter = categoryAdapter
            categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        postCategoryListOrEmpty()
    }

    private fun postCategoryListOrEmpty() {
        val listLocations = locationViewModel.listLocations
        if (listLocations.isEmpty()) {
            binding.emptyListText.visibility = View.VISIBLE
        } else {
            categoryAdapter.setData(locationViewModel.listLocations)
        }
    }

    private fun adapterOnClick(category: Category) {
        val fragment = NotificationFragment()
        val args = Bundle()
        val builder = GsonBuilder()
        val gson = builder.create()
        val result: String = gson.toJson(category)

        args.putString(ARG_CATEGORY, result)
        fragment.changeFragment(args, parentFragmentManager)
    }
}