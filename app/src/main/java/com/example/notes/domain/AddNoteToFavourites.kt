package com.example.notes.domain

class AddNoteToFavourites(
    private val repository: NoteRepository
) {
    operator fun invoke(note: Note) {
        repository.addNoteToFavourites(note)
    }
}