package com.example.categorynoteapp.ui.category

import android.app.Activity
import android.content.Intent
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

const val CREATE_CATEGORY_FRAGMENT = 1
const val ARG_CATEGORY: String = "CATEGORY"
class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private val categoryViewModel by viewModels<CategoryViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title =
            getString(R.string.toolbar_title_category)


        binding.addButton.setOnClickListener {
            val dialog = CreateCategoryDialogFragment()
            dialog.setTargetFragment(this, CREATE_CATEGORY_FRAGMENT)
            dialog.show(parentFragmentManager, "CustomDialog")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CREATE_CATEGORY_FRAGMENT -> {
                if (resultCode == Activity.RESULT_OK) {
                    val bundle = data?.extras
                    val category = bundle?.getString("category")
                    val newCategory =
                        category?.let { categoryName -> Category(name = categoryName) }

                    if (newCategory != null) {
                        categoryViewModel.saveCategory(newCategory)
                    }
                }
            }
        }
    }
}
