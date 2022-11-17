package com.example.categorynoteapp.repository

import android.content.Context
import androidx.room.Room

private const val DATABASE_NAME = "category-note-database"
class DatabaseFactory private constructor(context: Context){

    val database : CategoryNoteDatabase = Room.databaseBuilder(
        context.applicationContext,
        CategoryNoteDatabase::class.java,
        DATABASE_NAME
    ).build()

    companion object {
        private var INSTANCE: DatabaseFactory? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = DatabaseFactory(context)
            }
        }

        fun get() : DatabaseFactory {
            return INSTANCE ?:
            throw IllegalStateException("CategoryNoteRepository must be initialized")
        }
    }
}