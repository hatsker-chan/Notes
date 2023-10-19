package com.example.notes.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetNoteListUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(): LiveData<List<Note>> {
        return repository.getNoteList()
    }
}