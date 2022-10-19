package com.example.categorynoteapp.repository.category

import com.example.categorynoteapp.model.Category
import javax.inject.Inject

class CategoryDataSourceImpl2 @Inject constructor(categoryDao: CategoryDao): CategoryDataSource {
    private val categoryDao: CategoryDao by lazy {
        categoryDao
    }

    override suspend fun getCategories(): List<Category> {
        return categoryDao.getCategories()
    }

    override suspend fun addCategory(category: Category) {
        categoryDao.addCategory(category)
    }
}