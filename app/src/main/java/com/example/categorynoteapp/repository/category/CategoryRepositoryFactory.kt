package com.example.categorynoteapp.repository.category

import com.example.categorynoteapp.repository.CategoryRepository

interface CategoryRepositoryFactory {
    fun createCategoryRepository(
        repositoryType: RepositoryType,
        categoryDataSourceFactory: CategoryDataSourceFactory,
        categoryDao: CategoryDao
    ): CategoryRepository
}

class CategoryRepositoryFactoryImpl : CategoryRepositoryFactory {
    override fun createCategoryRepository(
        repositoryType: RepositoryType,
        categoryDataSourceFactory: CategoryDataSourceFactory,
        categoryDao: CategoryDao
    ): CategoryRepository {
        return when (repositoryType) {
            RepositoryType.CATEGORY_REPOSITORY_1 -> CategoryRepositoryImpl(
                CategoryDataSourceFactoryImpl(),
                categoryDao
            )
            RepositoryType.CATEGORY_REPOSITORY_2 -> CategoryRepositoryImpl2(
                CategoryDataSourceFactoryImpl2(),
                categoryDao
            )
        }
    }
}

enum class RepositoryType {
    CATEGORY_REPOSITORY_1,
    CATEGORY_REPOSITORY_2
}