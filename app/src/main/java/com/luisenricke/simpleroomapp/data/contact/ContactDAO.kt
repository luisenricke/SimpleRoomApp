package com.luisenricke.simpleroomapp.data.contact

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.luisenricke.simpleroomapp.data.BaseDAO
import com.luisenricke.simpleroomapp.data.DatabaseScheme

@Dao
abstract class ContactDAO : BaseDAO<Contact>,
    BaseDAO.ComplementaryOperationsWithList<Contact>,
    BaseDAO.AdditionalOperationWithID<Contact> {

    @Query("SELECT count(*) FROM ${DatabaseScheme.Contact.TABLE}")
    abstract override fun count(): Int

    @Query("SELECT * FROM ${DatabaseScheme.Contact.TABLE}")
    abstract override fun get(): List<Contact>

    @Query("DELETE FROM ${DatabaseScheme.Contact.TABLE}")
    abstract override fun drop()

    @Query("SELECT * FROM ${DatabaseScheme.Contact.TABLE} WHERE ${DatabaseScheme.Contact.ID} == :id")
    abstract override fun filterId(id: Long): Contact

    @Query("DELETE FROM ${DatabaseScheme.Contact.TABLE} WHERE ${DatabaseScheme.Contact.ID} == :id")
    abstract override fun delete(id: Long)

    //Specific queries for the table
    @Transaction
    open fun reset(rows: List<Contact>) {
        drop()
        inserts(rows)
    }

    @Query("SELECT ${DatabaseScheme.Contact.NAME} FROM ${DatabaseScheme.Contact.TABLE}")
    abstract fun getCustomColumns(): List<ContactMinimal>
}