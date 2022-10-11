package com.example.categorynoteapp.repository

import com.example.categorynoteapp.model.Note

/* open closed principle created note repository interface to make different implementations of
 different notes */
interface NoteRepository {
    suspend fun addNote(note: Note)
    suspend fun getNotes(categoryId: Int): List<Note>
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)
}