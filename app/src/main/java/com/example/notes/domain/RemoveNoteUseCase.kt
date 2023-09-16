package com.example.notes.domain

class RemoveNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(noteId: Int){
        repository.removeNote(noteId)
    }
}