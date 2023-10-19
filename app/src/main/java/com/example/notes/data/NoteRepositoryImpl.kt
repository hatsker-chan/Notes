package com.example.notes.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.notes.data.database.NoteDao
import com.example.notes.data.database.NoteMapper
import com.example.notes.domain.Note
import com.example.notes.domain.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val mapper: NoteMapper
) : NoteRepository {

    override fun getNoteList(): LiveData<List<Note>> = Transformations.map(noteDao.getNoteList()) {
        it.map { mapper.mapDbModelToEntity(it) }
    }

    override fun getNoteListOfFavourites(): LiveData<List<Note>> {
        return Transformations.map(noteDao.getNoteListOfFavourites()) {
            it.map { mapper.mapDbModelToEntity(it) }
        }
    }

    override suspend fun getNote(noteId: Int): Note {
        return mapper.mapDbModelToEntity(noteDao.getNote(noteId))
    }

    override suspend fun addNote(note: Note) {
        noteDao.addNote(mapper.mapEntityToDbModel(note))
    }

    override fun addNoteToFavourites(note: Note) {
    }

    override suspend fun removeNote(noteId: Int) {
        noteDao.deleteNote(noteId)
    }

    override suspend fun editNote(note: Note) {
        noteDao.addNote(mapper.mapEntityToDbModel(note))
    }
}