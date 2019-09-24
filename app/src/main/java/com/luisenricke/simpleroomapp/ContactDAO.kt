package com.luisenricke.simpleroomapp

import androidx.room.*

@Dao
interface ContactDAO {

    @Insert
    fun insertContact(contact: Contact): Long

    @Insert
    fun insertAllContacts(users: List<Contact>)

    @Update
    fun updateContact(contact: Contact): Unit

    @Delete
    fun deleteContact(contact: Contact): Unit

    @Query("SELECT * FROM contacs")
    fun getAllContacts(): List<Contact>

    @Query("SELECT * FROM contacs WHERE id_contact == :id")
    fun getContactById(id: Long): Contact

}