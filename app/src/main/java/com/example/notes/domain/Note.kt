package com.example.notes.domain

data class Note(
    val id: Int = UNDEFINED_ID,
    val title: String,
    val description: String
) {
    companion object {
        private const val UNDEFINED_ID = 0
    }
}