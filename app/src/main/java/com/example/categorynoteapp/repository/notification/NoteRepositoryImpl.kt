package com.example.categorynoteapp.repository.notification

import com.example.categorynoteapp.model.Note
import com.example.categorynoteapp.repository.NoteRepository
import com.example.categorynoteapp.repository.note.NoteDataSourceImpl
import javax.inject.Inject

/* Dependency inversion principle. NoteRepositoryImpl uses NoteRepository interface to create
 (override) methods and relay on NoteDataSource interface not some of it's implementation to implement methods */
/* Barbara Liskov Principle. NoteRepositoryImpl override all methods of NoteRepository interface
 and program will work correctly with substitution of NoteRepository to NoteRepositoryImpl */
/* open closed principle one of implementation of note repository interface
open to extend(we can add more functionality) but class doesn't change  */
class NoteRepositoryImpl: NoteRepository {
    private val noteDataSource: NoteDataSource = NoteDataSourceImpl()

    override suspend fun addNote(note: Note) {
        noteDataSource.addNote(note)
    }

    override suspend fun getNotes(categoryId: Int): List<Note> {
        return noteDataSource.getNotes(categoryId)
    }

    override suspend fun deleteNote(note: Note) {
        noteDataSource.deleteNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDataSource.updateNote(note)
    }
}