package com.example.categorynoteapp.repository.category

import com.example.categorynoteapp.MainActivity
import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.repository.CategoryNoteDatabase
import javax.inject.Inject

/* Dependency inversion principle. CategoryDataSourceImpl uses CategoryDataSource interface to create
 (override) methods and relay on CategoryDao interface not some of it's implementation to implement methods */
/* Barbara Liskov Principle. CategoryDataSourceImpl override all methods of CategoryDataSource interface
 and program will work correctly with substitution of CategoryDataSource to CategoryDataSourceImpl */
/* open closed principle one of implementation of interface to operate with database
open to extend(we can add more functionality) but class doesn't change  */
class CategoryDataSourceImpl: CategoryDataSource {

    private val database: CategoryNoteDatabase = MainActivity.getDatabase()!!

    override suspend fun getCategories(): List<Category> {
        return database.categoryDao().getCategories()
    }

    override suspend fun addCategory(category: Category) {
        database.categoryDao().addCategory(category)
    }
}