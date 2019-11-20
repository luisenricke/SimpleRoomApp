package com.luisenricke.simpleroomapp.data_kotlin.image

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luisenricke.simpleroomapp.data_kotlin.image.Image.SCHEMA.ID
import com.luisenricke.simpleroomapp.data_kotlin.image.Image.SCHEMA.NAME
import com.luisenricke.simpleroomapp.data_kotlin.image.Image.SCHEMA.SRC
import com.luisenricke.simpleroomapp.data_kotlin.image.Image.SCHEMA.TABLE

@Entity(tableName = TABLE)
data class Image(
    @ColumnInfo(name = NAME)
    var name: String,
    @ColumnInfo(name = SRC, typeAffinity = ColumnInfo.BLOB)
    var src: ByteArray? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long = 0
){
    object SCHEMA {
        const val TABLE = "Image"
        const val ID = "id"
        const val NAME = "name"
        const val SRC = "source"
    }
}