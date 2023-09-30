package com.example.notes.domain

class EditNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.editNote(note)
    }
}