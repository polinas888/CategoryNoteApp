package com.example.categorynoteapp.repository

import com.example.categorynoteapp.model.Category

//interface segregation principle. functionality of interface is only to get CRUD operations for category from repository
/* Barbara Liskov Principle. CategoryRepository interface(base interface) made to create classes with
 specific implementation of its methods. All interface methods must be override by child classes to keep program works
  without changes, so CategoryRepository could be changed to specific class implementation */
/* open closed principle created note repository interface to make different implementations of
 different categories */
interface CategoryRepository {
    suspend fun getCategories(): List<Category>
    suspend fun addCategory(category: Category)
}