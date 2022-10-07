package com.example.categorynoteapp.di

import com.example.categorynoteapp.repository.category.CategoryRepository
import com.example.categorynoteapp.repository.notification.NotificationRepository
import com.example.categorynoteapp.ui.category.CategoryViewModelFactory
import com.example.categorynoteapp.ui.notification.NotificationViewModelFactory
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
    fun provideNotificationViewModelFactory(notificationRepository: NotificationRepository): NotificationViewModelFactory {
        return NotificationViewModelFactory(
            notificationRepository = notificationRepository)
    }
}