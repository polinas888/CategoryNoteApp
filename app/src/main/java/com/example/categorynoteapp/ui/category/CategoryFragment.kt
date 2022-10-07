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
import com.example.categorynoteapp.databinding.FragmentCategoryBinding
import com.example.categorynoteapp.model.Category

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
    }
}