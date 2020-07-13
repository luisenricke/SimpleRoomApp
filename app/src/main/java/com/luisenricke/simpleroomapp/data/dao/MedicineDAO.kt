package com.luisenricke.simpleroomapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.luisenricke.room.dao.Base
import com.luisenricke.room.dao.Delete
import com.luisenricke.room.dao.PrimaryKey
import com.luisenricke.room.dao.Update
import com.luisenricke.simpleroomapp.data.entity.Medicine
import com.luisenricke.simpleroomapp.data.entity.Medicine.SCHEMA

@Suppress("unused")
@Dao
abstract class MedicineDAO : Base<Medicine>, Update<Medicine>, Delete<Medicine>, PrimaryKey<Medicine> {

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")
    abstract override fun count(): Long

    @Query("SELECT * FROM ${SCHEMA.TABLE}")
    abstract override fun get(): List<Medicine>

    @Query("DELETE FROM ${SCHEMA.TABLE}")
    abstract override fun drop()

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun get(id: Long): Medicine?

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun get(ids: LongArray): List<Medicine>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun delete(id: Long): Int

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun deletes(ids: LongArray): Int
}
