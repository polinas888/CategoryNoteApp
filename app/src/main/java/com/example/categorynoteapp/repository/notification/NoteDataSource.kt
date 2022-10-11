package com.example.categorynoteapp.repository.notification

import com.example.categorynoteapp.model.Note

/* open closed principle interface to operate with database allows to implement it to create
 different realization, for example use different databases */
interface NoteDataSource {
    suspend fun addNote(note: Note)
    suspend fun getNotes(categoryId: Int): List<Note>
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)
}