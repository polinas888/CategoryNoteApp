package com.example.categorynoteapp.ui.note

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categorynoteapp.model.Note
import com.example.categorynoteapp.repository.DataResult
import com.example.categorynoteapp.repository.note.noteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

//Single Responsibility Principle class include only functionality how to get and safe data for category
class NoteViewModel @Inject constructor(private val noteRepository: noteRepository) :
    ViewModel() {
    val noteListLiveData = MutableLiveData<List<Note>>()
    val categoryId = MutableLiveData(0)

    suspend fun saveNote(note: Note) {
        try {
            noteRepository.addNote(note)
        } catch (exception: SQLiteConstraintException) {
            Log.i("noteLog", "Couldnt save note")
        }
    }

    fun loadData() {
        viewModelScope.launch {
            when (val note = categoryId.value?.let { getNotes(it) }) {
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
        } catch (e: java.lang.Exception) {
            Log.i("noteLog", "Couldn't delete note")
        }
    }

    suspend fun updateNote(note: Note) {
        try {
            noteRepository.updateNote(note)
        } catch (e: Exception) {
            Log.i("noteLog", "Couldn't update note")
        }
    }

    private suspend fun getNotes(categoryId: Int): DataResult<List<Note>> {
        return try {
            val notes = noteRepository.getNotes(categoryId)
            DataResult.Ok(notes)
        } catch (e: Exception) {
            DataResult.Error(e.message.toString())
        }
    }
}