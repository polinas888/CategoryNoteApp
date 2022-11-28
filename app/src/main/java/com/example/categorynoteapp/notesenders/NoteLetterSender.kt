package com.example.categorynoteapp.notesenders

import android.util.Log

class NoteLetterSender {

    fun writeLetter() {
        Log.i("Sender", "write letter on paper")
    }

    fun sendNote() {
        Log.i("Sender", "letter is send")
    }

    fun returnNote() {
        Log.i("Sender", "note returned")
    }
}