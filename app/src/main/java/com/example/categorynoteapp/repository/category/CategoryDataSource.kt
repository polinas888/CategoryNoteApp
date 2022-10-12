package com.example.categorynoteapp.repository.category

import com.example.categorynoteapp.model.Category

/* Barbara Liskov Principle. CategoryDataSource interface(base interface) made to create classes with
 specific implementation of its methods. All interface methods must be override by child classes to keep program works
  without changes, so CategoryDataSource could be changed to specific class implementation */
/* open closed principle interface to operate with database allows to implement it to create
 different realization, for example use different databases */
interface CategoryDataSource {
    suspend fun getCategories(): List<Category>
    suspend fun addCategory(category: Category)
}