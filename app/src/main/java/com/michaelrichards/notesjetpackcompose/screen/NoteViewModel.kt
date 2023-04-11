package com.michaelrichards.notesjetpackcompose.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.michaelrichards.notesjetpackcompose.data.NotesDataSource
import com.michaelrichards.notesjetpackcompose.model.Note

private const val TAG = "NoteViewModel"

class NoteViewModel: ViewModel() {

   
    
    private var noteList = mutableListOf<Note>()

    init {
        noteList.addAll(NotesDataSource.loadNotes())
    }



    fun addNote(note: Note){
        noteList.add(note)
    }

    fun remove(note: Note){
        noteList.remove(note)
    }

    fun getAllNote(): List<Note>{
        return noteList
    }

}