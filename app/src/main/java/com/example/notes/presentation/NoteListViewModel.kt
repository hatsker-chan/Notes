package com.example.notes.presentation

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notes.data.NoteRepositoryImpl
import com.example.notes.domain.GetNoteListUseCase
import com.example.notes.domain.GetNoteUseCase
import com.example.notes.domain.RemoveNoteUseCase

class NoteListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NoteRepositoryImpl(application)

    private val getNoteUseCase = GetNoteUseCase(repository)
    private val getNoteListUseCase = GetNoteListUseCase(repository)
    private val removeNoteUseCase = RemoveNoteUseCase(repository)

    val notes = getNoteListUseCase()

}