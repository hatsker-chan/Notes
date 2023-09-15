package com.example.notes.domain

interface NoteRepository {

    fun getNoteList(): List<Note>

    fun getNote(noteId: Int)

    fun addNote(note: Note)

    fun addNoteToFavourites(note: Note)

    fun removeNote(note: Note)

    fun editNote(note: Note)
}