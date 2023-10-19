package com.example.notes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.EditNoteUseCase
import com.example.notes.domain.GetFavouritesUseCase
import com.example.notes.domain.GetNoteListUseCase
import com.example.notes.domain.Note
import com.example.notes.domain.RemoveNoteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteListViewModel @Inject constructor(
    private val getNoteListUseCase: GetNoteListUseCase,
    private val removeNoteUseCase: RemoveNoteUseCase,
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val editNoteUseCase: EditNoteUseCase,

    ) : ViewModel() {

    val notes = getNoteListUseCase()

    val favouriteNotes = getFavouritesUseCase()

    fun removeNote(noteId: Int) {
        viewModelScope.launch {
            removeNoteUseCase(noteId)
        }
    }

    fun addNoteToFavourites(note: Note): Boolean {
        viewModelScope.launch {
            val copy = note.copy(isFavourite = !note.isFavourite)
            editNoteUseCase(copy)
        }
        return true
    }
}