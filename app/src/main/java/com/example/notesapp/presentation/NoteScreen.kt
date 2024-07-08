package com.example.notesapp.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notesapp.ui.theme.Purple80

@Composable
fun NoteScreen (
    state : NoteState,
    navController : NavController,
    onEvent: (NotesEvent) -> Unit
){

    Scaffold (
        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(Black)
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Notes App",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                IconButton(onClick = { /*TODO*/
                    onEvent(NotesEvent.SortNotes) })
                {
                    Icon(imageVector = Icons.Rounded.Sort , contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = Purple80
                    )

                }
            }
        },

        floatingActionButton = {
            FloatingActionButton(containerColor = Purple80,
                onClick = { /*TODO*/
                    state.title.value=""
                    state.disp.value=""
                    navController.navigate("AddNoteScreen")
                }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }
    ){
        // we will use Lazy Column because we can have many notes here
        LazyColumn (contentPadding = it,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceAround){

            items(state.notes.size){
                NoteItem(
                    state = state,
                    index = it,
                    onEvent = onEvent
                )
            }
        }
    }
}

@Composable
fun NoteItem(state: NoteState, index: Int, onEvent: (NotesEvent) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(Black)
        .padding(12.dp)

    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = state.notes.get(index = index).title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = state.notes.get(index = index).disp,
                fontSize = 16.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
        }

        IconButton(onClick = { /*TODO*/
            onEvent(NotesEvent.DeleteNote(
                state.notes.get(index=index)
            ))
        }) {

            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = null,
                modifier = Modifier.size(35.dp)
            )
        }
    }
}
