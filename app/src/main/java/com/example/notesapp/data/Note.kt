package com.example.notesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title : String,
    val disp : String,
    var dateAdded : Long
)