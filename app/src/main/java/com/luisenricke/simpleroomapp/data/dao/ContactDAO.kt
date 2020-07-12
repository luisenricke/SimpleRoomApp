package com.luisenricke.simpleroomapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.luisenricke.room.dao.Base
import com.luisenricke.room.dao.Delete
import com.luisenricke.room.dao.PrimaryKey
import com.luisenricke.room.dao.Update
import com.luisenricke.simpleroomapp.data.entity.Contact
import com.luisenricke.simpleroomapp.data.entity.Contact.SCHEMA

@Suppress("unused")
@Dao
abstract class ContactDAO : Base<Contact>, Update<Contact>, Delete<Contact>, PrimaryKey<Contact> {

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")
    abstract override fun count(): Long

    @Query("SELECT * FROM ${SCHEMA.TABLE}")
    abstract override fun get(): List<Contact>

    @Query("DELETE FROM ${SCHEMA.TABLE}")
    abstract override fun drop()

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun get(id: Long): Contact

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun get(ids: LongArray): List<Contact>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun delete(id: Long): Int

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun deletes(ids: LongArray): Int

    @Query("SELECT * FROM ${SCHEMA.TABLE} ORDER BY ${SCHEMA.ID} LIMIT 1")
    abstract fun last(): Contact?

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = (SELECT MAX(id) FROM ${SCHEMA.TABLE})")
    abstract fun deleteLast()
}
