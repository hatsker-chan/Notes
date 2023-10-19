package com.example.notes.di

import android.app.Application
import com.example.notes.data.NoteRepositoryImpl
import com.example.notes.data.database.AppDatabase
import com.example.notes.data.database.NoteDao
import com.example.notes.domain.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindNoteRepository(noteRepository: NoteRepositoryImpl): NoteRepository

    companion object {
        @Provides
        fun provideNoteDao(application: Application): NoteDao {
            return AppDatabase.getInstance(application).noteDao()
        }
    }
}