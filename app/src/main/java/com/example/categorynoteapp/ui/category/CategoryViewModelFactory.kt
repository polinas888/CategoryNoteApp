package com.example.categorynoteapp.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.categorynoteapp.repository.category.*
import javax.inject.Inject

class CategoryViewModelFactory @Inject constructor(
    val categoryRepositoryFactory: CategoryRepositoryFactory,
    val categoryDataSourceFactory: CategoryDataSourceFactory,
    val categoryDao: CategoryDao
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java))
            return CategoryViewModel(categoryRepositoryFactory, categoryDataSourceFactory, categoryDao) as T
        else
            throw IllegalArgumentException("Unable to construct viewModel")
    }
}