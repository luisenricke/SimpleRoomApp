package com.luisenricke.simpleroomapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luisenricke.simpleroomapp.data.entity.Contact.SCHEMA.EMAIL
import com.luisenricke.simpleroomapp.data.entity.Contact.SCHEMA.ID
import com.luisenricke.simpleroomapp.data.entity.Contact.SCHEMA.NAME
import com.luisenricke.simpleroomapp.data.entity.Contact.SCHEMA.TABLE

@Entity(tableName = TABLE)
data class Contact(
    @ColumnInfo(name = EMAIL)
    var email: String,
    @ColumnInfo(name = NAME)
    var name: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long = 0
) {
    object SCHEMA {
        const val TABLE = "Contact"
        const val ID = "id"
        const val EMAIL = "email"
        const val NAME = "name"
    }
}
