package com.example.notes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.AddNoteUseCase
import com.example.notes.domain.EditNoteUseCase
import com.example.notes.domain.GetNoteUseCase
import com.example.notes.domain.Note
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class NoteItemViewModel @Inject constructor(
    private val editNoteUseCase: EditNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase
) : ViewModel() {

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
                    datetime = getCurrentDateTime()
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
                val copyNote = it.copy(
                    title = inputTitle,
                    description = inputDescription,
                    datetime = getCurrentDateTime()
                )

                editNoteUseCase(copyNote)
            }
            finishWork()
        }
    }

    private fun finishWork() {
        _shouldFinishFragment.value = true
    }

    private fun getCurrentDateTime(): String {
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm ") // "yyyy-MM-dd HH:mm"
        val current = LocalDateTime.now().format(formatter)
        return current
    }
}