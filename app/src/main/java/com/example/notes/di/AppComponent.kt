package com.example.notes.di

import android.app.Application
import com.example.notes.presentation.NoteItemFragment
import com.example.notes.presentation.NoteListActivity
import com.example.notes.presentation.NoteListOfFavouritesActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(noteListActivity: NoteListActivity)

    fun inject(noteItemFragment: NoteItemFragment)

    fun inject(noteListOfFavouritesActivity: NoteListOfFavouritesActivity)

    @Component.Factory
    interface AppComponentFactory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}