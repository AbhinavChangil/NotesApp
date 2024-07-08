package com.example.notesapp.presentation


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.example.notesapp.ui.theme.Purple80
import com.example.notesapp.data.Note



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteScreen (
    state : NoteState,
    navController : NavController,
    onEvent: (NotesEvent) -> Unit
){

    Scaffold (
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Black)
                    .padding(top = 40.dp)
            ) {
                Text(
                    text = "All notes",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                        .padding(bottom = 12.dp),
                    fontWeight = FontWeight.Medium,
                    fontSize = 35.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "${state.notes.size} notes",
                    fontSize = 22.sp,
                    modifier = Modifier.align(CenterHorizontally),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )




                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom
                ) {IconButton(onClick = {
                    onEvent(NotesEvent.SortNotes)
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Sort,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = Purple80
                    )
                }
                    // Display the current sorting method

                    Text(
                        text = "Sorted by ${state.sortBy.value}",
                        color = Color.White,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp)
                    )}

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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Black) // Set the background color of the entire screen to black
                .padding(it)
        ) {



            // we will use Lazy Column because we can have many notes here
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Set the number of columns for the grid
                modifier = Modifier
                    .width(400.dp)
                    .padding(start = 5.dp, end = 5.dp, top = 10.dp, bottom = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(state.notes.size) { index ->
                    NoteItem(
                        note = state.notes[index],
                        onNoteClick = {
                            state.title.value = state.notes[index].title
                            state.disp.value = state.notes[index].disp
                            navController.navigate("NoteDetailScreen")

                            // Navigate to detail screen using NavController

                        },
                        onDeleteClick = {
                            onEvent(NotesEvent.DeleteNote(state.notes[index])) }
                    )
                }
            }
        }
    }
}

@Composable
fun NoteItem(note: Note,
             onNoteClick: () -> Unit,
             onDeleteClick: () -> Unit) {

//    var isExpanded by remember { mutableStateOf(false) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(Gray)
        .padding(start = 5.dp, top = 3.dp, bottom = 3.dp)
        .clickable { onNoteClick() }
    )



    {
        Column(
            modifier = Modifier
                .weight(1f)
                .height(200.dp)
        ) {
            Text(
                text = note.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = note.disp,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
        }

        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.size(25.dp)
        // Change the tint color here
        ){

            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = Black
            )
        }
    }

    // Show NoteDetailView when isExpanded is true
//    if (isExpanded) {
////        NoteDetailScreen(
////            note = state.notes[index],
////            onClose = { isExpanded = false }
////        )
//
//    }

}





