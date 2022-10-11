package com.example.categorynoteapp.ui.category

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.categorynoteapp.databinding.FragmentCreateCategoryDialogBinding

//Single Responsibility Principle class include only functionality for creating new category
class CreateCategoryDialogFragment: DialogFragment() {
    private lateinit var binding: FragmentCreateCategoryDialogBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateCategoryDialogBinding.inflate(layoutInflater)
        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        binding.okButton.setOnClickListener {
            // Single Responsibility Principle, created separate method runOnActivityResultAndPassCategoryArg
            runOnActivityResultAndPassCategoryArg()
            dismiss()
        }
        return binding.root
    }

    private fun runOnActivityResultAndPassCategoryArg() {
        val category = binding.createCategoryEditText.text.toString()
        if(category.isNotEmpty()) {
            val intent: Intent = Intent().putExtra("category", category)
            targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        }
    }
}