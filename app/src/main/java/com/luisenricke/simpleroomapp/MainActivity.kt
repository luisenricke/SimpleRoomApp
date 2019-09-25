package com.luisenricke.simpleroomapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    //private lateinit var appDatabase: AppDatabase

    companion object {
        private var contacts = arrayListOf<Contact>()
        private lateinit var manageDB: ContactDAO
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Witjin Intance - First run
    /*
        var appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "CONTACS_DB")
            .allowMainThreadQueries().addCallback(object : RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Insert rows
                            Log.d("Database","Created")
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    Log.d("Database", "opened")
                }
            }).build()
    */
        AppDatabase.openDB(this)
        CreateContactAsyncTask().execute(Contact(3, "Check", "Check"))
        GetAllContactsAsyncTask().execute()

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

        contacts[id.toInt()] = updateContact
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

    /* PARAMS, PROGRESS, RESULT*/
    private class GetAllContactsAsyncTask : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg params: Unit?): Unit {
            contacts.addAll(manageDB.getAllContacts())
            return
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            //contactAdapter.notifySetDataChanged()
        }
    }

    private class CreateContactAsyncTask : AsyncTask<Contact, Unit, Unit>() {
        override fun doInBackground(vararg params: Contact?): Unit {

            manageDB.insertContact(params[0]!!)
            var contact = manageDB.getContactById(params.get(0)?.id!!)

            if (contact != null) {
                contacts.add(contact)
            }

            return
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            //contactAdapter.notifySetDataChanged()
        }
    }

    private class UpdateContactAsyncTask : AsyncTask<Contact, Unit, Unit>() {
        override fun doInBackground(vararg params: Contact?): Unit {

            manageDB.updateContact(params[0]!!)

            return
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            //contactAdapter.notifySetDataChanged()
        }
    }

    private class DeleteContactAsyncTask : AsyncTask<Contact, Unit, Unit>() {
        override fun doInBackground(vararg params: Contact?): Unit {

            manageDB.deleteContact(params[0]!!)

            return
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            //contactAdapter.notifySetDataChanged()
        }
    }
}
