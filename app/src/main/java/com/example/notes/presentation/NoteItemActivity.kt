package com.example.notes.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notes.R
import com.example.notes.databinding.ActivityNoteItemBinding
import com.example.notes.domain.Note

class NoteItemActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNoteItemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        launchFragment()
    }

    private fun launchFragment() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NoteItemFragment.newInstance())
            .commit()
    }

    companion object {
        fun newIntentAddMode(context: Context): Intent {
            return Intent(context, NoteItemActivity::class.java).apply {
                putExtra(EXTRA_MODE, ADD_MODE)
            }
        }

        fun newIntentEditMode(context: Context, note: Note): Intent {
            return Intent(context, NoteItemActivity::class.java).apply {
                putExtra(EXTRA_MODE, EDIT_MODE)
                putExtra(EXTRA_NOTE_ID, note.id)
            }
        }

        const val EXTRA_MODE = "mode"
        private const val ADD_MODE = "add"
        private const val EDIT_MODE = "edit"
        const val EXTRA_NOTE_ID = "id"
    }
}