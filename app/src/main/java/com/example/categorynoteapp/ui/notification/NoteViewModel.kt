package com.example.categorynoteapp.ui.notification

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categorynoteapp.model.Note
import com.example.categorynoteapp.repository.DataResult
import com.example.categorynoteapp.repository.NoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/* Dependency inversion principle. NoteViewModel relay on NoteRepository interface not some of it's
implementation to implement methods*/
/* Barbara Liskov Principle. We can set as a parameter NoteRepository and setup it's implementation
in NoteViewModelFactory */
//Single Responsibility Principle class include only functionality how to get and safe data for category
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {
    val noteListLiveData = MutableLiveData<List<Note>>()
    val categoryId = MutableLiveData(0)

    suspend fun saveNote(note: Note) {
        try {
            noteRepository.addNote(note)
            loadData()
        } catch (e: android.database.sqlite.SQLiteException) {
            Log.i("noteLog", "Couldnt save note" + e.message)
        }
    }

    fun loadData() {
        viewModelScope.launch {
            when (val note = categoryId.value?.let { loadNotes(it) }) {
                is DataResult.Ok -> {
                    noteListLiveData.value = note.response!!
                }
                is DataResult.Error -> note.error
            }
        }
    }

    suspend fun deleteNote(note: Note) {
        try {
            noteRepository.deleteNote(note)
            loadData()
        } catch (e: android.database.sqlite.SQLiteException) {
            Log.i("noteLog", "Couldn't delete note" + e.message)
        }
    }

    private suspend fun loadNotes(categoryId: Int): DataResult<List<Note>> {
        return try {
            val notes = noteRepository.getNotes(categoryId)
            DataResult.Ok(notes)
        } catch (e: Exception) {
            DataResult.Error(e.message.toString())
        }
    }

    //single responsibility principle method to update note
    suspend fun updateNote(noteText: String, noteIdForUpdate: Int, categoryId: Int) {
        val newNote = Note(id = noteIdForUpdate, text = noteText, category_id = categoryId)
        try {
            noteRepository.updateNote(newNote)
            loadData()
        }
        catch (e: android.database.sqlite.SQLiteException) {
            Log.e("noteLog", "Couldn't update note" + e.message);
        }
    }
}