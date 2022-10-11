package com.example.categorynoteapp.repository.category

import com.example.categorynoteapp.model.Category
import javax.inject.Inject

//Single Responsibility Principle class include only functionality connected to database to operate with category
class CategoryRepository @Inject constructor(categoryDao: CategoryDao): CategoryDao {
    private val categoryDao: CategoryDao by lazy {
        categoryDao
    }

    override suspend fun getCategories() : List<Category> = categoryDao.getCategories()

    override suspend fun addCategory(category: Category) = categoryDao.addCategory(category)
}