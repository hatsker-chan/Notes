package com.example.notes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ActivityNoteListBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRv()

        viewModel.notes.observe(this) {
            noteListAdapter.submitList(it)
        }

        binding.btAddItem.setOnClickListener {
            val intent = NoteItemActivity.newIntentAddMode(this)
            startActivity(intent)
        }
    }

    private fun setSwipeListener() {
        val callback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val note = noteListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.removeNote(note.id)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)

        itemTouchHelper.attachToRecyclerView(binding.rvNoteList)
    }

    private fun setupRv() {
        binding.rvNoteList.adapter = noteListAdapter

        noteListAdapter.onNoteClickListener = {
            val intent = NoteItemActivity.newIntentEditMode(this, it)
            startActivity(intent)
        }

        noteListAdapter.onNoteLongClickListener = {
            viewModel.addNoteToFavourites(it)
        }
        setSwipeListener()
    }
}