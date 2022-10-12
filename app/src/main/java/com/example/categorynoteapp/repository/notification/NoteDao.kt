package com.example.categorynoteapp.repository.note

import androidx.room.*
import com.example.categorynoteapp.model.Note


//interface segregation principle. functionality of interface is only to get CRUD operations for note for room database
@Dao
interface NoteDao {

    @Query("SELECT * FROM note WHERE category_id = :categoryId")
    suspend fun getNotes(categoryId: Int): List<Note>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}