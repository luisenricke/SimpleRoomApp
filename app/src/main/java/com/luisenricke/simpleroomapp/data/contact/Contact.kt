package com.luisenricke.simpleroomapp.data.contact

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luisenricke.simpleroomapp.data.Database

@Entity(tableName = Database.Contact.TABLE)
data class Contact(
    @ColumnInfo(name = Database.Contact.EMAIL)
    var email: String,
    @ColumnInfo(name = Database.Contact.NAME)
    var name: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Database.Contact.ID)
    val id: Long = 0
)