package com.example.categorynoteapp.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categorynoteapp.MainActivity
import com.example.categorynoteapp.R
import com.example.categorynoteapp.appComponent
import com.example.categorynoteapp.changeFragment
import com.example.categorynoteapp.databinding.FragmentNoteBinding
import com.example.categorynoteapp.model.Note
import com.example.categorynoteapp.ui.category.ARG_CATEGORY_ID
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import javax.inject.Inject

const val ARG_NOTE = "arg_note"
const val NOTE_REQUEST_KEY = "requestKey"

//Single Responsibility Principle class include only functionality note needs to operate in UI layout
class NoteFragment : Fragment() {
    private lateinit var binding: FragmentNoteBinding
    private lateinit var noteAdapter: NoteAdapter
    private var categoryId = 0
    private var noteIdForUpdate = 0

    @Inject
    lateinit var noteViewModelFactory: NoteViewModelFactory
    private val noteViewModel by viewModels<NoteViewModel> {
        noteViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireContext().appComponent.inject(this)

        setFragmentResultListener(NOTE_REQUEST_KEY) { requestKey, bundle ->
            val note = bundle.getString(ARG_NOTE)
            val isNewNote = bundle.getBoolean(IS_NEW_NOTE)

            if (note != null) {
                if (isNewNote) {
                    lifecycleScope.launch {
                        //single responsibility principle wrote method for saving new note
                        saveNewNote(note)
                        //single responsibility principle wrote method for updating UI after change
                        updateUiAfterChange()
                    }
                } else {
                    lifecycleScope.launch {
                        //single responsibility principle wrote method for updating new note
                        openFragmentUpdateNote(note)
                        updateUiAfterChange()
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(layoutInflater)
        binding.noteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryId = arguments?.getInt(ARG_CATEGORY_ID)!!
        noteViewModel.categoryId.value = categoryId

        (activity as MainActivity).supportActionBar?.title = getString(R.string.toolbar_title_note) + " $categoryId"

        noteViewModel.noteListLiveData.observe(viewLifecycleOwner) { note ->
          //single responsibility principle created method to setup data
            setupDataOrEmptyListOnUi(note)
            binding.progressBar.visibility = View.GONE
        }
        noteViewModel.loadData()

        binding.addButton.setOnClickListener {
            //single responsibility principle to open noteCreateOrChangeFragment
            openNoteCreateOrChangeFragment()
        }
    }

    //single responsibility principle method to setup data or empty list
    private fun setupDataOrEmptyListOnUi(notes: List<Note>) {
        updateUI(notes)
        setupVisibilityOfEmptyList(notes)
    }

    //single responsibility principle method to setup visibility of EmptyList
    private fun setupVisibilityOfEmptyList(notes: List<Note>) {
        if (notes.isEmpty()) {
            binding.emptyListText.visibility = View.VISIBLE
        } else {
            binding.emptyListText.visibility = View.INVISIBLE
        }
    }

    //single responsibility principle method updated Ui after change
    private fun updateUiAfterChange() {
        lifecycleScope.launch {
            noteViewModel.loadData()
            noteViewModel.noteListLiveData.value?.let {
                setupDataOrEmptyListOnUi(it)
                binding.progressBar.visibility = View.GONE}
        }
    }

    //single responsibility principle method to save new note
    private suspend fun saveNewNote(noteText: String) {
        val newNote = Note(text = noteText, category_id = categoryId)
        binding.progressBar.visibility = View.VISIBLE
            noteViewModel.saveNote(newNote)
    }

    //single responsibility principle method to update note
    private suspend fun openFragmentUpdateNote(noteText: String) {
        val newNote = Note(
            id = noteIdForUpdate,
            text = noteText,
            category_id = categoryId
        )
            noteViewModel.updateNote(newNote)
    }

    private fun updateUI(notes: List<Note>) {
        noteAdapter = NoteAdapter((notes),
            { note -> deleteNote(note) },
            { note -> openFragmentUpdateNote(note) })
        binding.noteRecyclerView.adapter = noteAdapter
    }

    private fun openNoteCreateOrChangeFragment() {
        val fragment = NoteCreateOrChangeFragment()
        val args = Bundle()
        fragment.changeFragment(args, parentFragmentManager)
    }

    private fun openFragmentUpdateNote(note: Note) {
        lifecycleScope.launch {
            noteIdForUpdate = note.id
            val fragment = NoteCreateOrChangeFragment()
            val args = Bundle()
            val builder = GsonBuilder()
            val gson = builder.create()
            val result: String = gson.toJson(note)
            args.putString(ARG_NOTE, result)
            fragment.changeFragment(args, parentFragmentManager)
        }
    }

    private fun deleteNote(note: Note) {
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            noteViewModel.deleteNote(note)
            noteViewModel.noteListLiveData.observe(viewLifecycleOwner) { notes ->
                setupDataOrEmptyListOnUi(notes)
                binding.progressBar.visibility = View.GONE
            }
            noteViewModel.loadData()
        }
    }
}
