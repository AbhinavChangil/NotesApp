package com.example.notesapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notesapp.data.Note
import com.example.notesapp.ui.theme.Purple40
import kotlin.math.round

@Composable
fun NoteDetailScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NotesEvent) -> Unit
) {
    Scaffold { it ->
        Column(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(it)
                .fillMaxSize()
        ) {


            OutlinedTextField(
                value = state.title.value,
                onValueChange = { state.title.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textStyle = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Start),
                placeholder = {
                    Text(
                        text = "",
                        color = Color.Black
                    )
                }
            )

            OutlinedTextField(
                value = state.disp.value,
                onValueChange = { state.disp.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = {
                    Text(
                        text = "",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
            )

            TextButton(
                onClick = {

                    // Find the existing note to update
                    val existingNote = state.notes.find {
                        it.title == state.title.value && it.disp == state.disp.value
                    } ?: Note(
                        title = state.title.value,
                        disp = state.disp.value,
                        dateAdded = System.currentTimeMillis() // Default date if note is not found
                    )

                    // Trigger the UpdateNote event
                    onEvent(NotesEvent.UpdateNote(existingNote))
                    navController.popBackStack()
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp)
                    .background(Purple40)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Text(text = "Close", color = Color.White)
            }
        }
    }
}
