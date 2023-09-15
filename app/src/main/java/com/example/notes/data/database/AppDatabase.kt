package com.example.notes.data.database

import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase: RoomDatabase() {

    companion object{

        private var instance: AppDatabase? = null

        fun getInstance(){

        }
    }
}