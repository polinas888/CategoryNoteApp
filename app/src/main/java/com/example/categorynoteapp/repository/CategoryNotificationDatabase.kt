package com.example.categorynoteapp.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.model.Note
import com.example.categorynoteapp.repository.category.CategoryDao
import com.example.categorynoteapp.repository.note.NoteDao

@Database(entities = [Category::class, Note::class], version = 1)
abstract class CategoryNoteDatabase: RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun noteDao(): NoteDao
}