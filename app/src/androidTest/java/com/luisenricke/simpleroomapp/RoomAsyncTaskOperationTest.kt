package com.luisenricke.simpleroomapp

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.luisenricke.simpleroomapp.data.AppDatabase
import com.luisenricke.simpleroomapp.data.contact.Contact
import com.luisenricke.simpleroomapp.data.contact.ContactDAO
import com.luisenricke.simpleroomapp.data.contact.ContactOperationsAsyncTask
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoomAsyncTaskOperationTest {
    private lateinit var instrumentationContext: Context
    private lateinit var dao: ContactDAO
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(instrumentationContext, AppDatabase::class.java)
            .build()
        dao = db.contactDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertRow() {
        var list: ArrayList<Contact>?

        ContactOperationsAsyncTask.CreateContact(db.contactDAO())
            .execute(Contact("activity@activity.com", "activity"))
        list =
            ContactOperationsAsyncTask.GetContacts(db.contactDAO()).execute().get() as ArrayList<Contact>
        Assert.assertEquals(1, list.size)
    }

    @Test
    fun updateRow() {
        var list: ArrayList<Contact>?

        ContactOperationsAsyncTask.CreateContact(db.contactDAO())
            .execute(Contact("activity@activity.com", "activity"))
        list =
            ContactOperationsAsyncTask.GetContacts(db.contactDAO()).execute().get() as ArrayList<Contact>

        ContactOperationsAsyncTask.UpdateContact(db.contactDAO())
            .execute(Contact("activity@activity.com", "activity", 1))

        Assert.assertEquals("activity", list[0].name)
    }

    @Test
    fun deleteRow() {
        var list: ArrayList<Contact>?

        ContactOperationsAsyncTask.CreateContact(db.contactDAO())
            .execute(Contact("activity@activity.com", "activity"))
        ContactOperationsAsyncTask.DeleteContact(db.contactDAO())
            .execute(Contact("activity@activity.com", "activity", 1))
        list =
            ContactOperationsAsyncTask.GetContacts(db.contactDAO()).execute().get() as ArrayList<Contact>

        Assert.assertEquals(0, list.size)
    }
}