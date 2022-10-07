package com.example.categorynoteapp.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.model.Notification

@Database(entities = [Category::class, Notification::class], version = 1)
abstract class CategoryNotificationDatabase : RoomDatabase() {

}