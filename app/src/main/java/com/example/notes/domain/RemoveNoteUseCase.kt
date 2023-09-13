package com.example.notes.domain

class RemoveNoteUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(note: Note){
        repository.removeNote(note)
    }
}