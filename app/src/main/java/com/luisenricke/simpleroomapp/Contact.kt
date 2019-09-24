package com.luisenricke.simpleroomapp

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacs")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_contact")
    @NonNull
    val id: Long /*= UUID.randomUUID() // Alternative */,
    @ColumnInfo(name = "email_contact")
    var email: String,
    @ColumnInfo(name = "name_contact")
    var name: String
)