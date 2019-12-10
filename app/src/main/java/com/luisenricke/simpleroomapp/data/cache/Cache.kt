package com.luisenricke.simpleroomapp.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luisenricke.simpleroomapp.data.cache.Cache.SCHEMA.ID
import com.luisenricke.simpleroomapp.data.cache.Cache.SCHEMA.SRC
import com.luisenricke.simpleroomapp.data.cache.Cache.SCHEMA.TABLE

@Entity(tableName = TABLE)
data class Cache(
    @ColumnInfo(name = SRC, typeAffinity = ColumnInfo.BLOB)
    var src: ByteArray? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long = 0
){
    object SCHEMA {
        const val TABLE = "Cache"
        const val ID = "id"
        const val SRC = "source"
    }
}