package com.luisenricke.simpleroomapp.data

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.luisenricke.simpleroomapp.data.dao.PetDAO
import com.luisenricke.simpleroomapp.data.dao.UserDAO
import com.luisenricke.simpleroomapp.data.entity.Pet
import com.luisenricke.simpleroomapp.data.entity.User
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class PetQueryTest {
    private lateinit var user: UserDAO
    private lateinit var pet: PetDAO
    private lateinit var database: AppDatabase

    @Before fun setup() {
        val instrumentation: Context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder<AppDatabase>(instrumentation, AppDatabase::class.java).build()
        user = database.user()
        pet = database.pet()

        val userOne = User("test@test", "test")
        val userTwo = User("test2@test", "test2")
        val userThree = User("test3@test", "test3")
        user.inserts(userOne, userTwo, userThree)

        val timestamp = System.currentTimeMillis()
        pet.inserts(
            Pet("test1", Date(timestamp), 1),
            Pet("test2", Date(timestamp), 1),
            Pet("test3", Date(timestamp), 1),
            Pet("test4", Date(timestamp), 2),
            Pet("test5", Date(timestamp), 2),
            Pet("test6", Date(timestamp), 3)
        )
    }

    @After fun finish() {
        database.close()
    }

    @Test fun checkData() {
        val users = user.get()
        val pets = pet.get()
        Assert.assertEquals(3, users.size.toLong())
        Assert.assertEquals(6, pets.size.toLong())
    }

    @Test fun countByUser() {
        val count = pet.countByUser(3)
        Assert.assertEquals(1, count)
    }

    @Test fun getByUser() {
        val list = pet.getByUser(1)
        Assert.assertEquals(3, list.size)
    }

    @Test fun countJoinByUser() {
        val count = pet.countJoinByUser()
        Assert.assertEquals(6, count)
    }

    @Test fun countJoinByUserId() {
        val count = pet.countJoinByUser(1)
        Assert.assertEquals(3, count)
    }

    @Test fun getJoinByUser() {
        val list = pet.getJoinByUser()
        Assert.assertEquals(6, list.size)
    }

    @Test fun getJoinByUserId() {
        val list = pet.getJoinByUser(2)
        Assert.assertEquals(2, list.size)
    }

    @Test fun dropByUser() {
        pet.dropByUser(1)
        val list = pet.get()
        Assert.assertEquals(3, list.size)
    }

    //
    @Test fun countByUserEmpty() {
        pet.drop()
        val count = pet.countByUser(1)
        Assert.assertEquals(0, count)
    }

    @Test fun countByUserNotFound() {
        val count = pet.countByUser(999)
        Assert.assertEquals(0, count)
    }

    @Test fun getByUserEmpty() {
        pet.drop()
        val list = pet.getByUser(1)
        Assert.assertEquals(0, list.size)
    }

    @Test fun getByUserNotFound() {
        val list = pet.getByUser(999)
        Assert.assertEquals(0, list.size)
    }

    @Test fun countJoinByUserEmpty() {
        pet.drop()
        val count = pet.countJoinByUser()
        Assert.assertEquals(0, count)
    }

    @Test fun countJoinByUserIdEmpty() {
        pet.drop()
        val count = pet.countJoinByUser(1)
        Assert.assertEquals(0, count)
    }

    @Test fun countJointByUserIdNotFound() {
        val count = pet.countByUser(999)
        Assert.assertEquals(0, count)
    }

    @Test fun getJoinByUserEmpty() {
        pet.drop()
        val list = pet.getJoinByUser()
        Assert.assertEquals(0, list.size)
    }

    @Test fun getJoinByUserEmptyId() {
        pet.drop()
        val list = pet.getJoinByUser(2)
        Assert.assertEquals(0, list.size)
    }

    @Test fun getJoinByUserIdNotFound() {
        val list = pet.getByUser(999)
        Assert.assertEquals(0, list.size)
    }

    @Test fun dropByUserEmpty() {
        pet.drop()
        pet.dropByUser(1)
        val count = pet.count()
        Assert.assertEquals(0, count)
    }
}