package com.example.notes.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private const val NAME = "main.db"

        private var INSTANCE: AppDatabase? = null

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            val database = Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                NAME
            ).build()
            INSTANCE = database
            return database
        }
    }


}