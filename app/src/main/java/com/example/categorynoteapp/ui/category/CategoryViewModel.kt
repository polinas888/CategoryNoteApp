package com.example.categorynoteapp.ui.category

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.repository.DataResult
import com.example.categorynoteapp.repository.category.CategoryNotificationRepository
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val categoryRepository = CategoryNotificationRepository.get()
    val categoryListLiveData = MutableLiveData<List<Category>>()

    fun saveCategory(category: Category) {
        viewModelScope.launch {
            try {
                categoryRepository.addCategory(category)
            } catch (exception: SQLiteConstraintException) {
                Log.i("SaveError", "Couldn't save category")
            }
        }
    }

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
}