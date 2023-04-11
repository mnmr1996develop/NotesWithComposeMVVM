package com.michaelrichards.notesjetpackcompose.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onValChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyBoardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onValChange,
        modifier = modifier,
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyBoardController?.hide()
        }),
        label = { Text(text = label) },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    )
}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Button(onClick = onClick, shape = CircleShape, enabled = enabled, modifier = modifier) {
        Text(text = text)
    }
}