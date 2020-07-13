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
class UpdateDAOTest {
    private lateinit var dao: UserDAO
    private lateinit var database: AppDatabase

    @Before fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder<AppDatabase>(context, AppDatabase::class.java).build()
        dao = database.user()

        dao.inserts(
            User("test1@test", "test1"),
            User("test2@test", "test2"),
            User("test3@test", "test3")
        )
    }

    @After fun finish() {
        database.close()
    }

    @Test fun updateNonExistentObject() {
        val updated = dao.update(User("modified", "modified", 100))
        Assert.assertEquals(0, updated)
    }

    @Test fun update() {
        val modified = User("modified", "modified", 1)
        val updates = dao.update(modified)
        val after = dao.get(1)
        Assert.assertEquals(1, updates)
        Assert.assertEquals(modified, after)
    }

    @Test fun updatesList() {
        val rowOneModified = User("modified1", "modified1", 1)
        val rowTwoModified = User("modified2", "modified2", 2)
        val rowThreeNotModified = User("test3@test", "test3", 3)
        val rowFailUpdate = User("fail", "fail", 100)

        val updates = dao.updates(listOf(rowOneModified, rowTwoModified, rowFailUpdate))

        val expected = listOf(rowOneModified, rowTwoModified, rowThreeNotModified)
        val actuals = dao.get()

        Assert.assertEquals(2, updates)
        Assert.assertArrayEquals(expected.toTypedArray(), actuals.toTypedArray())
    }

    @Test fun updatesVararg() {
        val rowOneModified = User("modified1", "modified1", 1)
        val rowTwoNotModified = User("test2@test", "test2", 2)
        val rowThreeModified = User("modified3", "modified3", 3)
        val rowFailUpdate = User("fail", "fail", 100)

        val updates = dao.updates(rowOneModified, rowThreeModified, rowFailUpdate)

        val expected = listOf(rowOneModified, rowTwoNotModified, rowThreeModified)
        val actuals = dao.get()

        Assert.assertEquals(2, updates)
        Assert.assertArrayEquals(expected.toTypedArray(), actuals.toTypedArray())
    }

    @Test fun multipleUpdates() {
        var modified = 0
        for (loop in 1..1000) {
            if (dao.update(User("modified${loop}@", "modified${loop}", loop.toLong())) == 1) {
                modified += 1
            }
        }
        Assert.assertEquals(3, modified)
    }
}
