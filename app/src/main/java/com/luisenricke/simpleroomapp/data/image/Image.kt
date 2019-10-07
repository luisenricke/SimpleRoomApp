package com.luisenricke.simpleroomapp.data.image

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luisenricke.simpleroomapp.data.DatabaseScheme

@Entity(tableName = DatabaseScheme.Image.TABLE)
data class Image(
    @ColumnInfo(name = DatabaseScheme.Image.NAME)
    var name: String,
    @ColumnInfo(name = DatabaseScheme.Image.SRC, typeAffinity = ColumnInfo.BLOB)
    var src: ByteArray? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DatabaseScheme.Image.ID)
    val id: Long = 0
)