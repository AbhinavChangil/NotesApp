package com.example.notesapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.notesapp.data.Note

data class NoteState ( // round brackets as we have passed parameters here

    // here we will 3 states -> 1) List of Notes,  2) Where we will add data( Title and Description)

    // we will use var for notes here as these need to be mutable otherwise read only will not allow us to copy or delete notes
    var notes : List<Note> = emptyList(),
    var title : MutableState<String> = mutableStateOf(""),
    var disp : MutableState<String> = mutableStateOf(""),
    var sortBy: MutableState<String> = mutableStateOf("Date Added")
)