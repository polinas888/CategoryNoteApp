package com.example.categorynoteapp.repository.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.categorynoteapp.model.Category
import androidx.room.Query

//interface segregation principle. functionality of interface is only to get CRUD operations for category in room database
@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    suspend fun getCategories(): List<Category>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategory(category: Category)
}