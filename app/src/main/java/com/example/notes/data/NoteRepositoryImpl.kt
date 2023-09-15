package com.example.notes.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.notes.data.database.AppDatabase
import com.example.notes.data.database.NoteMapper
import com.example.notes.domain.Note
import com.example.notes.domain.NoteRepository

class NoteRepositoryImpl(application: Application) : NoteRepository {

    private val noteDao = AppDatabase.getInstance(application).noteDao()
    private val mapper = NoteMapper()

    override fun getNoteList(): LiveData<List<Note>> = Transformations.map(noteDao.getNoteList()) {
        it.map { mapper.mapDbModelToEntity(it) }
    }

    override fun getNote(noteId: Int): Note {
        return mapper.mapDbModelToEntity(noteDao.getNote(noteId))
    }

    override fun addNote(note: Note) {
        noteDao.addNote(mapper.mapEntityToDbModel(note))
    }

    override fun addNoteToFavourites(note: Note) {
    }

    override fun removeNote(note: Note) {
        noteDao.deleteNote(note.id)
    }

    override fun editNote(note: Note) {
        noteDao.addNote(mapper.mapEntityToDbModel(note))
    }
}