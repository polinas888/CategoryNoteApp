package com.example.categorynoteapp.ui.category

import androidx.lifecycle.ViewModel
import com.example.categorynoteapp.model.Category

class CategoryViewModel : ViewModel() {
    val listLocations = listOf(Category("1", "Яблоко"), Category("2", "Груша"),
        Category("3", "Огурец")
    )
}