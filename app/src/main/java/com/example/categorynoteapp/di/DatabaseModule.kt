package com.example.categorynoteapp.di

import com.example.categorynoteapp.repository.CategoryRepository
import com.example.categorynoteapp.repository.DatabaseFactory
import com.example.categorynoteapp.repository.NoteRepository
import com.example.categorynoteapp.repository.category.CategoryDao
import com.example.categorynoteapp.repository.category.CategoryDataSource
import com.example.categorynoteapp.repository.category.CategoryDataSourceImpl
import com.example.categorynoteapp.repository.category.CategoryRepositoryImpl
import com.example.categorynoteapp.repository.note.NoteDao
import com.example.categorynoteapp.repository.note.NoteDataSourceImpl
import com.example.categorynoteapp.repository.notification.NoteDataSource
import com.example.categorynoteapp.repository.notification.NoteRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {
    @Provides
    fun provideCategoryDao(): CategoryDao {
        return DatabaseFactory.get().database.categoryDao()
    }

    @Provides
    fun provideNoteDao(): NoteDao {
        return DatabaseFactory.get().database.noteDao()
    }

    @Provides
    fun provideCategoryRepository(categoryDataSource: CategoryDataSource): CategoryRepository {
        return CategoryRepositoryImpl(
            categoryDataSource = categoryDataSource
        )
    }

    @Provides
    fun provideCategoryDataSource(categoryDao: CategoryDao) : CategoryDataSource {
        return CategoryDataSourceImpl(
            categoryDao = categoryDao
        )
    }

    @Provides
    fun provideNoteDataSource(noteDao: NoteDao): NoteDataSource {
        return NoteDataSourceImpl(
            noteDao = noteDao
        )
    }

    @Provides
    fun provideNoteRepository(noteDataSource: NoteDataSource) : NoteRepository {
        return NoteRepositoryImpl(noteDataSource = noteDataSource)
    }
}