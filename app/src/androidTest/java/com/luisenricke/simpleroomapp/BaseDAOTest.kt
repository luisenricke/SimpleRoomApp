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
class BaseDAOTest {
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

    @Test fun insertWithId() {
        val rowOne = User("test@test", "test", 100)
        dao.insert(rowOne)
        val row = dao.get(100)
        println("id: ${row?.id}, email: ${row?.email}, password: ${row?.password}")
        Assert.assertEquals(rowOne, row)
    }

    @Test fun insertWithIdNegative() {
        val rowOne = User("test@test", "test", -1)
        dao.insert(rowOne)
        val row = dao.get(-1)
        Assert.assertEquals(rowOne, row)
    }

    @Test fun insertWithIdZero() {
        val rowOne = User("test@test", "test", 0)
        dao.insert(rowOne)
        val row = dao.get(0)
        val row1 = dao.get(1)
        Assert.assertNull(row)
        Assert.assertEquals(1, dao.count())
        Assert.assertNotNull(row1)
    }

    @Test fun insertWithIdNegativeSequence() {
        val rowOne = User("test@test", "test", -1)
        val rowTwo = User("test2@test", "test2")
        val inserts = dao.inserts(rowOne, rowTwo)
        Assert.assertNotEquals(arrayListOf(-1, 0), inserts)

        val count = dao.count()
        Assert.assertEquals(2, count)

        val row1 = dao.get(-1)
        val row2 = dao.get(1)
        Assert.assertEquals(rowOne, row1)
        Assert.assertEquals(User("test2@test", "test2", 1), row2)
    }

    @Test fun insertWithSameId() {
        val rowOne = User("test1@test", "test1", 100)
        dao.insert(rowOne)
        val rowTwo = User("test2@test", "test2", 100)
        dao.insert(rowTwo)

        val row = dao.get(100)
        val row2 = dao.get(101)
        Assert.assertNotNull(row)
        Assert.assertNull(row2)
    }

    @Test fun insertIdInterleaving() {
        val rowOne = User("test@test", "test", 100)
        dao.insert(rowOne)
        val rowTwo = User("test2@test", "test2")
        dao.insert(rowTwo)

        val successive = dao.get(101)
        val noSuccessive = dao.get(1)
        Assert.assertNotNull(successive)
        Assert.assertNull(noSuccessive)

    }

    @Test fun insertIdInterleavingSequence() {
        val rowOne = User("test@test", "test")
        dao.insert(rowOne)
        val rowTwo = User("test2@test", "test2", 100)
        dao.insert(rowTwo)
        val rowThree = User("test3@test", "test3")
        dao.insert(rowThree)
        val rowFour = User("test4@test", "test4")
        dao.insert(rowFour)

        val successiveOne = dao.get(100)
        val noSuccessiveOne = dao.get(2)
        Assert.assertNotNull(successiveOne)
        Assert.assertNull(noSuccessiveOne)
        val successiveTwo = dao.get(101)
        val noSuccessiveTwo = dao.get(3)
        Assert.assertNotNull(successiveTwo)
        Assert.assertNull(noSuccessiveTwo)
        val successiveThree = dao.get(102)
        val noSuccessiveThree = dao.get(4)
        Assert.assertNotNull(successiveThree)
        Assert.assertNull(noSuccessiveThree)
    }

    @Test fun insertCrossingIds() {
        val rowOne = User("test@test", "test")
        dao.insert(rowOne)
        val rowTwo = User("test2@test", "test2", 3)
        dao.insert(rowTwo)
        val rowThree = User("test3@test", "test3", 2)
        dao.insert(rowThree)
        val rowFour = User("test4@test", "test4")
        dao.insert(rowFour)

        dao.get().forEach { row ->
            println("id: ${row.id}, password: ${row.password}")
        }

        val successive = dao.get(5)
        val noSuccessive = dao.get(4)
        Assert.assertNull(successive)
        Assert.assertNotNull(noSuccessive)
    }

    @Test fun multipleInserts() {
        for (loop in 1..1000) {
            dao.insert(User("test${loop}@Test", "Test${loop}"))
        }
        Assert.assertEquals(1000, dao.count())
    }

    @Test fun countIfEmpty() {
        Assert.assertEquals(0, dao.count())
    }

    @Test fun countList() {
        val list = listOf(
            User("test@test", "test"),
            User("test2@test", "test2"),
            User("test3@test", "test3")
        )
        dao.inserts(list)
        val count = dao.count()
        Assert.assertEquals(3, count)
    }

    @Test fun getIfEmpty() {
        Assert.assertEquals(0, dao.get().size)
    }

    @Test fun getList() {
        val list = listOf(
            User("test@test", "test"),
            User("test2@test", "test2"),
            User("test3@test", "test3")
        )
        dao.inserts(list)
        val rows = dao.get()
        Assert.assertEquals(3, rows.size)
    }

    @Test fun dropIfEmpty() {
        dao.drop()
        Assert.assertEquals(0, dao.get().size)
    }

    @Test fun dropList() {
        val list = listOf(
            User("test@test", "test"),
            User("test2@test", "test2"),
            User("test3@test", "test3")
        )
        dao.inserts(list)
        Assert.assertEquals(3, dao.count())
        dao.drop()
        Assert.assertEquals(0, dao.count())
    }

    @Test fun multipleDrops() {
        for (loop in 1..100) {
            dao.drop()
        }
        Assert.assertEquals(0, dao.count())
    }
}
