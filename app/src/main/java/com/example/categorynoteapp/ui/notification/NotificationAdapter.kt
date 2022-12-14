package com.example.categorynoteapp.ui.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.categorynoteapp.databinding.ItemNoteBinding
import com.example.categorynoteapp.model.Note

//Single Responsibility Principle class include only functionality for recyclerViewAdapter for note
class NoteAdapter(
    private val onClickDelete: (Note) -> Unit,
    private val onClickUpdate: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private val listNotes: MutableList<Note> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNoteBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        getNote(position).also {
            viewHolder.bind(it)
        }
    }

    override fun getItemCount() = listNotes.size

    private fun getNote(position: Int): Note {
        return listNotes[position]
    }

    fun setData(listNote: List<Note>) {
        listNotes.clear()
        listNotes.addAll(listNote)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.note = note
            binding.deleteNote.setOnClickListener {
                onClickDelete(note)
            }
            binding.editNote.setOnClickListener {
                onClickUpdate(note)
            }
        }
    }
}