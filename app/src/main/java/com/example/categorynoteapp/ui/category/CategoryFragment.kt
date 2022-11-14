package com.example.categorynoteapp.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categorynoteapp.MainActivity
import com.example.categorynoteapp.R
import com.example.categorynoteapp.changeFragment
import com.example.categorynoteapp.databinding.FragmentCategoryBinding
import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.ui.note.NoteFragment

const val CREATE_CATEGORY_EVENT = "create_category_event"
const val ARG_CATEGORY_ID: String = "CATEGORY_ID"

//Single Responsibility Principle class include only functionality category needs to operate in UI layout
class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding

    val categoryViewModel: CategoryViewModel by viewModels()

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)

        setFragmentResultListener(CREATE_CATEGORY_EVENT) { key, bundle ->
            handleNewCategory(bundle)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            categoryAdapter = CategoryAdapter{ category -> adapterOnClick(category) }
            categoryRecyclerView.adapter = categoryAdapter
            categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        (activity as MainActivity).supportActionBar?.title =
            getString(R.string.toolbar_title_category)

        categoryViewModel.loadData()
        setupData()

        binding.addButton.setOnClickListener {
            openCreateCategoryFragment()
        }
    }

    private fun openCreateCategoryFragment() {
        CreateCategoryFragment().changeFragment(Bundle(), parentFragmentManager)
    }

    private fun handleNewCategory(bundle: Bundle) {
        val result = bundle.getString("newCategory")
        val category = result?.let { categoryName -> Category(name = categoryName) }
        binding.progressBar.visibility = View.VISIBLE
        if (category != null) {
            categoryViewModel.saveNewCategory(category)
        }
    }

    private fun setupData() {
        categoryViewModel.categoryListLiveData.observe(viewLifecycleOwner) { categories ->
            if (categories.isEmpty()) {
                binding.emptyListText.visibility = View.VISIBLE
            } else {
                categoryAdapter.setData(categories)
                binding.emptyListText.visibility = View.INVISIBLE
            }
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun adapterOnClick(category: Category) {
        val fragment = NoteFragment()
        val args = Bundle()
        args.putInt(ARG_CATEGORY_ID, category.id)
        fragment.changeFragment(args, parentFragmentManager)
    }
}
