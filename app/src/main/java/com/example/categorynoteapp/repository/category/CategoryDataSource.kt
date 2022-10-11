package com.example.categorynoteapp.repository.category

import com.example.categorynoteapp.model.Category

/* open closed principle interface to operate with database allows to implement it to create
 different realization, for example use different databases */
interface CategoryDataSource {
    suspend fun getCategories(): List<Category>
    suspend fun addCategory(category: Category)
}