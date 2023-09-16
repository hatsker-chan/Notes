package com.example.notes.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notes.R
import com.example.notes.databinding.ActivityNoteItemBinding

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
            return Intent(context, NoteItemActivity::class.java)
        }
    }
}