package com.example.notes.domain

import javax.inject.Inject

class RemoveNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(noteId: Int) {
        repository.removeNote(noteId)
    }
}