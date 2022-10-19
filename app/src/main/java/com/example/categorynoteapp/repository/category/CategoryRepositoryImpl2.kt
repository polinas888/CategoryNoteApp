package com.example.categorynoteapp.repository.category

import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl2 @Inject constructor(
    categoryDataSourceFactoryImpl2: CategoryDataSourceFactoryImpl2,
    categoryDao: CategoryDao
) : CategoryRepository {
    private val categoryDataSource = categoryDataSourceFactoryImpl2.createCategoryDataSource(categoryDao)

    override suspend fun getCategories(): List<Category> = categoryDataSource.getCategories()

    override suspend fun addCategory(category: Category) =
        categoryDataSource.addCategory(category)

}