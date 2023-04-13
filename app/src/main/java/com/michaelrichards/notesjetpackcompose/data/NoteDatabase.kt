package com.michaelrichards.notesjetpackcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.michaelrichards.notesjetpackcompose.model.Note
import com.michaelrichards.notesjetpackcompose.util.DateConverter
import com.michaelrichards.notesjetpackcompose.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDatabaseDao
}