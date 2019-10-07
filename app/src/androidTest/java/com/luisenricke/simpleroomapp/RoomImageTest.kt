package com.luisenricke.simpleroomapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.luisenricke.simpleroomapp.data.AppDatabase
import com.luisenricke.simpleroomapp.data.contact.Contact
import com.luisenricke.simpleroomapp.data.contact.ContactDAO
import com.luisenricke.simpleroomapp.data.image.Image
import com.luisenricke.simpleroomapp.data.image.ImageDAO
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.internal.Classes.getClass
import org.junit.runner.RunWith
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import android.graphics.Bitmap.CompressFormat


@RunWith(AndroidJUnit4::class)
class RoomImageTest {
    private lateinit var instrumentationContext: Context
    private lateinit var dao: ImageDAO
    private lateinit var db: AppDatabase

    private lateinit var targetContext: Context

    @Before
    fun setContext() {
        targetContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Before
    fun createDb() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(instrumentationContext, AppDatabase::class.java)
            .build()
        dao = db.imageDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun checkImage() {
        val bitmap = BitmapFactory.decodeResource(targetContext.resources, R.mipmap.img_test)
        Assert.assertNotNull(bitmap)
    }


    @Test
    @Throws(Exception::class)
    fun insertSimpleRow() {
        val bitmap = BitmapFactory.decodeResource(targetContext.resources, R.mipmap.img_test)
        val byteArray = bitmap.toByteArray()

        val row = Image("Tes", byteArray, 1)
        val idGenerated = dao.insert(row)

        Assert.assertEquals(1, idGenerated)
    }

    @Test
    @Throws(Exception::class)
    fun checkIfSameImage() {
        val bitmap = BitmapFactory.decodeResource(targetContext.resources, R.mipmap.img_test)
        val byteArray = bitmap.toByteArray()
        var image: Image?

        val row = Image("Tes", byteArray, 1)
        dao.insert(row)

        image = dao.filterId(1)

        Assert.assertTrue(bitmap.sameAs(image.src?.toBitmap()))
    }

/*
    @Test
    @Throws(Exception::class)
    fun getListOfRows() {
        val rowOne = Contact("test@test.com,", "test", 1)
        val rowTwo = Contact("test@test.com,", "test", 2)
        val rowThree =
            Contact("test@test.com,", "test", 3)
        val list = listOf(rowOne, rowTwo, rowThree)
        dao.inserts(list)
        val checkList = dao.get()

        Assert.assertArrayEquals(list.toTypedArray(), checkList.toTypedArray())
    }
     */

    private fun Bitmap.toByteArray(): ByteArray? {
        val blob = ByteArrayOutputStream()
        this.compress(CompressFormat.PNG, 0 /* Ignored for PNGs */, blob)
        return blob.toByteArray()
    }

    private fun ByteArray.toBitmap(): Bitmap? {
        return BitmapFactory.decodeByteArray(this, 0, this.size)
    }
}