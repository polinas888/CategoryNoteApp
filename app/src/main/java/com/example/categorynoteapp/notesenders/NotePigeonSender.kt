package com.example.categorynoteapp.notesenders

import android.util.Log

class NotePigeonSender {

    fun writeLetter() {
        Log.i("Sender", "write letter on paper")
    }

    fun tieLetterToPaw() {
        Log.i("Sender", "note tied to the paw of the pigeon")
    }

    fun releaseBird() {
        Log.i("Sender", "note by pigeon is send")
    }

    fun returnNote() {
        Log.i("Sender", "note returned")
    }
}