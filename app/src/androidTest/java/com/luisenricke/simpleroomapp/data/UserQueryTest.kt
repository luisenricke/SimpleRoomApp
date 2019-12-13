package com.luisenricke.simpleroomapp.data

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.luisenricke.simpleroomapp.data.dao.UserDAO
import com.luisenricke.simpleroomapp.data.entity.User
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserQueryTest {
    private lateinit var dao: UserDAO
    private lateinit var database: AppDatabase

    @Before fun setup() {
        val instrumentation: Context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder<AppDatabase>(instrumentation, AppDatabase::class.java).build()
        dao = database.user()
    }

    @After fun finish() {
        database.close()
    }

    @Test fun get() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        dao.inserts(rowOne, rowTwo, rowThree)
        val list = dao.get()
        Assert.assertEquals(3, list.size)
    }

    @Test fun drop() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        dao.inserts(rowOne, rowTwo, rowThree)
        dao.drop()
        val count = dao.count()
        Assert.assertEquals(0, count)
    }

    @Test fun getByIds() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        dao.inserts(rowOne, rowTwo, rowThree)
        val list = dao.get(longArrayOf(1, 2, 3))
        val check = dao.get()
        Assert.assertArrayEquals(check.toTypedArray(), list.toTypedArray())
    }

    @Test fun deleteById() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        dao.inserts(rowOne, rowTwo, rowThree)
        val deletes = dao.delete(2)
        val count = dao.count()
        println("deleted: $deletes")
        Assert.assertEquals(2, count)
    }

    @Test fun deletesByIds() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        dao.inserts(rowOne, rowTwo, rowThree)
        val deleteRows = dao.deletes(longArrayOf(1, 2, 3))
        val count = dao.count()
        println("Count of deletes: $deleteRows")
        Assert.assertEquals(0, count)
    }

    @Test fun insert() {
        val rowOne = User("test@test", "test")
        dao.insert(rowOne)
        val count = dao.count()
        Assert.assertEquals(1, count)
    }

    @Test fun insertsList() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        val list: List<User> = listOf(rowOne, rowTwo, rowThree)
        dao.inserts(list)
        val count = dao.count()
        Assert.assertEquals(3, count)
    }

    @Test fun insertsVararg() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        dao.inserts(rowOne, rowTwo, rowThree)
        val count = dao.count()
        Assert.assertEquals(3, count)
    }

    @Test fun update() {
        val rowOne = User("test@test", "test")
        dao.insert(rowOne)
        val modified = User("modified", "modified", 1)
        val count = dao.update(modified)
        Assert.assertEquals(1, count)
    }

    @Test fun updatesList() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        var list: List<User> = listOf(rowOne, rowTwo, rowThree)
        dao.inserts(list)
        val rowOneModified = User("test@testM", "test", 1)
        val rowTwoModified = User("test2@testM", "test2", 2)
        val rowThreeNotModified = User("test3@test", "test3", 3)
        val rowFailUpdate = User("fail", "fail", 100)
        list = listOf(rowOneModified, rowTwoModified, rowFailUpdate)
        val aux = dao.updates(list)
        println(aux.toString() + "")
        list = listOf(rowOneModified, rowTwoModified, rowThreeNotModified)
        val usersDB = dao.get()
        Assert.assertArrayEquals(list.toTypedArray(), usersDB.toTypedArray())
    }

    @Test fun updatesVararg() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        dao.inserts(rowOne, rowTwo, rowThree)
        val rowOneModified = User("test@testM", "test", 1)
        val rowTwoNotModified = User("test2@test", "test2", 2)
        val rowThreeModified = User("test3@testM", "test3", 3)
        val rowFailUpdate = User("fail", "fail", 100)
        val aux = dao.updates(rowOneModified, rowThreeModified, rowFailUpdate)
        println(aux.toString() + "")
        val list: List<User> = listOf(rowOneModified, rowTwoNotModified, rowThreeModified)
        val usersDB = dao.get()
        Assert.assertArrayEquals(list.toTypedArray(), usersDB.toTypedArray())
    }

    @Test fun delete() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        dao.inserts(rowOne, rowTwo, rowThree)
        val deletes = dao.delete(User("test@test", "test", 1))
        val count = dao.count()
        println("deleted: $deletes")
        Assert.assertEquals(2, count)
    }

    @Test fun deletesList() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        dao.inserts(rowOne, rowTwo, rowThree)
        val deleteRows: List<User> = listOf(
            User("test@test", "test", 1),
            User("test2@test", "test2", 2),
            User("test3@test", "test3", 3)
        )
        val deletes = dao.deletes(deleteRows)
        val count = dao.count()
        println("Count of deletes: $deletes")
        Assert.assertEquals(0, count)
    }

    @Test fun deletesVararg() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        dao.inserts(rowOne, rowTwo, rowThree)
        val deletes = dao.deletes(
            User("test@test", "test", 1),
            User("test2@test", "test2", 2),
            User("test3@test", "test3", 3)
        )
        val count = dao.count()
        println("Count of deletes: $deletes")
        Assert.assertEquals(0, count)
    }

    @Test fun countNothing() {
        val count = dao.count()
        Assert.assertEquals(0, count)
    }


    @Test fun getInEmpty() {
        val list = dao.get()
        Assert.assertEquals(0, list.size)
    }

    @Test fun dropInEmpty() {
        dao.drop()
        val count = dao.count()
        Assert.assertEquals(0, count)
    }

    @Test fun getByIdsInEmpty() {
        val list = dao.get(longArrayOf(1, 2, 3))
        Assert.assertEquals(0, list.size)
    }

    @Test fun deleteByIdInEmpty() {
        val delete = dao.delete(2)
        println("deleted: $delete")
        Assert.assertEquals(0, delete)
    }

    @Test fun deleteByIdNotFound() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        dao.inserts(rowOne, rowTwo, rowThree)
        val delete = dao.delete(5)
        println("deleted: $delete")
        Assert.assertEquals(0, delete)
    }

    @Test fun deletesByIdsInEmpty() {
        val deleteRows = dao.deletes(longArrayOf(1, 2, 3))
        println("Count of deletes: $deleteRows")
        Assert.assertEquals(0, deleteRows)
    }

    @Test fun deletesByIdsNotFoundIds() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        dao.inserts(rowOne, rowTwo, rowThree)
        val deleteRows = dao.deletes(longArrayOf(4, 5, 6))
        val count = dao.count()
        println("Count of deletes: $deleteRows")
        Assert.assertEquals(3, count)
    }

    @Test fun deletesListWithDifferentContentAndSameId() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        dao.inserts(rowOne, rowTwo, rowThree)
        val deleteRows: List<User> = listOf(
            User("random", "random", 1),
            User("random", "random", 2),
            User("random", "random", 3)
        )
        val deletes = dao.deletes(deleteRows)
        val count = dao.count()
        println("Count of deletes: $deletes")
        Assert.assertNotEquals(3, count)
    }

    @Test fun deletesListWithSameContentAndNotFoundIds() {
        val rowOne = User("test@test", "test")
        val rowTwo = User("test2@test", "test2")
        val rowThree = User("test3@test", "test3")
        dao.inserts(rowOne, rowTwo, rowThree)
        val deleteRows: List<User> = listOf(
            User("test@test", "test", 4),
            User("test2@test", "test2", 5),
            User("test3@test", "test3", 6)
        )
        val deletes = dao.deletes(deleteRows)
        val count = dao.count()
        println("Count of deletes: $deletes")
        Assert.assertEquals(3, count)
    }
}
