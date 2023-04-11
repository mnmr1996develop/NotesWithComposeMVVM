package com.michaelrichards.notesjetpackcompose.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.michaelrichards.notesjetpackcompose.R
import com.michaelrichards.notesjetpackcompose.components.NoteButton
import com.michaelrichards.notesjetpackcompose.components.NoteInputText
import com.michaelrichards.notesjetpackcompose.data.NotesDataSource
import com.michaelrichards.notesjetpackcompose.model.Note
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    notes: List<Note> = listOf(),
    addNote: (Note) -> Unit,
    removeNote: (Note) -> Unit
) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current


    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }, actions = {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Icon"
            )
        }, backgroundColor = Color(0xFFF89292))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                text = title, label = "Title",
                onValChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) title = it
                },
                modifier = Modifier.padding(9.dp)
            )
            NoteInputText(
                text = description, label = "Add A Note",
                onValChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) description = it
                },
                modifier = Modifier.padding(9.dp)
            )
            NoteButton(text = "Save", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    addNote(Note(title = title, description = description))
                    Toast.makeText(context, "Note Added", Toast.LENGTH_LONG).show()
                    description = ""
                    title = ""
                }
            })
        }

        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes) { note ->
                    NoteRow(note = note, onNoteClicked = {removeNote(note)})
            }
        }
    }

}

@Composable
fun NoteRow(modifier: Modifier = Modifier, note: Note, onNoteClicked: (Note) -> Unit) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFFA8181),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .clickable {onNoteClicked(note) }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
                Text(text = note.title, style = MaterialTheme.typography.subtitle2)
                Text(text = note.description, style = MaterialTheme.typography.subtitle1)
                Text(text = note.entryDateTimeStamp.format(DateTimeFormatter.ofPattern("EEE, d MMM")), style = MaterialTheme.typography.caption)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NotesDataSource.loadNotes(), addNote = {}, removeNote = {})
}