package com.example.categorynoteapp.ui.category

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.categorynoteapp.closeFragment
import com.example.categorynoteapp.databinding.FragmentCreateCategoryBinding

//Single Responsibility Principle class include only functionality for creating new category
class CreateCategoryFragment: Fragment() {
    private lateinit var binding: FragmentCreateCategoryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateCategoryBinding.inflate(layoutInflater)
        binding.cancelButton.setOnClickListener {
            this.closeFragment()
        }

        binding.okButton.setOnClickListener {
            // Single Responsibility Principle, created separate method runOnActivityResultAndPassCategoryArg
            val category =  binding.createCategoryEditText.text.toString()
            setFragmentResult(CREATE_CATEGORY_FRAGMENT, bundleOf("newCategory" to category))
            this.closeFragment()
        }
        return binding.root
    }
}