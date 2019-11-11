package com.luisenricke.simpleroomapp.data

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.luisenricke.simpleroomapp.data.contact.Contact
import com.luisenricke.simpleroomapp.data.contact.ContactDAO
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ContactQueryTest {
    private lateinit var instrumentationContext: Context
    private lateinit var dao: ContactDAO
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(instrumentationContext, AppDatabase::class.java)
            .build()
        dao = db.contactDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

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

    @Test
    @Throws(Exception::class)
    fun getListOfRowsCompact() {
        val rowOne = Contact("test@test.com,", "test", 1)
        val rowTwo = Contact("test@test.com,", "test2", 2)
        val rowThree =
            Contact("test@test.com,", "test3", 3)
        val list = listOf(rowOne, rowTwo, rowThree)
        dao.inserts(list)
        val checkList = dao.getCustomColumns()

        Assert.assertEquals(list[0].name, checkList[0].name)
    }

    @Test
    @Throws(Exception::class)
    fun insertSimpleRowDifferentId() {
        val row = Contact("test@test.com,", "test", 10)
        val idGenerated = dao.insert(row)

        Assert.assertNotEquals(1, idGenerated)
    }

    @Test
    @Throws(Exception::class)
    fun insertSimpleRow() {
        val row = Contact("test@test.com,", "test")
        val idGenerated = dao.insert(row)

        Assert.assertEquals(1, idGenerated)
    }

    @Test
    @Throws(Exception::class)
    fun insertVarargRows() {
        val rowOne = Contact("test@test.com,", "test")
        val rowTwo = Contact("test@test.com,", "test")
        val rowThree = Contact("test@test.com,", "test")
        dao.inserts(rowOne, rowTwo, rowThree)
        val checkRow = dao.count()

        Assert.assertEquals(3, checkRow)
    }


    @Test
    @Throws(Exception::class)
    fun insertListOfRows() {
        val rowOne = Contact("test@test.com,", "test")
        val rowTwo = Contact("test@test.com,", "test")
        val rowThree = Contact("test@test.com,", "test")
        val list = listOf(rowOne, rowTwo, rowThree)
        dao.inserts(list)
        val checkRow = dao.count()

        Assert.assertEquals(3, checkRow)
    }

    @Test
    @Throws(Exception::class)
    fun updateRow() {
        val rowOne = Contact("test@test.com,", "test", 1)
        val rowTwo = Contact("test@test.com,", "test", 2)
        val rowThree =
            Contact("test@test.com,", "test", 3)
        val list = listOf(rowOne, rowTwo, rowThree)
        dao.inserts(list)
        val updatedRow = dao.filterId(2)
        updatedRow.name = "changed"
        dao.update(updatedRow)

        val checkRow = dao.filterId(2)

        Assert.assertEquals(updatedRow.name, checkRow.name)
    }

    @Test
    @Throws(Exception::class)
    fun updateAllRows() {
        val rowOne = Contact("test@test.com,", "test", 1)
        val rowTwo = Contact("test@test.com,", "test", 2)
        val rowThree =
            Contact("test@test.com,", "test", 3)
        val list = listOf(rowOne, rowTwo, rowThree)
        dao.inserts(list)
        val rowOneUpdated =
            Contact("test@test.com,", "One", 1)
        val rowTwoUpdated =
            Contact("test@test.com,", "Two", 2)
        val rowThreeUpdated =
            Contact("test@test.com,", "Three", 3)
        val listUpdated = listOf(rowOneUpdated, rowTwoUpdated, rowThreeUpdated)
        dao.reset(listUpdated)
        val checkListRows = dao.get()

        Assert.assertArrayEquals(listUpdated.toTypedArray(), checkListRows.toTypedArray())
    }

    @Test
    @Throws(Exception::class)
    fun deleteByObjectRow() {
        val row = Contact("test@test.com,", "test")
        dao.insert(row)
        val rowFind = dao.filterId(1)
        dao.delete(rowFind)
        val checkRow = dao.count()

        Assert.assertEquals(0, checkRow)
    }

    @Test
    @Throws(Exception::class)
    fun deleteByIdRow() {
        val row = Contact("test@test.com,", "test")
        val rowInserted = dao.insert(row)
        dao.delete(rowInserted)
        val checkRow = dao.count()

        Assert.assertEquals(0, checkRow)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllRows() {
        val rowOne = Contact("test@test.com,", "test", 1)
        val rowTwo = Contact("test@test.com,", "test", 2)
        val rowThree =
            Contact("test@test.com,", "test", 3)
        val list = listOf(rowOne, rowTwo, rowThree)
        dao.inserts(list)
        dao.drop()
        val checkListRowCount = dao.count()

        Assert.assertEquals(0, checkListRowCount)
    }
}
