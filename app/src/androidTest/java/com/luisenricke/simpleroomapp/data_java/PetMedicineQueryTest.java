package com.luisenricke.simpleroomapp.data_java;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.luisenricke.simpleroomapp.data_java.converter.DateConverter;
import com.luisenricke.simpleroomapp.data_java.dao.MedicineDAO;
import com.luisenricke.simpleroomapp.data_java.dao.PetDAO;
import com.luisenricke.simpleroomapp.data_java.dao.PetMedicineJoinDAO;
import com.luisenricke.simpleroomapp.data_java.entity.Medicine;
import com.luisenricke.simpleroomapp.data_java.entity.Pet;
import com.luisenricke.simpleroomapp.data_java.entity.PetMedicine;
import com.luisenricke.simpleroomapp.data_java.entity.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class PetMedicineQueryTest {

    private PetDAO pet;
    private MedicineDAO medicine;
    private PetMedicineJoinDAO petMedicineJoin;
    private DatabaseRoom database;

    @Before
    public void setup() {
        Context intrumentationContex = InstrumentationRegistry.getInstrumentation().getContext();
        database = Room.inMemoryDatabaseBuilder(intrumentationContex, DatabaseRoom.class).build();
        pet = database.petDAO();
        medicine = database.medicineDAO();
        petMedicineJoin = database.petMedicineJoinDAO();

        //Provide one user for Tests
        database.userDAO().insert(new User("test@test", "test"));
        User user = database.userDAO().get(1);

        Pet petRowOne = new Pet("Adonis", "11/08/2019", user.getId());
        Pet petRowTwo = new Pet("Adonis2", "12/08/2019", user.getId());
        Pet petRowThree = new Pet("Adonis3", "13/08/2019", user.getId());

        pet.inserts(petRowOne, petRowTwo, petRowThree);

        Medicine medicineRowOne = new Medicine("Paracetamol", 250, "Oral", "Farmacias del ahorro");
        Medicine medicineRowTwo = new Medicine("Paracetamol2", 500, "Oral", "Farmacias del ahorro");
        Medicine medicineRowThree = new Medicine("Paracetamol3", 750, "Oral", "Farmacias del ahorro");

        medicine.inserts(medicineRowOne, medicineRowTwo, medicineRowThree);
    }

    @After
    public void finish() {
        database.close();
    }

    @Test
    public void insert() {
        PetMedicine rowOne = new PetMedicine(1, 1);

        petMedicineJoin.insert(rowOne);

        List<PetMedicine> list = petMedicineJoin.get();

        for (PetMedicine row : list) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(1, list.size());
    }

    @Test
    public void insertsList() {
        PetMedicine rowOne = new PetMedicine(1, 1);
        PetMedicine rowTwo = new PetMedicine(2, 1);
        PetMedicine rowThree = new PetMedicine(3, 1);
        PetMedicine rowFour = new PetMedicine(1, 2);
        PetMedicine rowFive = new PetMedicine(2, 2);
        PetMedicine rowSix = new PetMedicine(3, 2);
        PetMedicine rowSeven = new PetMedicine(1, 3);
        PetMedicine rowEight = new PetMedicine(2, 3);
        PetMedicine rowNine = new PetMedicine(3, 3);

        List<PetMedicine> list = Arrays.asList(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix, rowSeven, rowEight, rowNine);
        petMedicineJoin.inserts(list);

        List<PetMedicine> aux = petMedicineJoin.get();

        for (PetMedicine row : aux) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(9, aux.size());
    }

    @Test
    public void insertsVarags() {
        PetMedicine rowOne = new PetMedicine(1, 1);
        PetMedicine rowTwo = new PetMedicine(2, 1);
        PetMedicine rowThree = new PetMedicine(3, 1);
        PetMedicine rowFour = new PetMedicine(1, 2);
        PetMedicine rowFive = new PetMedicine(2, 2);
        PetMedicine rowSix = new PetMedicine(3, 2);
        PetMedicine rowSeven = new PetMedicine(1, 3);
        PetMedicine rowEight = new PetMedicine(2, 3);
        PetMedicine rowNine = new PetMedicine(3, 3);

        petMedicineJoin.inserts(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix, rowSeven, rowEight, rowNine);

        List<PetMedicine> aux = petMedicineJoin.get();

        for (PetMedicine row : aux) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(9, aux.size());
    }

    @Test
    public void deletePet() {
        PetMedicine rowOne = new PetMedicine(1, 1);
        PetMedicine rowTwo = new PetMedicine(2, 1);
        PetMedicine rowThree = new PetMedicine(3, 1);
        PetMedicine rowFour = new PetMedicine(1, 2);
        PetMedicine rowFive = new PetMedicine(2, 2);
        PetMedicine rowSix = new PetMedicine(3, 2);
        PetMedicine rowSeven = new PetMedicine(1, 3);
        PetMedicine rowEight = new PetMedicine(2, 3);
        PetMedicine rowNine = new PetMedicine(3, 3);

        petMedicineJoin.inserts(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix, rowSeven, rowEight, rowNine);

        pet.delete(pet.get(1));
        List<PetMedicine> aux = petMedicineJoin.get();

        for (PetMedicine row : aux) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(6, aux.size());
    }

    @Test
    public void deleteMedicines() {
        PetMedicine rowOne = new PetMedicine(1, 1);
        PetMedicine rowTwo = new PetMedicine(2, 1);
        PetMedicine rowThree = new PetMedicine(3, 1);
        PetMedicine rowFour = new PetMedicine(1, 2);
        PetMedicine rowFive = new PetMedicine(2, 2);
        PetMedicine rowSix = new PetMedicine(3, 2);
        PetMedicine rowSeven = new PetMedicine(1, 3);
        PetMedicine rowEight = new PetMedicine(2, 3);
        PetMedicine rowNine = new PetMedicine(3, 3);

        petMedicineJoin.inserts(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix, rowSeven, rowEight, rowNine);

        medicine.delete(medicine.get(1));
        medicine.delete(medicine.get(2));
        medicine.delete(medicine.get(3));
        List<PetMedicine> aux = petMedicineJoin.get();

        for (PetMedicine row : aux) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(0, aux.size());
    }

    @Test
    public void get() {
        PetMedicine rowOne = new PetMedicine(1, 1);
        PetMedicine rowTwo = new PetMedicine(2, 1);
        PetMedicine rowThree = new PetMedicine(3, 1);
        PetMedicine rowFour = new PetMedicine(1, 2);
        PetMedicine rowFive = new PetMedicine(2, 2);
        PetMedicine rowSix = new PetMedicine(3, 2);
        PetMedicine rowSeven = new PetMedicine(1, 3);
        PetMedicine rowEight = new PetMedicine(2, 3);
        PetMedicine rowNine = new PetMedicine(3, 3);

        petMedicineJoin.inserts(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix, rowSeven, rowEight, rowNine);

        List<PetMedicine> list = petMedicineJoin.get();

        for (PetMedicine row : list) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(9, list.size());
    }

    @Test
    public void petsInMedicine() {
        PetMedicine rowOne = new PetMedicine(1, 1);
        PetMedicine rowTwo = new PetMedicine(2, 1);
        PetMedicine rowThree = new PetMedicine(3, 1);
        PetMedicine rowFour = new PetMedicine(1, 2);
        PetMedicine rowFive = new PetMedicine(2, 2);
        PetMedicine rowSix = new PetMedicine(3, 2);

        petMedicineJoin.inserts(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix);

        List<Pet> petsFromMedicine = petMedicineJoin.getLeftRightJoin(1);
        List<PetMedicine> list = petMedicineJoin.get();

        for (Pet row : petsFromMedicine) {
            System.out.println("Pet -> id: " + row.getId() + " name: " + row.getName() + " born: " + row.getBorn());
        }

        for (PetMedicine row : list) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(3, petsFromMedicine.size());
    }

    @Test
    public void medicinesInPet() {
        PetMedicine rowOne = new PetMedicine(1, 1);
        PetMedicine rowTwo = new PetMedicine(1, 2);
        PetMedicine rowThree = new PetMedicine(1, 3);
        PetMedicine rowFour = new PetMedicine(2, 1);
        PetMedicine rowFive = new PetMedicine(2, 2);
        PetMedicine rowSix = new PetMedicine(2, 3);

        petMedicineJoin.inserts(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix);

        List<Medicine> medicinesFromPet = petMedicineJoin.getRightLeftJoin(1);
        List<PetMedicine> list = petMedicineJoin.get();

        for (Medicine row : medicinesFromPet) {
            System.out.println("Medicine -> id: " + row.getId() + " name: " + row.getName() + " application: " + row.getApplication());
        }

        for (PetMedicine row : list) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(3, medicinesFromPet.size());
    }
}