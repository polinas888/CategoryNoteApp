package com.example.categorynoteapp.di

import com.example.categorynoteapp.repository.DatabaseFactory
import com.example.categorynoteapp.repository.category.CategoryDao
import com.example.categorynoteapp.repository.category.CategoryRepository
import com.example.categorynoteapp.repository.note.NoteDao
import com.example.categorynoteapp.repository.note.noteRepository
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {
    @Provides
    fun provideCategoryDao(): CategoryDao {
        return DatabaseFactory.get().database.categoryDao()
    }

    @Provides
    fun providenoteDao(): NoteDao {
        return DatabaseFactory.get().database.noteDao()
    }

    @Provides
    fun provideCategoryRepository(categoryDao: CategoryDao): CategoryRepository {
        return CategoryRepository(
            categoryDao = categoryDao
        )
    }

    @Provides
    fun providenoteRepository(noteDao: NoteDao): noteRepository {
        return noteRepository(
            noteDao = noteDao
        )
    }
}