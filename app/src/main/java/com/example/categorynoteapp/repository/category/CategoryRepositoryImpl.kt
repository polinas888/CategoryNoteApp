package com.example.categorynoteapp.repository.category

import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.repository.CategoryRepository
import javax.inject.Inject

/* open closed principle one of implementation of note repository interface
open to extend(we can add more functionality) but class doesn't change  */
//Single Responsibility Principle class include only functionality connected to database to operate with category
class CategoryRepositoryImpl @Inject constructor(private val categoryDataSource: CategoryDataSource
): CategoryRepository {

    override suspend fun getCategories() : List<Category> = categoryDataSource.getCategories()

    override suspend fun addCategory(category: Category) = categoryDataSource.addCategory(category)
}