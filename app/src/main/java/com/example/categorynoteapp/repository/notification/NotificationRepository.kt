package com.example.categorynoteapp.repository.note

import com.example.categorynoteapp.model.Note
import javax.inject.Inject

//Single Responsibility Principle class include only functionality connected to database to operate with note
class NoteRepository @Inject constructor(noteDao: NoteDao): NoteDao {

    private val noteDao: NoteDao by lazy {
        noteDao
    }

    override suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    override suspend fun getNotes(categoryId: Int): List<Note> {
        return noteDao.getNotes(categoryId)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }
}