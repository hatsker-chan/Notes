package com.example.notes.domain

data class Note(
    val id: Int = UNDEFINED_ID,
    val title: String,
    val description: String,
    var datetime: String,
    var isFavourite: Boolean = false
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}