package com.example.categorynoteapp.repository.category

import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.repository.CategoryRepository
import javax.inject.Inject

/* Dependency inversion principle. CategoryRepositoryImpl uses CategoryRepository interface to create
 (override) methods and relay on CategoryDataSource interface not some of it's implementation to implement methods */
/* Barbara Liskov Principle. CategoryRepositoryImpl override all methods of CategoryRepository interface
 and program will work correctly with substitution of CategoryRepository to CategoryRepositoryImpl */
/* open closed principle one of implementation of note repository interface
open to extend(we can add more functionality) but class doesn't change  */
//Single Responsibility Principle class include only functionality connected to database to operate with category
class CategoryRepositoryImpl: CategoryRepository {
    private val categoryDataSource: CategoryDataSource = CategoryDataSourceImpl()

    override suspend fun getCategories() : List<Category> = categoryDataSource.getCategories()

    override suspend fun addCategory(category: Category) = categoryDataSource.addCategory(category)
}
