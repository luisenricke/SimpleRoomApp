package com.luisenricke.simpleroomapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.luisenricke.simpleroomapp.data.AppDatabase
import com.luisenricke.simpleroomapp.data.contact.Contact
import com.luisenricke.simpleroomapp.data.contact.ContactDAO
import com.luisenricke.simpleroomapp.data.contact.ContactOperationsAsyncTask
import com.luisenricke.simpleroomapp.data.image.Image
import com.luisenricke.simpleroomapp.utils.ImageHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var list: List<Contact>
    private lateinit var manageDB: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manageDB = AppDatabase.openDB(this)

        if (manageDB.imageDAO().count() <= 0) {
            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.img_test)
            val byteArray = ImageHelper.BitmaptoByteArray(bitmap)
            val row = Image("Tes", byteArray, 1)
            manageDB.imageDAO().insert(row)
        }

        img_db.setImageBitmap(manageDB.imageDAO().filterId(1)
            .src?.let { ImageHelper.ByteArraytoBitmap(it) })

    }

    /**
     *  Create the database when doesn't exist
     *  or get instances if exist
     *  WARNING: Use when a special class is not required to handle the instances.
     */
    private fun instanceDB(database: String): AppDatabase {
        return Room.databaseBuilder(this, AppDatabase::class.java, database)
            //.allowMainThreadQueries() // Uncomment if threads available in MainThread
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.d("Database", "Room created")
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    Log.d("Database", "Room opened")
                }
            }).build()
    }

    /**
     *  Test functions from ContactOperationsAsyncTask::class
     */
    private fun testAsyncTask() {
        ContactOperationsAsyncTask.CreateContact(manageDB.contactDAO())
            .execute(Contact("activity@activity.com", "activity"))
        list = ContactOperationsAsyncTask.GetContacts(manageDB.contactDAO())
            .execute().get()
        Log.println(Log.DEBUG, "OperationsAsyncTask", list.toList().toString())
        ContactOperationsAsyncTask.UpdateContact(manageDB.contactDAO())
            .execute(Contact("activity@activity.com", "activity", 2))
        ContactOperationsAsyncTask.DeleteContact(manageDB.contactDAO())
            .execute(Contact("activity@activity.com", "activity", 2))
    }
}
