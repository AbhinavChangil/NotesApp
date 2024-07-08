package com.example.notesapp.presentation


import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.w3c.dom.Text
import kotlin.coroutines.coroutineContext

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
            if(state.title.value == "" && state.disp.value == ""){
                Error("hey")
            }
            else {
                onEvent(
                    NotesEvent.SaveNote(
                        title = state.title.value,
                        disp = state.disp.value
                    )

                )
            }
            //scree se bahr jana hai
            navController.popBackStack()


        }, modifier = Modifier.padding(end = 160.dp, bottom = 30.dp)) {
            Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
        }}

    ){
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(top = 50.dp),

        ) {
            OutlinedTextField(
                value = state.title.value,
                onValueChange = { state.title.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = {
                    Text( "Title",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold

                    )
                }
            )

            Spacer(modifier = Modifier.padding(5.dp))

            OutlinedTextField(
                value = state.disp.value,
                onValueChange = { state.disp.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp, start = 16.dp, end = 16.dp, top = 5.dp),
                placeholder = {
                    Text("Description",
                        color = Color.Black,)
                }
            )

//                FloatingActionButton(onClick = { /*TODO*/
//                    if(state.title.value == "" && state.disp.value == ""){
//                        Error("hey")
//                    }
//                    else {
//                        onEvent(
//                            NotesEvent.SaveNote(
//                                title = state.title.value,
//                                disp = state.disp.value
//                            )
//
//                        )
//                    }
//                    //scree se bahr jana hai
//                    navController.popBackStack()
//
//
//                }, modifier = Modifier.padding(start = 180.dp, bottom = 1.dp)) {
//                    Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
//                }
            }
        }

    }



