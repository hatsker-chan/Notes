package com.example.notes.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notes.data.NoteRepositoryImpl
import com.example.notes.domain.AddNoteUseCase
import com.example.notes.domain.EditNoteUseCase
import com.example.notes.domain.GetNoteUseCase
import com.example.notes.domain.Note

class NoteItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NoteRepositoryImpl(application)

    private val editNoteUseCase = EditNoteUseCase(repository)
    private val addNoteUseCase = AddNoteUseCase(repository)
    private val getNoteUseCase = GetNoteUseCase(repository)

    fun getNote(noteId: Int): Note {
        return getNoteUseCase(noteId)
    }

    fun editNote(note: Note) {

    }
}