package com.luisenricke.simpleroomapp.data_kotlin.contact

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.luisenricke.simpleroomapp.data_kotlin.BaseDAO
import com.luisenricke.simpleroomapp.data_kotlin.contact.Contact.SCHEMA

@Dao
abstract class ContactDAO : BaseDAO<Contact>,
    BaseDAO.UpdateDAO<Contact>,
    BaseDAO.DeleteDAO<Contact>,
    BaseDAO.OperationsPrimaryKeyDAO<Contact> {

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")
    abstract override fun count(): Int

    @Query("SELECT * FROM ${SCHEMA.TABLE}")
    abstract override fun get(): List<Contact>

    @Query("DELETE FROM ${SCHEMA.TABLE}")
    abstract override fun drop()

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun getById(id: Int): Contact

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun getByIds(ids: LongArray): List<Contact>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun deleteById(id: Int): Int

    @Query("SELECT * FROM ${SCHEMA.TABLE} ORDER BY ${SCHEMA.ID} LIMIT 1")
    abstract fun last(): Contact?

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = (SELECT MAX(id) FROM ${SCHEMA.TABLE})")
    abstract fun deleteLast()
}