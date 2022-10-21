package com.example.categorynoteapp.repository

import com.example.categorynoteapp.model.Category
import com.example.categorynoteapp.model.Note
import javax.inject.Inject

// Facade class to combine operation with categories and notes
class SomeDifficultOperationFacade @Inject constructor(private val categoryRepository: CategoryRepository, val noteRepository: NoteRepository) {

    //some method to make a lot of operations with category and notes
    suspend fun makeSomeDifficultOperation() {
        categoryRepository.addCategory(Category(44, "New Category"))
        noteRepository.addNote(Note(35, "New note", 44))
        noteRepository.updateNote(Note(35, "New note", 44))
        val categories = categoryRepository.getCategories()
        for (category in categories) {
            noteRepository.getNotes(category.id)
        }
    }
}