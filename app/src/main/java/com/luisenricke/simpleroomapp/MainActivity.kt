package com.luisenricke.simpleroomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    //private lateinit var appDatabase: AppDatabase

    private var contacts = arrayListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Witjin Intance - First run
        /*
        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "CONTACS_DB")
            .allowMainThreadQueries().build()
        */

        getAllContacts()
        Log.e("Funciono", contacts.toList().toString())
    }

    // TODO: Check later
    private fun insertContact(email: String, name: String) {
        var id =
            AppDatabase.getInstance(this).contactDAO()
                .insertContact(Contact(Math.random().toLong(), email, name))
        var contact = AppDatabase.getInstance(this).contactDAO().getContactById(id)

        if (contact != null) {
            contacts.add(contact)
        }

    }

    //TODO: Check later
    private fun updateContact(id: Long, email: String, name: String) {
        var updateContact = AppDatabase.getInstance(this).contactDAO().getContactById(id)
        updateContact.name = name
        updateContact.email = email

        contacts.set(id.toInt(), updateContact)
        //contactAdapter.notifyDataSetChanged()
    }

    //TODO: Chech later
    private fun deleteContact(id: Long, contactDelete: Contact) {
        AppDatabase.getInstance(this).contactDAO().deleteContact(contactDelete)
        contacts.removeAt(id.toInt())
        //contactAdapter.notifyDataSetChanged
    }

    private fun getAllContacts() {
        contacts.addAll(AppDatabase.getInstance(this).contactDAO().getAllContacts())
    }

}
