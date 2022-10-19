package com.example.categorynoteapp.repository.category

import com.example.categorynoteapp.repository.CategoryRepository

interface CategoryRepositoryFactory {
    fun createCategoryRepository(repositoryType: RepositoryType, categoryDataSource: CategoryDataSource) : CategoryRepository
}

class CategoryRepositoryFactoryImpl: CategoryRepositoryFactory {
    override fun createCategoryRepository(repositoryType: RepositoryType, categoryDataSource: CategoryDataSource): CategoryRepository {
        return when(repositoryType) {
            RepositoryType.CATEGORY_REPOSITORY_1 -> CategoryRepositoryImpl(categoryDataSource)
            RepositoryType.CATEGORY_REPOSITORY_2 -> CategoryRepositoryImpl2(categoryDataSource)
        }
    }
}

enum class RepositoryType {
    CATEGORY_REPOSITORY_1,
    CATEGORY_REPOSITORY_2
}