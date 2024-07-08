package com.example.notesapp.data


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    //We will define all functions here

    @Upsert
    // suspended function use krenge mtlb couroutine bhi hum yha use karenge
    suspend fun upsertNote(note : Note)

    @Delete
    suspend fun deleteNote(note : Note)

    @Query("SELECT * FROM note ORDER BY title ASC")
    fun getOrderedByTitle() : Flow<List<Note>>  // Import Flow from kotlin coroutines

    @Query("SELECT * FROM note ORDER BY dateAdded ASC")
    fun getOrderedByDateAdded() : Flow<List<Note>>  // Import Flow from kotlin coroutines
}