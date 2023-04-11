package com.michaelrichards.notesjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.michaelrichards.notesjetpackcompose.data.NotesDataSource
import com.michaelrichards.notesjetpackcompose.model.Note
import com.michaelrichards.notesjetpackcompose.screen.NoteScreen
import com.michaelrichards.notesjetpackcompose.screen.NoteScreenPreview
import com.michaelrichards.notesjetpackcompose.ui.theme.NotesJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesJetpackComposeTheme {
                Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
                    val notes = remember {
                        mutableListOf<Note>()
                    }


                    NoteScreen(notes = notes, removeNote = {note ->  notes.remove(note)}, addNote = {notes.add(it)})
                }
            }
        }
    }
}

