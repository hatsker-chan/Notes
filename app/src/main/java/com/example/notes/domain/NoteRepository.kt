package com.example.notes.domain

import androidx.lifecycle.LiveData

interface NoteRepository {

    fun getNoteList(): LiveData<List<Note>>

    fun getNoteListOfFavourites(): LiveData<List<Note>>

    suspend fun getNote(noteId: Int): Note

    suspend fun addNote(note: Note)

    fun addNoteToFavourites(note: Note)

    suspend fun removeNote(noteId: Int)

    suspend fun editNote(note: Note)
}