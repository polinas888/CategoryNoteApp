package com.example.categorynoteapp.ui.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.categorynoteapp.repository.note.noteRepository
import javax.inject.Inject

class NoteViewModelFactory @Inject constructor(
    val noteRepository: noteRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java))
            return NoteViewModel(noteRepository) as T
        else
            throw IllegalArgumentException("Unable to construct viewModel")
    }
}