package com.example.notesapp.presentation

import android.provider.ContactsContract
import com.example.notesapp.data.Note

sealed interface NotesEvent {

    //whenever we make sealed class, variable types remain same as class name
    object SortNotes : NotesEvent
    data class DeleteNote(var note : Note) : NotesEvent
    data class SaveNote(
        var title : String,
        var disp: String
    ) : NotesEvent
}