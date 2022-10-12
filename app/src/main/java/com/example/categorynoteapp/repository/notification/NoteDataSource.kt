package com.example.categorynoteapp.repository.notification

import com.example.categorynoteapp.model.Note

/* Barbara Liskov Principle. NoteDataSource interface(base interface) made to create classes with
 specific implementation of its methods. All interface methods must be override by child classes to keep program works
  without changes, so NoteDataSource could be changed to specific class implementation */
/* open closed principle interface to operate with database allows to implement it to create
 different realization, for example use different databases */
interface NoteDataSource {
    suspend fun addNote(note: Note)
    suspend fun getNotes(categoryId: Int): List<Note>
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)
}