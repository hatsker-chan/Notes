package com.example.notes.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.notes.R
import com.example.notes.databinding.ActivityNoteItemBinding
import com.example.notes.domain.Note
import com.example.notes.presentation.NoteListActivity.Companion.EXTRA_THEME_KEY

class NoteItemActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNoteItemBinding.inflate(layoutInflater)
    }

    private val settings by lazy {
        PreferenceManager.getDefaultSharedPreferences(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(settings.getInt(EXTRA_THEME_KEY, -1))
        parseIntent()
        setContentView(binding.root)
        launchFragment()
    }

    private fun parseIntent() {
        val mode = this.intent.getStringExtra(EXTRA_MODE)
            ?: throw RuntimeException("no mode param in intent")

        when (mode) {
            ADD_MODE -> supportActionBar?.setTitle(getString(R.string.add_screen_action_bar))
            EDIT_MODE -> supportActionBar?.setTitle(getString(R.string.edit_screen_action_bar))
        }
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