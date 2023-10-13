package com.example.notes.domain

import androidx.lifecycle.LiveData

class GetFavouritesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(): LiveData<List<Note>> {
        return repository.getNoteListOfFavourites()
    }
}