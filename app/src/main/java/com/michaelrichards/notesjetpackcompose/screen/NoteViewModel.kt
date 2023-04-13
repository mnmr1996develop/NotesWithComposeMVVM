package com.michaelrichards.notesjetpackcompose.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaelrichards.notesjetpackcompose.data.NotesDataSource
import com.michaelrichards.notesjetpackcompose.model.Note
import com.michaelrichards.notesjetpackcompose.repository.NoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "NoteViewModel"
@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepo) : ViewModel() {

   
    
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.getAllNotes().distinctUntilChanged().collect{
                if (it.isNullOrEmpty()){
                    Log.d(TAG, "List is Empty")
                }else{
                    _noteList.value = it
                }
            }
        }
    }



    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note = note) }

    fun remove(note: Note) = viewModelScope.launch { repository.deleteNote(note = note) }
     fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note = note) }


}