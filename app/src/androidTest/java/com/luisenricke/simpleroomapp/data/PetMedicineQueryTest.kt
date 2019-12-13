package com.luisenricke.simpleroomapp.data

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.luisenricke.simpleroomapp.data.dao.MedicineDAO
import com.luisenricke.simpleroomapp.data.dao.PetDAO
import com.luisenricke.simpleroomapp.data.dao.PetMedicineDAO
import com.luisenricke.simpleroomapp.data.dao.UserDAO
import com.luisenricke.simpleroomapp.data.entity.Medicine
import com.luisenricke.simpleroomapp.data.entity.Pet
import com.luisenricke.simpleroomapp.data.entity.PetMedicine
import com.luisenricke.simpleroomapp.data.entity.User
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)

class PetMedicineQueryTest {
    private lateinit var user: UserDAO
    private lateinit var pet: PetDAO
    private lateinit var medicine: MedicineDAO
    private lateinit var petMedicine: PetMedicineDAO
    private lateinit var database: AppDatabase

    @Before fun setup() {
        val instrumentation: Context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder<AppDatabase>(instrumentation, AppDatabase::class.java).build()
        user = database.user()
        pet = database.pet()
        medicine = database.medicine()
        petMedicine = database.petMedicine()


        user.insert(User("test@test", "test"))

        val timestamp = System.currentTimeMillis()
        val currentDate = Date(timestamp)
        pet.inserts(
            Pet("test1", currentDate, 1),
            Pet("test2", currentDate, 1),
            Pet("test3", currentDate, 1)
        )

        medicine.inserts(
            Medicine("test1", "oral"),
            Medicine("test2", "oral"),
            Medicine("test3", "oral")
        )

        petMedicine.inserts(
            PetMedicine(1, 1, currentDate, currentDate),
            PetMedicine(1, 2, currentDate, currentDate),
            PetMedicine(1, 3, currentDate, currentDate),
            PetMedicine(3, 1, currentDate, currentDate),
            PetMedicine(3, 2, currentDate, currentDate),
            PetMedicine(3, 3, currentDate, currentDate)
        )
    }

    @After fun finish() {
        database.close()
    }

    @Test fun checkData() {
        val users = user.get()
        val pets = pet.get()
        val medicine = medicine.get()
        val petMedicine = petMedicine.get()
        Assert.assertEquals(1, users.size)
        Assert.assertEquals(3, pets.size)
        Assert.assertEquals(3, medicine.size)
        Assert.assertEquals(6, petMedicine.size)
    }

    @Test fun getJoinPets() {
        val pets = petMedicine.getJoinPets(1)
        pets.forEach { println("Dog: ${it.name}") }
        Assert.assertEquals(2, pets.size)
    }

    @Test fun getJoinMedicine() {
        val medicines = petMedicine.getJoinMedicines(3)
        medicines.forEach { println("Medicine: ${it.name}") }
        Assert.assertEquals(3, medicines.size)
    }

    @Test fun insertSameForeignKeys() {
        val timestamp = System.currentTimeMillis()
        val currentDate = Date(timestamp)
        petMedicine.insert(
            PetMedicine(3, 3, currentDate, currentDate)
        )
        var row = petMedicine.get(6)
        println("PetMedecine: ${row.id} :: ${row.petId} - ${row.medicineId} :: ${row.createdAt}")
        row = petMedicine.get(7)
        println("PetMedecine: ${row.id} :: ${row.petId} - ${row.medicineId} :: ${row.createdAt}")
        val petMedicines = petMedicine.get()
        Assert.assertEquals(7, petMedicines.size)
    }
}