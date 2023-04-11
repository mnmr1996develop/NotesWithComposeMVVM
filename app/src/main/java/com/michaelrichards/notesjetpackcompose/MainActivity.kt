package com.michaelrichards.notesjetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michaelrichards.notesjetpackcompose.data.NotesDataSource
import com.michaelrichards.notesjetpackcompose.model.Note
import com.michaelrichards.notesjetpackcompose.screen.NoteScreen
import com.michaelrichards.notesjetpackcompose.screen.NoteScreenPreview
import com.michaelrichards.notesjetpackcompose.screen.NoteViewModel
import com.michaelrichards.notesjetpackcompose.ui.theme.NotesJetpackComposeTheme

private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesJetpackComposeTheme {
                Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
                    val noteViewModel: NoteViewModel by viewModels()
                    NotesApp(noteViewModel = noteViewModel)

                }
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel = viewModel()) {

    val notesList = noteViewModel.getAllNote()

    NoteScreen(notes = notesList, removeNote = {noteViewModel.remove(note = it)}, addNote = {noteViewModel.addNote(it)})
}

