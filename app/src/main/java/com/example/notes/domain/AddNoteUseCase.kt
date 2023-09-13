package com.example.notes.domain

class AddNoteUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(note: Note){
        repository.addNote(note)
    }
}