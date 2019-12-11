package com.luisenricke.simpleroomapp.data.converter

import androidx.room.TypeConverter
import java.util.*

object DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}