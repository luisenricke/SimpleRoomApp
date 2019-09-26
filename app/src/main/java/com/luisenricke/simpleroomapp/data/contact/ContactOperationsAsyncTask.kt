package com.luisenricke.simpleroomapp.data.contact

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.luisenricke.simpleroomapp.data.AppDatabase

class ContactOperationsAsyncTask : AppCompatActivity() {

    /**
     *  Setting AsyncTask class : <PARAMS, PROGRESS, RESULT>
     */
    class GetContacts(val dao: ContactDAO) : AsyncTask<Unit, Unit, List<Contact>>() {
        override fun doInBackground(vararg params: Unit?): List<Contact> {
            return dao.get()
        }

        override fun onPostExecute(result: List<Contact>?) {
            super.onPostExecute(result)
            // Make something
            Log.println(Log.DEBUG, "OperationsAsyncTask", result?.toList().toString())
        }
    }

    class CreateContact(val dao: ContactDAO) : AsyncTask<Contact, Unit, Long>() {
        override fun doInBackground(vararg params: Contact?): Long {
            return dao.insert(params[0]!!)
        }

        override fun onPostExecute(result: Long?) {
            super.onPostExecute(result)
            // Make something
            Log.println(Log.DEBUG, "OperationsAsyncTask", result?.toString())
        }
    }

    class UpdateContact(val dao: ContactDAO) : AsyncTask<Contact, Unit, Boolean>() {
        override fun doInBackground(vararg params: Contact?): Boolean? {
            dao.update(params[0]!!)

            return !params[0]!!.name.equals(dao.filterId(params[0]!!.id))
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)
            // Make something
            Log.println(Log.DEBUG, "OperationsAsyncTask", "Updated: " + result?.toString())
        }
    }

    class DeleteContact(val dao: ContactDAO) : AsyncTask<Contact, Unit, Boolean>() {
        override fun doInBackground(vararg params: Contact?): Boolean? {
            val countAfter = dao.count()
            dao.delete(params[0]!!)
            val countBefore = dao.count()
            return countAfter != countBefore
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)
            // Make something
            Log.println(Log.DEBUG, "OperationsAsyncTask", "Deleted: " + result?.toString())
        }
    }
}