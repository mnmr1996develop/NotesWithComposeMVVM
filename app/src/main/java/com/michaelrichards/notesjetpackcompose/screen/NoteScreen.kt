package com.michaelrichards.notesjetpackcompose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.michaelrichards.notesjetpackcompose.R
import com.michaelrichards.notesjetpackcompose.components.NoteButton
import com.michaelrichards.notesjetpackcompose.components.NoteInputText
import com.michaelrichards.notesjetpackcompose.model.Note

@Composable
fun NoteScreen(notes: List<Note> = listOf(),
               addNote: (Note)-> Unit,
                removeNote: (Note) -> Unit) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }


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
            NoteInputText(text = title, label = "Title",
                onValChange = {
                    if(it.all { char -> char.isLetter() || char.isWhitespace()}) title = it},
                modifier = Modifier.padding(9.dp))
            NoteInputText(text = description, label = "Add A Note",
                onValChange = { if(it.all { char -> char.isLetter() || char.isWhitespace()})description = it },
                modifier = Modifier.padding(9.dp))
            NoteButton(text = "Save", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()){
                    
                    

                    description = ""
                    title = ""
                }
            })
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = emptyList(), addNote = {}, removeNote = {})
}