package com.luisenricke.simpleroomapp.database.converter;

import android.annotation.SuppressLint;

import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @SuppressLint("SimpleDateFormat")
    public static String toString(Date date){
        String pattern = "dd/MM/yyyy HH:mm:ss";
        return new SimpleDateFormat(pattern).format(date);
    }
}
