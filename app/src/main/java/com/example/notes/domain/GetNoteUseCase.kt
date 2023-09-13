package com.example.notes.domain

class GetNoteUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(noteId: Int){
        return repository.getNote(noteId)
    }
}