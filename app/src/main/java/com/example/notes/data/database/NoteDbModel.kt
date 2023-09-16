package com.example.notes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class NoteDbModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String
)