package com.example.categorynoteapp.repository.notification

import com.example.categorynoteapp.model.Note
import com.example.categorynoteapp.repository.NoteRepository
import javax.inject.Inject

/* Barbara Liskov Principle. NoteRepositoryImpl override all methods of NoteRepository interface
 and program will work correctly with substitution of NoteRepository to NoteRepositoryImpl */
/* open closed principle one of implementation of note repository interface
open to extend(we can add more functionality) but class doesn't change  */
class NoteRepositoryImpl @Inject constructor(
    private val noteDataSource: NoteDataSource
): NoteRepository {
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