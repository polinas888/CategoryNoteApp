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
import com.example.categorynoteapp.appComponent
import com.example.categorynoteapp.changeFragment
import com.example.categorynoteapp.databinding.FragmentCategoryBinding
import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.ui.note.NoteFragment
import javax.inject.Inject

const val CREATE_CATEGORY_FRAGMENT = "create_category_fragment"
const val ARG_CATEGORY_ID: String = "CATEGORY_ID"
const val NEW_CATEGORY = "newCategory"

//Single Responsibility Principle class include only functionality category needs to operate in UI layout
class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding

    @Inject
    lateinit var categoryViewModelFactory: CategoryViewModelFactory
    private val categoryViewModel by viewModels<CategoryViewModel> {
        categoryViewModelFactory
    }
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        requireContext().appComponent.inject(this)
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        setFragmentResultListener(CREATE_CATEGORY_FRAGMENT) { key, bundle ->
            handleNewCategory(bundle)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title =
            getString(R.string.toolbar_title_category)

        setUpData()
        categoryViewModel.loadData()

        binding.addButton.setOnClickListener {
            openCreateCategoryFragment()
        }
    }

    private fun openCreateCategoryFragment() {
        CreateCategoryFragment().changeFragment(Bundle(), parentFragmentManager)
    }

    private fun handleNewCategory(bundle: Bundle) {
        val categoryName = bundle.getString(NEW_CATEGORY)
        val category = categoryName?.let { Category.Builder().name(it).build() }
        binding.progressBar.visibility = View.VISIBLE
        if (category != null) {
            categoryViewModel.saveNewCategory(category)
        }
        categoryViewModel.loadData()
        setUpData()
    }

    private fun setUpData() {
        categoryViewModel.categoryListLiveData.observe(viewLifecycleOwner) { categories ->
            if (categories.isEmpty()) {
                binding.emptyListText.visibility = View.VISIBLE
            } else {
                updateUI(categories)
                binding.emptyListText.visibility = View.INVISIBLE
            }
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun updateUI(categories: List<Category>) {
        categoryAdapter = CategoryAdapter(categories) { category -> adapterOnClick(category) }
        binding.categoryRecyclerView.adapter = categoryAdapter
    }

    private fun adapterOnClick(category: Category) {
        val fragment = NoteFragment()
        val args = Bundle()
        args.putInt(ARG_CATEGORY_ID, category.id)
        fragment.changeFragment(args, parentFragmentManager)
    }
}
