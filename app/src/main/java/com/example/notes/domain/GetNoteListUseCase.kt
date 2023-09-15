package com.example.notes.domain

import androidx.lifecycle.LiveData

class GetNoteListUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(): LiveData<List<Note>> {
        return repository.getNoteList()
    }
}