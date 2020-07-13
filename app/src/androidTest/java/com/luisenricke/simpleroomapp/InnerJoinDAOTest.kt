package com.luisenricke.simpleroomapp

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.luisenricke.simpleroomapp.data.AppDatabase
import com.luisenricke.simpleroomapp.data.dao.PetDAO
import com.luisenricke.simpleroomapp.data.dao.PetMedicineDAO
import com.luisenricke.simpleroomapp.data.entity.Medicine
import com.luisenricke.simpleroomapp.data.entity.Pet
import com.luisenricke.simpleroomapp.data.entity.PetMedicine
import com.luisenricke.simpleroomapp.data.entity.User
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date

@RunWith(AndroidJUnit4::class)
class InnerJoinDAOTest {
    private lateinit var dao: PetMedicineDAO
    private lateinit var database: AppDatabase

    private val currentTime = System.currentTimeMillis()

    private val users = arrayListOf(
        User("test1@test", "test1", 1),
        User("test2@test", "test2", 2),
        User("test3@test", "test3", 3)
    )

    private val pets = arrayListOf(
        Pet("test1", Date(currentTime), 1, 1),
        Pet("test2", Date(currentTime), 2, 2),
        Pet("test3", Date(currentTime), 3, 3),
        Pet("test4", Date(currentTime), 1, 4),
        Pet("test5", Date(currentTime), 2, 5),
        Pet("test6", Date(currentTime), 3, 6),
        Pet("test7", Date(currentTime), 1, 7),
        Pet("test8", Date(currentTime), 2, 8)
    )

    private val medicines = arrayListOf(
        Medicine("test1", "oral", 1),
        Medicine("test2", "oral", 2),
        Medicine("test2", "oral", 3)
    )

    private val petMedicines = arrayListOf(
        PetMedicine(1, 1, Date(currentTime), Date(currentTime), 1),
        PetMedicine(2, 2, Date(currentTime), Date(currentTime), 2),
        PetMedicine(3, 3, Date(currentTime), Date(currentTime), 3),
        PetMedicine(4, 1, Date(currentTime), Date(currentTime), 4),
        PetMedicine(5, 2, Date(currentTime), Date(currentTime), 5),
        PetMedicine(6, 3, Date(currentTime), Date(currentTime), 6),
        PetMedicine(7, 1, Date(currentTime), Date(currentTime), 7),
        PetMedicine(8, 2, Date(currentTime), Date(currentTime), 8),
        PetMedicine(1, 1, Date(currentTime), Date(currentTime), 9),
        PetMedicine(1, 1, Date(currentTime), Date(currentTime), 10),
        PetMedicine(1, 1, Date(currentTime), Date(currentTime), 11)
    )

    @Before fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder<AppDatabase>(context, AppDatabase::class.java).build()
        dao = database.petMedicine()

        database.user().inserts(users)
        database.pet().inserts(pets)
        database.medicine().inserts(medicines)
        dao.inserts(petMedicines)
    }

    @After fun finish() {
        database.close()
    }

    @Test fun checkData() {
        val usersCount = database.user().count()
        val petsCount = database.pet().count()
        val medicineCount = database.medicine().count()
        val petMedicineCount = dao.count()
        Assert.assertEquals(3, usersCount)
        Assert.assertEquals(8, petsCount)
        Assert.assertEquals(3, medicineCount)
        Assert.assertEquals(11, petMedicineCount)
    }

    @Test fun getJoinLeft() {
        val pets = dao.getPets(1)
        pets.forEach { println("Pet = ${it.id} - ${it.name}") }
        Assert.assertEquals(6, pets.size)
    }

    @Test fun getJoinRight() {
        val medicines = dao.getMedicines(1)
        medicines.forEach { println("Medicine = ${it.id} - ${it.name}") }
        Assert.assertEquals(4, medicines.size)
    }
}
