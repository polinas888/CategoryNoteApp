package com.example.categorynoteapp.ui.category

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categorynoteapp.MainActivity
import com.example.categorynoteapp.R
import com.example.categorynoteapp.changeFragment
import com.example.categorynoteapp.databinding.FragmentCategoryBinding
import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.ui.notification.NotificationFragment
import com.google.gson.GsonBuilder
import com.example.categorynoteapp.appComponent
import javax.inject.Inject

const val CREATE_CATEGORY_FRAGMENT = 1
const val ARG_CATEGORY_ID: String = "CATEGORY_ID"

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    @Inject
    lateinit var categoryViewModelFactory: CategoryViewModelFactory
    private val categoryViewModel by viewModels<CategoryViewModel> {
        categoryViewModelFactory
    }
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        requireContext().appComponent.inject(this)
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title =
            getString(R.string.toolbar_title_category)
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title =
            getString(R.string.toolbar_title_category)

        categoryViewModel.loadData()
        categoryViewModel.categoryListLiveData.observe(viewLifecycleOwner, Observer { categories ->
            if (categories.isEmpty()) {
                binding.emptyListText.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            } else {
                updateUI(categories)
                binding.progressBar.visibility = View.GONE
            }
        })

        binding.addButton.setOnClickListener {
            val dialog = CreateCategoryDialogFragment()
            dialog.setTargetFragment(this, CREATE_CATEGORY_FRAGMENT)
            dialog.show(parentFragmentManager, "CustomDialog")
        }
    }

    private fun updateUI(categories: List<Category>) {
        categoryAdapter = CategoryAdapter(categories) { category -> adapterOnClick(category) }
        binding.categoryRecyclerView.adapter = categoryAdapter
    }

    private fun adapterOnClick(category: Category) {
        val fragment = NotificationFragment()
        val args = Bundle()
        args.putInt(ARG_CATEGORY_ID, category.id)
        fragment.changeFragment(args, parentFragmentManager)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CREATE_CATEGORY_FRAGMENT -> {
                binding.progressBar.visibility = View.VISIBLE
                if (resultCode == Activity.RESULT_OK) {
                    val bundle = data?.extras
                    val category = bundle?.getString("category")
                    val newCategory =
                        category?.let { categoryName -> Category(name = categoryName) }

                    if (newCategory != null) {
                        categoryViewModel.saveCategory(newCategory)
                    }
                    binding.emptyListText.visibility = View.GONE
                    categoryViewModel.loadData()
                    categoryViewModel.categoryListLiveData.value?.let { categories ->
                        updateUI(
                            categories
                        )
                    }
                } else {
                    Log.i("Error", "Bad result")
                }
            }
        }
    }
}
