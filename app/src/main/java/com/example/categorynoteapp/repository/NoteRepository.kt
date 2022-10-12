package com.example.categorynoteapp.repository

import com.example.categorynoteapp.model.Note

/* Barbara Liskov Principle. NoteRepository interface(base interface) made to create classes with
 specific implementation of its methods. All interface methods must be override by child classes to keep program works
  without changes, so NoteRepository could be changed to specific class implementation */
/* open closed principle created note repository interface to make different implementations of
 different notes */
interface NoteRepository {
    suspend fun addNote(note: Note)
    suspend fun getNotes(categoryId: Int): List<Note>
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)
}