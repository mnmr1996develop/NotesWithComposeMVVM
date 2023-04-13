package com.michaelrichards.notesjetpackcompose.util

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun timestampFromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun dateFromTimestamp(timeStamp: Long): Date? {
        return Date(timeStamp)
    }
}