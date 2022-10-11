package com.example.categorynoteapp.repository

import com.example.categorynoteapp.model.Category

/* open closed principle created note repository interface to make different implementations of
 different categories */
interface CategoryRepository {
    suspend fun getCategories(): List<Category>
    suspend fun addCategory(category: Category)
}