package com.example.categorynoteapp.ui.category

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.repository.CategoryRepository
import com.example.categorynoteapp.repository.DataResult
import com.example.categorynoteapp.repository.category.CategoryRepositoryImpl
import kotlinx.coroutines.launch

/* Dependency inversion principle. CategoryViewModel relay on CategoryRepository interface not some
of it's implementation to implement methods */
/* Barbara Liskov Principle. We can set as a parameter CategoryRepository and setup it's implementation
in CategoryViewModelFactory */
//Single Responsibility Principle class include only functionality how to get and save data for category
class CategoryViewModel: ViewModel() {
    private val categoryRepository: CategoryRepository = CategoryRepositoryImpl()
    val categoryListLiveData = MutableLiveData<List<Category>>()

    fun loadData() {
        viewModelScope.launch {
            when (val categories = getCategories()) {
                is DataResult.Ok -> {
                    categoryListLiveData.value = categories.response!!
                }
                is DataResult.Error -> categories.error
            }
        }
    }

    private suspend fun getCategories(): DataResult<List<Category>> {
        return try {
            val categories = categoryRepository.getCategories()
            DataResult.Ok(categories)
        } catch (e: Exception) {
            DataResult.Error(e.message.toString())
        }
    }

    // Single Responsibility Principle, separated method to save new category if exist
    fun saveNewCategory(category: Category) {
        viewModelScope.launch {
            try {
                categoryRepository.addCategory(category)
                loadData()
            } catch (exception: SQLiteConstraintException) {
                Log.i("SaveError", "Couldn't save category")
            }
        }
    }
}