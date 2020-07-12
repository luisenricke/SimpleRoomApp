package com.luisenricke.simpleroomapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Medicine.SCHEMA.TABLE)
data class Medicine(
    @ColumnInfo(name = SCHEMA.NAME)
    val name: String,
    @ColumnInfo(name = SCHEMA.APPLICATION)
    val application: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SCHEMA.ID)
    val id: Long = 0
){
    object SCHEMA {
        const val TABLE = "Medicine"
        const val ID = "id"
        const val NAME = "name"
        const val APPLICATION = "application"
    }
}
