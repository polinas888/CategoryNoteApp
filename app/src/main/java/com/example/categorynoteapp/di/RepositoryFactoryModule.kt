package com.example.categorynoteapp.di

import com.example.categorynoteapp.repository.category.CategoryDataSourceFactory
import com.example.categorynoteapp.repository.category.CategoryDataSourceFactoryImpl
import com.example.categorynoteapp.repository.category.CategoryRepositoryFactory
import com.example.categorynoteapp.repository.category.CategoryRepositoryFactoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryFactoryModule {
    @Provides
    fun provideCategoryRepositoryFactory(): CategoryRepositoryFactory {
        return CategoryRepositoryFactoryImpl()
    }

    @Provides
    fun provideCategoryDataSourceFactory(): CategoryDataSourceFactory {
        return CategoryDataSourceFactoryImpl()
    }
}