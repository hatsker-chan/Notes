package com.example.notes.di

import androidx.lifecycle.ViewModel
import com.example.notes.presentation.NoteItemViewModel
import com.example.notes.presentation.NoteListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NoteListViewModel::class)
    fun bindNoteListViewModel(noteListViewModel: NoteListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NoteItemViewModel::class)
    fun bindNoteItemViewModel(noteItemViewModel: NoteItemViewModel): ViewModel
}