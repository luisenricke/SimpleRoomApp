package com.luisenricke.simpleroomapp

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.luisenricke.simpleroomapp.data.AppDatabase
import com.luisenricke.simpleroomapp.data.dao.PetDAO
import com.luisenricke.simpleroomapp.data.dao.UserDAO
import com.luisenricke.simpleroomapp.data.entity.Pet
import com.luisenricke.simpleroomapp.data.entity.User
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date

@RunWith(AndroidJUnit4::class)
class ForeignKeyDAOTest {
    private lateinit var dao: PetDAO
    private lateinit var database: AppDatabase

    private val currentTime = System.currentTimeMillis()

    private val users = arrayListOf(
        User("test1@test", "test1", 1),
        User("test2@test", "test2", 2),
        User("test3@test", "test3", 3)
    )

    private val pets = arrayListOf(
        Pet("test1", Date(currentTime), 1, 1),
        Pet("test2", Date(currentTime), 1, 2),
        Pet("test3", Date(currentTime), null, 3),
        Pet("test4", Date(currentTime), 2, 4),
        Pet("test5", Date(currentTime), 2, 5),
        Pet("test6", Date(currentTime), null, 6)
    )

    @Before fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder<AppDatabase>(context, AppDatabase::class.java).build()
        dao = database.pet()

        database.user().inserts(users)
        dao.inserts(pets)
    }

    @After fun finish() {
        database.close()
    }

    @Test fun checkData() {
        val users = database.user().get()
        val pets = dao.get()
        Assert.assertEquals(3, users.size.toLong())
        Assert.assertEquals(6, pets.size.toLong())
    }

    @Test fun countByUserIfEmpty() {
        dao.drop()
        Assert.assertEquals(0, dao.countByUser())
    }

    @Test fun countByUser() {
        val count = dao.countByUser()
        Assert.assertEquals(4, count)
    }

    @Test fun countNotByUserIfEmpty() {
        dao.drop()
        Assert.assertEquals(0, dao.countNotByUser())
    }

    @Test fun countNotByUser() {
        val count = dao.countNotByUser()
        Assert.assertEquals(2, count)
    }

    @Test fun countByUserWithIdIfEmpty() {
        dao.drop()
        Assert.assertEquals(0, dao.countByUser(1))
    }

    @Test fun countByUserWithId() {
        val count = dao.countByUser(1)
        Assert.assertEquals(2, count)
    }

    @Test fun getByUserIfEmpty() {
        dao.drop()
        Assert.assertEquals(0, dao.getByUser().size)
    }

    @Test fun getByUser() {
        val row = dao.getByUser()
        val pets = arrayListOf(
            Pet("test1", Date(currentTime), 1, 1),
            Pet("test2", Date(currentTime), 1, 2),
            Pet("test4", Date(currentTime), 2, 4),
            Pet("test5", Date(currentTime), 2, 5)
        )
        Assert.assertEquals(pets, row)
    }

    @Test fun getNotByUserIfEmpty() {
        dao.drop()
        Assert.assertEquals(0, dao.getByUser().size)
    }

    @Test fun getNotByUser() {
        val row = dao.getNotByUser()
        val pets = arrayListOf(
            Pet("test3", Date(currentTime), null, 3),
            Pet("test6", Date(currentTime), null, 6)
        )
        Assert.assertEquals(pets, row)
    }

    @Test fun getByUserIfEmptyWithId() {
        dao.drop()
        Assert.assertEquals(0, dao.getByUser(1).size)
    }

    @Test fun getByUserWithId() {
        val row = dao.getByUser(1)
        val pets = arrayListOf(
            Pet("test1", Date(currentTime), 1, 1),
            Pet("test2", Date(currentTime), 1, 2)
        )
        Assert.assertEquals(pets, row)
    }

    @Test fun dropByUserIfEmpty() {
        dao.drop()
        dao.dropByUser(1)
        Assert.assertEquals(0, dao.get().size)
    }

    @Test fun dropByUser() {
        dao.dropByUser(1)

        val pets = arrayListOf(
            Pet("test3", Date(currentTime), null, 3),
            Pet("test4", Date(currentTime), 2, 4),
            Pet("test5", Date(currentTime), 2, 5),
            Pet("test6", Date(currentTime), null, 6)
        )
        val rows = dao.get()
        Assert.assertEquals(pets, rows)
    }

}
