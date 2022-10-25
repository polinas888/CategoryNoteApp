package com.example.categorynoteapp.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.categorynoteapp.closeFragment
import com.example.categorynoteapp.databinding.NoteCreateOrChangeFragmentBinding
import com.example.categorynoteapp.model.Note
import com.google.gson.GsonBuilder

const val IS_NEW_NOTE = "isNewNote"

/*Single Responsibility Principle class include functionality for creating or change note.
Here not following Single Responsibility Principle.
Could be separated just for create and just for change but it is the same layout and better to leave this way */
class NoteCreateOrChangeFragment : Fragment() {
    private lateinit var binding: NoteCreateOrChangeFragmentBinding
    private var isNewNote = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = NoteCreateOrChangeFragmentBinding.inflate(layoutInflater)

        setOldTextForUpdatedNote()

        binding.cancelButton.setOnClickListener {
            this.closeFragment()
        }

        binding.okButton.setOnClickListener {
            passNewNoteTextArgs()
            this.closeFragment()
        }
        return binding.root
    }

    //Single Responsibility Principle method to pass new note text through arguments
    private fun passNewNoteTextArgs() {
        setFragmentResult(
            NOTE_REQUEST_KEY,
            bundleOf(ARG_NOTE to getNewNoteText(), IS_NEW_NOTE to isNewNote)
        )
    }

    //Single Responsibility Principle method to get new note text
    private fun getNewNoteText() :  String {
        return binding.createOrEditNoteEditText.text.toString()
    }

    //Single Responsibility Principle method gets args
    private fun getNoteArgs() : Note? {
        val gson = GsonBuilder().create()
        return gson.fromJson(arguments?.getString(ARG_NOTE), Note::class.java)
    }

    //Single Responsibility Principle method setup that note isn't new
    private fun isNotNewNote() {
        if (getNoteArgs() != null) {
            isNewNote = false
        }
    }

    //Single Responsibility Principle method sets old text for updated note
    private fun setOldTextForUpdatedNote() {
        isNotNewNote()
        val noteArgs = getNoteArgs()
        if (getNoteArgs() != null) {
            binding.createOrEditNoteEditText.setText(noteArgs?.text)
        }
    }
}