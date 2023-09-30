package com.example.notes.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.data.NoteRepositoryImpl
import com.example.notes.domain.AddNoteUseCase
import com.example.notes.domain.EditNoteUseCase
import com.example.notes.domain.GetNoteUseCase
import com.example.notes.domain.Note
import kotlinx.coroutines.launch

class NoteItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NoteRepositoryImpl(application)

    private val editNoteUseCase = EditNoteUseCase(repository)
    private val addNoteUseCase = AddNoteUseCase(repository)
    private val getNoteUseCase = GetNoteUseCase(repository)


    private var _shouldFinishFragment = MutableLiveData(false)
    val shouldFinishFragment: LiveData<Boolean>
        get() = _shouldFinishFragment

    private var _noteItem = MutableLiveData<Note>()
    val noteItem: LiveData<Note>
        get() = _noteItem


    fun getNote(noteId: Int) {
        viewModelScope.launch {
            val note = getNoteUseCase(noteId)
            _noteItem.postValue(note)
        }
    }

    fun addNote(inputTitle: String, inputDescription: String) {
        if (inputDescription.isEmpty() && inputTitle.isEmpty()) {
            return
        }

        viewModelScope.launch {
            addNoteUseCase(
                Note(
                    title = inputTitle,
                    description = inputDescription,
                    datetime = "1 октября"
                )
            )
            finishWork()
        }
    }

    fun editNote(inputTitle: String, inputDescription: String) {
        if (inputDescription.isEmpty() && inputTitle.isEmpty()) {
            return
        }
        viewModelScope.launch {
            _noteItem.value?.let {
                val copyNote = it.copy(title = inputTitle, description = inputDescription)
                editNoteUseCase(copyNote)
            }
            finishWork()
        }

    }

    private fun finishWork() {
        _shouldFinishFragment.value = true
    }
}