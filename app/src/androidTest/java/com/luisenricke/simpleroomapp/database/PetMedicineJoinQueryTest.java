package com.luisenricke.simpleroomapp.database;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.luisenricke.simpleroomapp.database.converter.DateConverter;
import com.luisenricke.simpleroomapp.database.dao.MedicineDAO;
import com.luisenricke.simpleroomapp.database.dao.PetDAO;
import com.luisenricke.simpleroomapp.database.dao.PetMedicineJoinDAO;
import com.luisenricke.simpleroomapp.database.entity.Medicine;
import com.luisenricke.simpleroomapp.database.entity.Pet;
import com.luisenricke.simpleroomapp.database.entity.PetMedicineJoin;
import com.luisenricke.simpleroomapp.database.entity.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class PetMedicineJoinQueryTest {

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
        User user = database.userDAO().getById(1);

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
        PetMedicineJoin rowOne = new PetMedicineJoin(1, 1);

        petMedicineJoin.insert(rowOne);

        List<PetMedicineJoin> list = petMedicineJoin.get();

        for (PetMedicineJoin row : list) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(1, list.size());
    }

    @Test
    public void insertsList() {
        PetMedicineJoin rowOne = new PetMedicineJoin(1, 1);
        PetMedicineJoin rowTwo = new PetMedicineJoin(2, 1);
        PetMedicineJoin rowThree = new PetMedicineJoin(3, 1);
        PetMedicineJoin rowFour = new PetMedicineJoin(1, 2);
        PetMedicineJoin rowFive = new PetMedicineJoin(2, 2);
        PetMedicineJoin rowSix = new PetMedicineJoin(3, 2);
        PetMedicineJoin rowSeven = new PetMedicineJoin(1, 3);
        PetMedicineJoin rowEight = new PetMedicineJoin(2, 3);
        PetMedicineJoin rowNine = new PetMedicineJoin(3, 3);

        List<PetMedicineJoin> list = Arrays.asList(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix, rowSeven, rowEight, rowNine);
        petMedicineJoin.inserts(list);

        List<PetMedicineJoin> aux = petMedicineJoin.get();

        for (PetMedicineJoin row : aux) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(9, aux.size());
    }

    @Test
    public void insertsVarags() {
        PetMedicineJoin rowOne = new PetMedicineJoin(1, 1);
        PetMedicineJoin rowTwo = new PetMedicineJoin(2, 1);
        PetMedicineJoin rowThree = new PetMedicineJoin(3, 1);
        PetMedicineJoin rowFour = new PetMedicineJoin(1, 2);
        PetMedicineJoin rowFive = new PetMedicineJoin(2, 2);
        PetMedicineJoin rowSix = new PetMedicineJoin(3, 2);
        PetMedicineJoin rowSeven = new PetMedicineJoin(1, 3);
        PetMedicineJoin rowEight = new PetMedicineJoin(2, 3);
        PetMedicineJoin rowNine = new PetMedicineJoin(3, 3);

        petMedicineJoin.inserts(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix, rowSeven, rowEight, rowNine);

        List<PetMedicineJoin> aux = petMedicineJoin.get();

        for (PetMedicineJoin row : aux) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(9, aux.size());
    }

    @Test
    public void deletePet() {
        PetMedicineJoin rowOne = new PetMedicineJoin(1, 1);
        PetMedicineJoin rowTwo = new PetMedicineJoin(2, 1);
        PetMedicineJoin rowThree = new PetMedicineJoin(3, 1);
        PetMedicineJoin rowFour = new PetMedicineJoin(1, 2);
        PetMedicineJoin rowFive = new PetMedicineJoin(2, 2);
        PetMedicineJoin rowSix = new PetMedicineJoin(3, 2);
        PetMedicineJoin rowSeven = new PetMedicineJoin(1, 3);
        PetMedicineJoin rowEight = new PetMedicineJoin(2, 3);
        PetMedicineJoin rowNine = new PetMedicineJoin(3, 3);

        petMedicineJoin.inserts(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix, rowSeven, rowEight, rowNine);

        pet.delete(pet.getById(1));
        List<PetMedicineJoin> aux = petMedicineJoin.get();

        for (PetMedicineJoin row : aux) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(6, aux.size());
    }

    @Test
    public void deleteMedicines() {
        PetMedicineJoin rowOne = new PetMedicineJoin(1, 1);
        PetMedicineJoin rowTwo = new PetMedicineJoin(2, 1);
        PetMedicineJoin rowThree = new PetMedicineJoin(3, 1);
        PetMedicineJoin rowFour = new PetMedicineJoin(1, 2);
        PetMedicineJoin rowFive = new PetMedicineJoin(2, 2);
        PetMedicineJoin rowSix = new PetMedicineJoin(3, 2);
        PetMedicineJoin rowSeven = new PetMedicineJoin(1, 3);
        PetMedicineJoin rowEight = new PetMedicineJoin(2, 3);
        PetMedicineJoin rowNine = new PetMedicineJoin(3, 3);

        petMedicineJoin.inserts(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix, rowSeven, rowEight, rowNine);

        medicine.delete(medicine.getById(1));
        medicine.delete(medicine.getById(2));
        medicine.delete(medicine.getById(3));
        List<PetMedicineJoin> aux = petMedicineJoin.get();

        for (PetMedicineJoin row : aux) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(0, aux.size());
    }

    @Test
    public void get() {
        PetMedicineJoin rowOne = new PetMedicineJoin(1, 1);
        PetMedicineJoin rowTwo = new PetMedicineJoin(2, 1);
        PetMedicineJoin rowThree = new PetMedicineJoin(3, 1);
        PetMedicineJoin rowFour = new PetMedicineJoin(1, 2);
        PetMedicineJoin rowFive = new PetMedicineJoin(2, 2);
        PetMedicineJoin rowSix = new PetMedicineJoin(3, 2);
        PetMedicineJoin rowSeven = new PetMedicineJoin(1, 3);
        PetMedicineJoin rowEight = new PetMedicineJoin(2, 3);
        PetMedicineJoin rowNine = new PetMedicineJoin(3, 3);

        petMedicineJoin.inserts(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix, rowSeven, rowEight, rowNine);

        List<PetMedicineJoin> list = petMedicineJoin.get();

        for (PetMedicineJoin row : list) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(9, list.size());
    }

    @Test
    public void petsInMedicine() {
        PetMedicineJoin rowOne = new PetMedicineJoin(1, 1);
        PetMedicineJoin rowTwo = new PetMedicineJoin(2, 1);
        PetMedicineJoin rowThree = new PetMedicineJoin(3, 1);
        PetMedicineJoin rowFour = new PetMedicineJoin(1, 2);
        PetMedicineJoin rowFive = new PetMedicineJoin(2, 2);
        PetMedicineJoin rowSix = new PetMedicineJoin(3, 2);

        petMedicineJoin.inserts(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix);

        List<Pet> petsFromMedicine = petMedicineJoin.getLeftJoinRight(1);
        List<PetMedicineJoin> list = petMedicineJoin.get();

        for (Pet row : petsFromMedicine) {
            System.out.println("Pet -> id: " + row.getId() + " name: " + row.getName() + " born: " + row.getBorn());
        }

        for (PetMedicineJoin row : list) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(3, petsFromMedicine.size());
    }

    @Test
    public void medicinesInPet() {
        PetMedicineJoin rowOne = new PetMedicineJoin(1, 1);
        PetMedicineJoin rowTwo = new PetMedicineJoin(1, 2);
        PetMedicineJoin rowThree = new PetMedicineJoin(1, 3);
        PetMedicineJoin rowFour = new PetMedicineJoin(2, 1);
        PetMedicineJoin rowFive = new PetMedicineJoin(2, 2);
        PetMedicineJoin rowSix = new PetMedicineJoin(2, 3);

        petMedicineJoin.inserts(rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix);

        List<Medicine> medicinesFromPet = petMedicineJoin.getRightJoinLeft(1);
        List<PetMedicineJoin> list = petMedicineJoin.get();

        for (Medicine row : medicinesFromPet) {
            System.out.println("Medicine -> id: " + row.getId() + " name: " + row.getName() + " application: " + row.getApplication());
        }

        for (PetMedicineJoin row : list) {
            System.out.println("JOIN -> Pet: " + row.getPetId() + " Medicine: " + row.getMedicineId() + " created: " + DateConverter.toString(row.getCreated()));
        }

        Assert.assertEquals(3, medicinesFromPet.size());
    }
}