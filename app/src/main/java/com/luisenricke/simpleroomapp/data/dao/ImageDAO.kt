package com.luisenricke.simpleroomapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.luisenricke.room.dao.Base
import com.luisenricke.room.dao.Delete
import com.luisenricke.room.dao.PrimaryKey
import com.luisenricke.room.dao.Update
import com.luisenricke.simpleroomapp.data.entity.Image
import com.luisenricke.simpleroomapp.data.entity.Image.SCHEMA

@Suppress("unused")
@Dao
abstract class ImageDAO : Base<Image>, Update<Image>, Delete<Image>, PrimaryKey<Image> {

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")
    abstract override fun count(): Long

    @Query("SELECT * FROM ${SCHEMA.TABLE}")
    abstract override fun get(): List<Image>

    @Query("DELETE FROM ${SCHEMA.TABLE}")
    abstract override fun drop()

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun get(id: Long): Image?

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun get(ids: LongArray): List<Image>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun delete(id: Long): Int

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun deletes(ids: LongArray): Int

    @Transaction
    open fun reset(rows: List<Image>) {
        drop()
        inserts(rows)
    }
}
