package com.example.notes.domain

class EditNoteUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(note: Note){
        repository.editNote(note)
    }
}