package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.notesapp.data.NoteDatabase
import com.example.notesapp.presentation.AddNoteScreen
import com.example.notesapp.presentation.NoteDetailScreen
import com.example.notesapp.presentation.NoteScreen
import com.example.notesapp.presentation.NoteViewModel
import com.example.notesapp.ui.theme.NotesAppTheme

class MainActivity : ComponentActivity() {


    private val database by lazy {
        Room.databaseBuilder(
            applicationContext, //Application
            NoteDatabase::class.java,   //Database class name
            "notes.db"   //Database name
        ).build()
    }

    private val viewModel by viewModels<NoteViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NoteViewModel(database.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state by viewModel.state.collectAsState()
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "NoteScreen"){
                        composable("NoteScreen"){
                            NoteScreen(state = state, navController = navController, onEvent = viewModel::onEvent)
                        }
                        composable("AddNoteScreen"){
                            AddNoteScreen(state = state, navController = navController, onEvent = viewModel::onEvent)
                        }
                        composable("NoteDetailScreen"){
                            NoteDetailScreen(state = state, navController = navController, onEvent = viewModel::onEvent)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesAppTheme {
        Greeting("Android")
    }
}