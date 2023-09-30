package com.example.notes.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NoteRepositoryImpl
import com.example.notes.domain.GetNoteListUseCase
import com.example.notes.domain.RemoveNoteUseCase
import kotlinx.coroutines.launch

class NoteListViewModel(private val application: Application) : AndroidViewModel(application) {

    private val repository = NoteRepositoryImpl(application)

    private val getNoteListUseCase = GetNoteListUseCase(repository)
    private val removeNoteUseCase = RemoveNoteUseCase(repository)

    val notes = getNoteListUseCase()


    fun removeNote(noteId: Int) {
        viewModelScope.launch {
            removeNoteUseCase(noteId)
        }
    }
}