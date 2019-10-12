package com.luisenricke.simpleroomapp.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luisenricke.simpleroomapp.data.DatabaseScheme

@Entity(tableName = DatabaseScheme.Cache.TABLE)
data class Cache(
    @ColumnInfo(name = DatabaseScheme.Cache.SRC, typeAffinity = ColumnInfo.BLOB)
    var src: ByteArray? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DatabaseScheme.Cache.ID)
    val id: Long = 0
)