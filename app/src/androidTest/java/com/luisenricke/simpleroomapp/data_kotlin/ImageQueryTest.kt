package com.luisenricke.simpleroomapp.data_kotlin

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.luisenricke.simpleroomapp.data_kotlin.image.Image
import com.luisenricke.simpleroomapp.data_kotlin.image.ImageDAO
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.ByteArrayOutputStream
import java.io.IOException
import android.graphics.Bitmap.CompressFormat
import com.luisenricke.simpleroomapp.R


@RunWith(AndroidJUnit4::class)
class ImageQueryTest {
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

        image = dao.get(1)

        Assert.assertTrue(bitmap.sameAs(image.src?.toBitmap()))
    }

    private fun Bitmap.toByteArray(): ByteArray? {
        val blob = ByteArrayOutputStream()
        this.compress(CompressFormat.PNG, 0 /* Ignored for PNGs */, blob)
        return blob.toByteArray()
    }

    private fun ByteArray.toBitmap(): Bitmap? {
        return BitmapFactory.decodeByteArray(this, 0, this.size)
    }
}