package com.example.categorynoteapp.repository.category

import com.example.categorynoteapp.model.Category
import javax.inject.Inject

/* open closed principle one of implementation of interface to operate with database
open to extend(we can add more functionality) but class doesn't change  */
class CategoryDataSourceImpl @Inject constructor(categoryDao: CategoryDao): CategoryDataSource {
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