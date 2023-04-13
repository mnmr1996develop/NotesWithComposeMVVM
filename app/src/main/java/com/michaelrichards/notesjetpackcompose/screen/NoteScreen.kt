package com.michaelrichards.notesjetpackcompose.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import com.michaelrichards.notesjetpackcompose.R
import com.michaelrichards.notesjetpackcompose.components.NoteButton
import com.michaelrichards.notesjetpackcompose.components.NoteInputText
import com.michaelrichards.notesjetpackcompose.model.Note
import java.time.format.DateTimeFormatter


private const val TAG = "NoteScreen"

@Composable
fun NoteScreen(
    notes: List<Note> = listOf(),
    addNote: (Note) -> Unit,
    removeNote: (Note) -> Unit
) {

    var title = remember {
        mutableStateOf("")
    }

    var description = remember {
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

        AddToNotes(title, description, addNote, context)

        Divider(modifier = Modifier.padding(10.dp))
        NotesListBody(notes, removeNote)
    }

}

@Composable
private fun AddToNotes(
    title: MutableState<String>,
    description: MutableState<String>,
    addNote: (Note) -> Unit,
    context: Context
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoteInputText(
            text = title.value, label = "Title",
            onValChange = {
                if (it.all { char -> char.isLetter() || char.isWhitespace() }) title.value = it
            },
            modifier = Modifier.padding(9.dp)
        )
        NoteInputText(
            text = description.value, label = "Add A Note",
            onValChange = {
                if (it.all { char -> char.isLetter() || char.isWhitespace() }) description.value = it
            },
            modifier = Modifier.padding(9.dp)
        )
        NoteButton(text = "Save", onClick = {
            if (title.value.isNotEmpty() && description.value.isNotEmpty()) {
                addNote(Note(title = title.value, description = description.value))
                Toast.makeText(context, "Note Added", Toast.LENGTH_LONG).show()
                description.value = ""
                title.value = ""
            }
        })
    }
}

@Composable
private fun NotesListBody(
    notes: List<Note>,
    removeNote: (Note) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(items = notes) { note ->
            NoteRow(note = note, onNoteClicked = { removeNote(note) })
        }
    }
}

@Composable
fun NoteRow(modifier: Modifier = Modifier, note: Note, onNoteClicked: (Note) -> Unit) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth()
            .padding(5.dp),
        color = Color(0xFFFA8181),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .clickable { onNoteClicked(note) }
                .padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.Start
        ) {
                Text(text = note.title, style = MaterialTheme.typography.h5)
                Text(text = note.description, style = MaterialTheme.typography.subtitle1)
               // Text(text = note.entryDateTimeStamp.format(DateTimeFormatter.ofPattern("EEE MMMM dd, yyyy")), style = MaterialTheme.typography.caption)
        }
    }
}