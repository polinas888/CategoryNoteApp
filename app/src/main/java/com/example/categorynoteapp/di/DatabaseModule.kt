package com.example.categorynoteapp.di

import com.example.categorynoteapp.repository.DatabaseFactory
import com.example.categorynoteapp.repository.category.CategoryDao
import com.example.categorynoteapp.repository.category.CategoryRepository
import com.example.categorynoteapp.repository.notification.NotificationDao
import com.example.categorynoteapp.repository.notification.NotificationRepository
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {
    @Provides
    fun provideCategoryDao(): CategoryDao {
        return DatabaseFactory.get().database.categoryDao()
    }

    @Provides
    fun provideNotificationDao(): NotificationDao {
        return DatabaseFactory.get().database.notificationDao()
    }

    @Provides
    fun provideCategoryRepository(categoryDao: CategoryDao): CategoryRepository {
        return CategoryRepository(
            categoryDao = categoryDao
        )
    }

    @Provides
    fun provideNotificationRepository(notificationDao: NotificationDao): NotificationRepository {
        return NotificationRepository(
            notificationDao = notificationDao
        )
    }
}