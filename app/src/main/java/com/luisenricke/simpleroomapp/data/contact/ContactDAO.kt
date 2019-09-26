package com.luisenricke.simpleroomapp

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
abstract class ContactDAO : BaseDAO<Contact>,
    BaseDAO.ComplementaryOperations<Contact>,
    BaseDAO.AdditionalOperationWithID<Contact> {

    @Query("SELECT count(*) FROM ${Database.Contact.TABLE}")
    abstract override fun count(): Int

    @Query("SELECT * FROM ${Database.Contact.TABLE} WHERE ${Database.Contact.ID} == :id")
    abstract override fun filterId(id: Long): Contact

    @Query("SELECT * FROM ${Database.Contact.TABLE}")
    abstract override fun get(): List<Contact>

    @Query("DELETE FROM ${Database.Contact.TABLE} WHERE ${Database.Contact.ID} == :id")
    abstract override fun delete(id: Long)

    @Query("DELETE FROM ${Database.Contact.TABLE}")
    abstract override fun drop()

    @Transaction
    open fun reset(rows: List<Contact>) {
        drop()
        inserts(rows)
    }
}