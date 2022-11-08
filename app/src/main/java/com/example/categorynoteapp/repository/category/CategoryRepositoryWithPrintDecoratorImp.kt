package com.example.categorynoteapp.repository.category

import com.example.categorynoteapp.model.Category
import javax.inject.Inject

class CategoryRepositoryWithPrintDecoratorImp@Inject constructor(private val categoryDataSource: CategoryDataSource
) : CategoryRepositoryWithPrintDecorator {
    override suspend fun getCategories(): List<Category> {
        val categories = categoryDataSource.getCategories()
        for (category in categories) {
            println(category.name)
        }
        return categories
    }

    override suspend fun addCategory(category: Category) {
        categoryDataSource.addCategory(category)
        println(category.name)
    }
}