package com.example.notes.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.notes.databinding.NoteItemBinding
import com.example.notes.domain.Note

class NoteListAdapter : ListAdapter<Note, NoteViewHolder>(NoteDiffUtil()) {

    var onNoteClickListener: ((Note) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        Log.d("RecyclerView", "OnCreate")
        val binding = NoteItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        val binding = holder.binding

        with(binding) {
            if (note.title.trim().isEmpty()) {
                tvTitle.visibility = View.GONE
            } else {
                tvTitle.text = note.title
            }

            if (note.description.trim().isEmpty()) {
                tvDescription.visibility = View.GONE
            } else {
                tvDescription.text = note.description
            }

            tvDatetime.text = "14 сентября"

            root.setOnClickListener {
                onNoteClickListener?.invoke(note)
            }
        }
    }

    private fun getDatetime() {

    }
}