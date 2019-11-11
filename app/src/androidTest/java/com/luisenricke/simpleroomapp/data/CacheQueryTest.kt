package com.luisenricke.simpleroomapp.data

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.luisenricke.simpleroomapp.data.cache.Cache
import com.luisenricke.simpleroomapp.data.cache.CacheDAO
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CacheQueryTest {
    private lateinit var instrumentationContext: Context
    private lateinit var dao: CacheDAO
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(instrumentationContext, AppDatabase::class.java)
            .build()
        dao = db.cacheDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun getEmptyRow() {
        val row = dao.get()
        Assert.assertEquals(null, row)
    }

    @Test
    fun push() {
        val row = dao.push(Cache(ByteArray(10)))
        val count = dao.count()
        Assert.assertEquals(1, count)
    }

    @Test
    fun pop(){
        val row = dao.push(Cache(ByteArray(10)))
        val poped = dao.pop()
        val empty = dao.count()
        Assert.assertEquals(0, empty)
    }
}