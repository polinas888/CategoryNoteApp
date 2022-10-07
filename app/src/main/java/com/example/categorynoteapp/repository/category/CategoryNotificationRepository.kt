package com.example.categorynoteapp.repository.category

import android.content.Context
import androidx.room.Room
import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.repository.CategoryNotificationDatabase

private const val DATABASE_NAME = "crime-database"
class CategoryNotificationRepository private constructor(context: Context){

    private val database: CategoryNotificationDatabase = Room.databaseBuilder(
        context.applicationContext,
        CategoryNotificationDatabase:: class.java,
        DATABASE_NAME
    ).build()

    private val categoryDao = database.categoryDao()

    suspend fun getCategories() : List<Category> = categoryDao.getCategories()

    suspend fun addCategory(category: Category) = categoryDao.addCategory(category)

    companion object {
        private var INSTANCE: CategoryNotificationRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CategoryNotificationRepository(context)
            }
        }

        fun get(): CategoryNotificationRepository {
            return INSTANCE ?:
            throw IllegalStateException("CategoryNotificationRepository must be initialize")
        }
    }
}