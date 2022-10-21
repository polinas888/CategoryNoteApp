package com.example.categorynoteapp.ui.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.categorynoteapp.repository.SomeDifficultOperationFacade
import com.example.categorynoteapp.repository.notification.NoteRepositoryImpl
import javax.inject.Inject

class NoteViewModelFactory @Inject constructor(
    val noteRepository: NoteRepositoryImpl,
    val someDifficultOperationFacade: SomeDifficultOperationFacade
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java))
            return NoteViewModel(noteRepository, someDifficultOperationFacade) as T
        else
            throw IllegalArgumentException("Unable to construct viewModel")
    }
}