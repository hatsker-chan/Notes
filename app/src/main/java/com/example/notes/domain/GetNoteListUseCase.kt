package com.example.notes.domain

class GetNoteListUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(): List<Note> {
        return repository.getNoteList()
    }
}