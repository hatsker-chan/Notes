package com.example.notes.domain

import javax.inject.Inject

class AddNoteToFavourites @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(note: Note) {
        repository.addNoteToFavourites(note)
    }
}