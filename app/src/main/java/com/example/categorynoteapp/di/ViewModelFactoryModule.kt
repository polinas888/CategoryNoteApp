package com.example.categorynoteapp.di

import com.example.categorynoteapp.repository.category.CategoryRepository
import com.example.categorynoteapp.repository.note.NoteRepository
import com.example.categorynoteapp.ui.category.CategoryViewModelFactory
import com.example.categorynoteapp.ui.note.NoteViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule {

    @Provides
    fun provideCategoryViewModelFactory(categoryRepository: CategoryRepository): CategoryViewModelFactory {
        return CategoryViewModelFactory(
            categoryRepository = categoryRepository)
    }

    @Provides
    fun provideNoteViewModelFactory(noteRepository: NoteRepository): NoteViewModelFactory {
        return NoteViewModelFactory(
            noteRepository = noteRepository)
    }
}