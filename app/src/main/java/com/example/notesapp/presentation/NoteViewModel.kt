package com.example.notesapp.presentation


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Here we will perform all the operations
class NoteViewModel(
    // Because with dao it cannot access data
    private val dao: NoteDao
) : ViewModel() { // It will inherit ViewModel

    private val isSortedByDateAdded: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val notes = isSortedByDateAdded.flatMapLatest {
        if (it) {
            // If it is sortedByDateAdded we will show data orderedByDateAdded
            dao.getOrderedByDateAdded()
        } else {
            // Else we will show data orderedByTitle
            dao.getOrderedByTitle()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(NoteState())
    var state = combine(_state, isSortedByDateAdded, notes) {
            state, isSortedByDateAdded, notes ->
        state.copy(
            // We will copy all notes in this note variable
            notes = notes

        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteState())

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.DeleteNote -> {
                // We will use viewModelScope so that we can perform delete operation
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }
            is NotesEvent.SaveNote -> {
                // Handle save note event
                val note = Note(
                    title = state.value.title.value ,
                    disp = state.value.disp.value,
                    dateAdded = System.currentTimeMillis()
                )

                viewModelScope.launch {
                    dao.upsertNote(note)
                }
                _state.update {
                    it.copy(
                        title = mutableStateOf(""),
                        disp = mutableStateOf("")
                    )
                }
            }
            NotesEvent.SortNotes -> {
                // Handle sort notes event
                isSortedByDateAdded.value = !isSortedByDateAdded.value
                _state.update {
                    it.copy(
                        sortBy = mutableStateOf(if (isSortedByDateAdded.value) "Date Added" else "Title")
                    )
                }
            }
        }
    }
}