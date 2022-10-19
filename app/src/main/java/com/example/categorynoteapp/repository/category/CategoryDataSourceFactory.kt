package com.example.categorynoteapp.repository.category

interface CategoryDataSourceFactory {
    fun createCategoryDataSource(dataSourceType: DataSourceType, categoryDao: CategoryDao) : CategoryDataSource
}

class CategoryDataSourceFactoryImpl: CategoryDataSourceFactory {
    override fun createCategoryDataSource(dataSourceType: DataSourceType, categoryDao: CategoryDao): CategoryDataSource{
        return when(dataSourceType) {
            DataSourceType.CATEGORY_DATA_SOURCE_1 -> CategoryDataSourceImpl(categoryDao)
            DataSourceType.CATEGORY_DATA_SOURCE_2 -> CategoryDataSourceImpl2(categoryDao)
        }
    }
}

enum class DataSourceType {
    CATEGORY_DATA_SOURCE_1,
    CATEGORY_DATA_SOURCE_2
}