package com.example.notes.data.database

import com.example.notes.domain.Note

class NoteMapper {
    fun mapDbModelToEntity(dbModel: NoteDbModel): Note = Note(
        id = dbModel.id,
        title = dbModel.title,
        description = dbModel.description,
        datetime = dbModel.dateTime,
        isFavourite = dbModel.isFavourite
    )

    fun mapEntityToDbModel(note: Note): NoteDbModel = NoteDbModel(
        id = note.id,
        title = note.title,
        description = note.description,
        dateTime = note.datetime,
        isFavourite = note.isFavourite
    )
}