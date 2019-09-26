package com.luisenricke.simpleroomapp.data.contact

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luisenricke.simpleroomapp.data.DatabaseScheme

@Entity(tableName = DatabaseScheme.Contact.TABLE)
data class Contact(
    @ColumnInfo(name = DatabaseScheme.Contact.EMAIL)
    var email: String,
    @ColumnInfo(name = DatabaseScheme.Contact.NAME)
    var name: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DatabaseScheme.Contact.ID)
    val id: Long = 0
)