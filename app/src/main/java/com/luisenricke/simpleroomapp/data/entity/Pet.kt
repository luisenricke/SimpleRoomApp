package com.luisenricke.simpleroomapp.data.entity

import androidx.room.*
import java.util.*

@Suppress("SpellCheckingInspection")
@Entity(
    tableName = Pet.SCHEMA.TABLE,
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf(User.SCHEMA.ID),
            childColumns = arrayOf(Pet.SCHEMA.USER),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Pet(
    @ColumnInfo(name = SCHEMA.NAME)
    val name: String,
    @ColumnInfo(name = SCHEMA.BORN_DAY)
    val bornDay: Date,
    @ColumnInfo(name = SCHEMA.USER, index = true)
    val userId: Int,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SCHEMA.ID)
    val id: Long = 0
) {
    object SCHEMA {
        const val TABLE = "Pet"
        const val ID = "id"
        const val NAME = "name"
        const val BORN_DAY = "born"
        const val USER = "user_id"
    }
}
