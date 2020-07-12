package com.luisenricke.simpleroomapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = User.SCHEMA.TABLE)
data class User(
    @ColumnInfo(name = SCHEMA.EMAIL)
    var email: String,
    @ColumnInfo(name = SCHEMA.PASSWORD)
    var password: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SCHEMA.ID)
    val id: Long = 0
) {
    object SCHEMA {
        const val TABLE = "User"
        const val ID = "id"
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        val cast = other as User
        return cast.id == id
                && cast.email == email
                && cast.password == password
    }
}

