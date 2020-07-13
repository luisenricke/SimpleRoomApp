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
class DeleteDAOTest {
    private lateinit var dao: UserDAO
    private lateinit var database: AppDatabase

    @Before fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder<AppDatabase>(context, AppDatabase::class.java).build()
        dao = database.user()
    }

    @After fun finish() {
        database.close()
    }

    @Test fun deleteNonExistentObject() {
        val deleted = dao.delete(User("test@test", "test", 1))
        Assert.assertEquals(0, deleted)
        Assert.assertEquals(0, dao.count())
    }

    @Test fun delete() {
        val rowOne = User("test@test", "test")
        dao.insert(rowOne)
        val deleted = dao.delete(User("test@test", "test", 1))
        Assert.assertEquals(1, deleted)
        Assert.assertEquals(0, dao.count())
    }

    @Test fun deletedList() {
        val insert = listOf(
            User("test@test", "test"),
            User("test2@test", "test2"),
            User("test3@test", "test3")
        )
        dao.inserts(insert)
        val delete: List<User> = listOf(
            User("test@test", "test", 1),
            User("test2@test", "test2", 2),
            User("test3@test", "test3", 3)
        )
        val deleted = dao.deletes(delete)
        Assert.assertEquals(3, deleted)
        Assert.assertEquals(0, dao.count())
    }

    @Test fun deletedVararg() {
        val insertOne = User("test@test", "test")
        val insertTwo = User("test2@test", "test2")
        val insertThree = User("test3@test", "test3")
        dao.inserts(insertOne, insertTwo, insertThree)
        val deleteOne = User("test@test", "test", 1)
        val deleteTwo = User("test2@test", "test2", 2)
        val deleteThree = User("test3@test", "test3", 3)
        val deleted = dao.deletes(deleteOne, deleteTwo, deleteThree)
        Assert.assertEquals(3, deleted)
        Assert.assertEquals(0, dao.count())
    }

    @Test fun multipleDeletes() {
        val insert = listOf(
            User("test1@test", "test1"),
            User("test2@test", "test2"),
            User("test3@test", "test3")
        )
        dao.inserts(insert)
        var deleted = 0
        for (loop in 1..1000) {
            if (dao.delete(User("test${loop}@test", "test${loop}", loop.toLong())) == 1) {
                deleted += 1
            }
        }
        Assert.assertEquals(3, deleted)
        Assert.assertEquals(0, dao.count())
    }
}
