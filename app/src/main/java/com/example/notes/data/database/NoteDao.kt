package com.example.notes.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getNoteList(): LiveData<List<NoteDbModel>>

    @Query("SELECT * FROM notes WHERE isFavourite = 1")
    fun getNoteListOfFavourites(): LiveData<List<NoteDbModel>>

    @Query("SELECT * FROM notes WHERE id=:noteId LIMIT 1")
    suspend fun getNote(noteId: Int): NoteDbModel

    @Query("DELETE FROM notes WHERE id=:noteId")
    suspend fun deleteNote(noteId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: NoteDbModel)
}