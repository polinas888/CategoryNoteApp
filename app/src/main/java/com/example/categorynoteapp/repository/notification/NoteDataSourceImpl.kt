package com.example.categorynoteapp.repository.note

import com.example.categorynoteapp.MainActivity
import com.example.categorynoteapp.model.Note
import com.example.categorynoteapp.repository.CategoryNoteDatabase
import com.example.categorynoteapp.repository.notification.NoteDataSource
import javax.inject.Inject

/* Dependency inversion principle. NoteDataSourceImpl uses NoteDataSource interface to create
 (override) methods and relay on NoteDao interface not some of it's implementation to implement methods */
/* Barbara Liskov Principle. NoteDataSourceImpl override all methods of CategoryDataSource interface
 and program will work correctly with substitution of NoteDataSource to NoteDataSourceImpl */
/* open closed principle one of implementation of interface to operate with database
open to extend(we can add more functionality) but class doesn't change  */
//Single Responsibility Principle class include only functionality connected to database to operate with note
class NoteDataSourceImpl /*@Inject constructor(noteDao: NoteDao)*/: NoteDataSource {

//    private val noteDao: NoteDao by lazy {
//        noteDao
//    }

    private val database: CategoryNoteDatabase = MainActivity.getDatabase()!!

    override suspend fun addNote(note: Note) {
        database.noteDao().addNote(note)
    }

    override suspend fun getNotes(categoryId: Int): List<Note> {
        return database.noteDao().getNotes(categoryId)
    }

    override suspend fun deleteNote(note: Note) {
        database.noteDao().deleteNote(note)
    }

    override suspend fun updateNote(note: Note) {
        database.noteDao().updateNote(note)
    }
}