package com.example.notes.domain

class GetNoteUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(noteId: Int): Note {
        return repository.getNote(noteId)
    }
}