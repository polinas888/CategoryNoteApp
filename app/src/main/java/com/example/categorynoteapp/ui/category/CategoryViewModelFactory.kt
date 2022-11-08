package com.example.categorynoteapp.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.categorynoteapp.repository.category.CategoryRepositoryWithPrintDecoratorImp
import javax.inject.Inject

class CategoryViewModelFactory @Inject constructor(
    private val categoryRepositoryWithPrintDecorator: CategoryRepositoryWithPrintDecoratorImp
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java))
            return CategoryViewModel(categoryRepositoryWithPrintDecorator) as T
        else
            throw IllegalArgumentException("Unable to construct viewModel")
    }
}