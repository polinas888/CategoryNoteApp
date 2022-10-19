package com.example.categorynoteapp.repository.category

interface CategoryDataSourceFactory {
    fun createCategoryDataSource(categoryDao: CategoryDao) : CategoryDataSource
}

class CategoryDataSourceFactoryImpl: CategoryDataSourceFactory {
    override fun createCategoryDataSource(categoryDao: CategoryDao): CategoryDataSource{
        return CategoryDataSourceImpl(categoryDao)
        }
}

class CategoryDataSourceFactoryImpl2: CategoryDataSourceFactory {
    override fun createCategoryDataSource(categoryDao: CategoryDao): CategoryDataSource{
        return CategoryDataSourceImpl2(categoryDao)
    }
}