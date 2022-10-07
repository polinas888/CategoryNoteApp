package com.example.categorynoteapp.ui.category

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.repository.CategoryNotificationRepository
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
}