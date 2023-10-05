package com.example.notes.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NoteRepositoryImpl
import com.example.notes.domain.EditNoteUseCase
import com.example.notes.domain.GetFavouritesUseCase
import com.example.notes.domain.GetNoteListUseCase
import com.example.notes.domain.Note
import com.example.notes.domain.RemoveNoteUseCase
import kotlinx.coroutines.launch

class NoteListViewModel(private val application: Application) : AndroidViewModel(application) {

    private val repository = NoteRepositoryImpl(application)

    private val getNoteListUseCase = GetNoteListUseCase(repository)
    private val removeNoteUseCase = RemoveNoteUseCase(repository)
    private val editNoteUseCase = EditNoteUseCase(repository)
    private val getFavouritesUseCase = GetFavouritesUseCase(repository)

    val notes = getNoteListUseCase()

    val favouriteNotes = getFavouritesUseCase()


    fun removeNote(noteId: Int) {
        viewModelScope.launch {
            removeNoteUseCase(noteId)
        }
    }

    fun addNoteToFavourites(note: Note): Boolean{
        viewModelScope.launch {
            val copy = note.copy(isFavourite = !note.isFavourite)
            editNoteUseCase(copy)
        }
        return true
    }

}