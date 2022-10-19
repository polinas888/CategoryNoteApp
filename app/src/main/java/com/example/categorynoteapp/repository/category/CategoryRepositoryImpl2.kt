package com.example.categorynoteapp.repository.category

import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl2 @Inject constructor(private val categoryDataSource: CategoryDataSource
): CategoryRepository {

    override suspend fun getCategories() : List<Category> = categoryDataSource.getCategories()

    override suspend fun addCategory(category: Category) =
        categoryDataSource.addCategory(category)

}