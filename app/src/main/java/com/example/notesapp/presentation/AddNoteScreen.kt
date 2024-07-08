package com.example.notesapp.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import androidx.compose.material3.Text
import org.w3c.dom.Text

@Composable
fun AddNoteScreen(
    //first of all we will take a state beacuse we will add values of all variables in a state
    state : NoteState,
    //navController ki help se control karenge
    navController : NavController,
    onEvent: (NotesEvent) -> Unit

){
    //we will use scaffold it provide extra space
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/
                onEvent(
                    NotesEvent.SaveNote(
                        title = state.title.value,
                        disp = state.disp.value
                    )
                )
                //scree se bahr jana hai
                navController.popBackStack()


            }) {
                Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
            }
        }
    ){
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            OutlinedTextField(
                value = state.title.value,
                onValueChange = { state.title.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = {
                    Text( "Title", Modifier.size(18.dp))
                }
            )
            OutlinedTextField(
                value = state.disp.value,
                onValueChange = { state.disp.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = {
                    Text("Description")
                }
            )
        }
    }
}