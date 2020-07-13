package com.luisenricke.simpleroomapp

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.luisenricke.simpleroomapp.data.AppDatabase
import com.luisenricke.simpleroomapp.data.dao.UserDAO
import com.luisenricke.simpleroomapp.data.entity.User
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PrimaryKeyDAOTest {
    private lateinit var dao: UserDAO
    private lateinit var database: AppDatabase

    private val list = arrayListOf(
        User("test1@test", "test1", 1),
        User("test2@test", "test2", 2),
        User("test3@test", "test3", 3)
    )

    @Before fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder<AppDatabase>(context, AppDatabase::class.java).build()
        dao = database.user()

        dao.inserts(list)
    }

    @After fun finish() {
        database.close()
    }

    @Test fun getNonExistentObject() {
        val row = dao.get(100)
        Assert.assertNull(row)
    }

    @Test fun get() {
        val row = dao.get(1)
        Assert.assertEquals(list[0], row)
    }

    @Test fun getListNonExistentObject() {
        val rows = dao.get(longArrayOf(100, 200, 300))
        Assert.assertEquals(0, rows.size)
    }

    @Test fun getList() {
        val rows = dao.get(longArrayOf(1, 2, 3))
        Assert.assertEquals(3, rows.size)
    }

    @Test fun deleteNonExistentObject() {
        val deleted = dao.delete(100)
        Assert.assertEquals(0, deleted)
    }

    @Test fun delete() {
        val deleted = dao.delete(1)
        Assert.assertEquals(1, deleted)
        Assert.assertEquals(2, dao.count())
    }

    @Test fun deleteListNonExistentObject() {
        val deleted = dao.deletes(longArrayOf(100,200,300))
        Assert.assertEquals(0, deleted)
    }

    @Test fun deleteList() {
        val deleted = dao.deletes(longArrayOf(1,2,3))
        Assert.assertEquals(3, deleted)
        Assert.assertEquals(0, dao.count())
    }
}
