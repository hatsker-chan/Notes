package com.example.notes.domain

import androidx.lifecycle.LiveData

interface NoteRepository {

    fun getNoteList(): LiveData<List<Note>>

    fun getNote(noteId: Int): Note

    fun addNote(note: Note)

    fun addNoteToFavourites(note: Note)

    fun removeNote(note: Note)

    fun editNote(note: Note)
}