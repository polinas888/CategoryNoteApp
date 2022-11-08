package com.example.categorynoteapp.repository.category

import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.repository.CategoryRepository

interface CategoryRepositoryWithPrintDecorator : CategoryRepository {
    override suspend fun getCategories(): List<Category>
    override suspend fun addCategory(category: Category)
}