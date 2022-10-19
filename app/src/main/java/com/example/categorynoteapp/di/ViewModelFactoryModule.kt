package com.example.categorynoteapp.di

import com.example.categorynoteapp.repository.category.CategoryDao
import com.example.categorynoteapp.repository.category.CategoryDataSourceFactory
import com.example.categorynoteapp.repository.category.CategoryRepositoryFactory
import com.example.categorynoteapp.repository.notification.NoteRepositoryImpl
import com.example.categorynoteapp.ui.category.CategoryViewModelFactory
import com.example.categorynoteapp.ui.notification.NoteViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule {

    @Provides
    fun provideCategoryViewModelFactory(
        categoryRepositoryFactory: CategoryRepositoryFactory,
        categoryDataSourceFactory: CategoryDataSourceFactory,
        categoryDao: CategoryDao
    ): CategoryViewModelFactory {
        return CategoryViewModelFactory(
            categoryRepositoryFactory = categoryRepositoryFactory,
            categoryDataSourceFactory = categoryDataSourceFactory,
            categoryDao = categoryDao
        )
    }

    @Provides
    fun provideNoteViewModelFactory(noteRepository: NoteRepositoryImpl): NoteViewModelFactory {
        return NoteViewModelFactory(
            noteRepository = noteRepository
        )
    }
}