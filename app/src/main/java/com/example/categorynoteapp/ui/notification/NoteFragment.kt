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
import com.example.categorynoteapp.ui.notification.NoteViewModel
import com.example.categorynoteapp.ui.notification.NoteViewModelFactory
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
             handleNote(bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(layoutInflater)
        binding.addButton.setOnClickListener {
            //single responsibility principle to open noteCreateOrChangeFragment
            openNoteCreateOrChangeFragment()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            noteAdapter = NoteAdapter (
                { categoryList -> deleteNote(categoryList)},
                { categoryList -> openFragmentUpdateNote(categoryList) }
            )
            binding.noteRecyclerView.adapter = noteAdapter
            binding.noteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        categoryId = arguments?.getInt(ARG_CATEGORY_ID)!!
        noteViewModel.categoryId.value = categoryId

        (activity as MainActivity).supportActionBar?.title = getString(R.string.toolbar_title_note) + " $categoryId"

        noteViewModel.noteListLiveData.observe(viewLifecycleOwner) { note ->
            setupNotesRecyclerView(note)
            binding.progressBar.visibility = View.GONE
        }
        noteViewModel.loadData()
    }


    private fun handleNote(bundle: Bundle) {
        val note = bundle.getString(ARG_NOTE)
        val isNewNote = bundle.getBoolean(IS_NEW_NOTE)

        note?.let {
            if (isNewNote) {
                lifecycleScope.launch {
                    //single responsibility principle wrote method for saving new note
                    saveNewNote(note)
                    //single responsibility principle wrote method for updating UI after change
                    noteViewModel.loadData()
                }
            } else {
                lifecycleScope.launch {
                    //single responsibility principle wrote method for updating new note
                    noteViewModel.updateNote(note, noteIdForUpdate, categoryId)
                    noteViewModel.loadData()
                }
            }
        }
    }

    //single responsibility principle method to setup data or empty list
    private fun setupNotesRecyclerView(notes: List<Note>) {
        noteAdapter.setData(notes)
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

    //single responsibility principle method to save new note
    private suspend fun saveNewNote(noteText: String) {
        val newNote = Note(text = noteText, category_id = categoryId)
        binding.progressBar.visibility = View.VISIBLE
            noteViewModel.saveNote(newNote)
    }

    private fun openNoteCreateOrChangeFragment() {
        WorkWithNoteFragment().changeFragment(Bundle(), parentFragmentManager)
    }

    private fun openFragmentUpdateNote(note: Note) {
        lifecycleScope.launch {
            noteIdForUpdate = note.id
            val fragment = WorkWithNoteFragment()
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
            noteViewModel.loadData()
        }
    }
}
