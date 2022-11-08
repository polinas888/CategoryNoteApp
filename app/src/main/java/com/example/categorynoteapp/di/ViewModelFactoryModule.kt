package com.example.categorynoteapp.di

import com.example.categorynoteapp.repository.category.CategoryRepositoryWithPrintDecoratorImp
import com.example.categorynoteapp.repository.notification.NoteRepositoryImpl
import com.example.categorynoteapp.ui.category.CategoryViewModelFactory
import com.example.categorynoteapp.ui.notification.NoteViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule {

    @Provides
    fun provideCategoryViewModelFactory(categoryRepositoryWithPrintDecoratorImpl: CategoryRepositoryWithPrintDecoratorImp): CategoryViewModelFactory {
        return CategoryViewModelFactory(categoryRepositoryWithPrintDecorator = categoryRepositoryWithPrintDecoratorImpl)
    }

    @Provides
    fun provideNoteViewModelFactory(noteRepository: NoteRepositoryImpl): NoteViewModelFactory {
        return NoteViewModelFactory(
            noteRepository = noteRepository)
    }
}