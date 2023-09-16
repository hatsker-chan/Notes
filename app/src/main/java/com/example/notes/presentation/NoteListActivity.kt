package com.example.notes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notes.databinding.ActivityNoteListBinding
import com.example.notes.domain.Note
import com.example.notes.presentation.adapter.NoteListAdapter

class NoteListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNoteListBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[NoteListViewModel::class.java]
    }

    private val noteListAdapter by lazy {
        NoteListAdapter()
    }

    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvNoteList.adapter = noteListAdapter

        noteListAdapter.onNoteClickListener = {
            viewModel.removeNote(it.id)
        }

        viewModel.notes.observe(this) {
            noteListAdapter.submitList(it)
        }

        binding.btAddItem.setOnClickListener {
            viewModel.addNote(Note(
                description = "jfal;jflajsf",
                title = "Title ${i++}"
            ))
        }


    }
}