package com.luisenricke.simpleroomapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.luisenricke.simpleroomapp.database.pet.Pet;
import com.luisenricke.simpleroomapp.database.pet.PetDAO;
import com.luisenricke.simpleroomapp.database.user.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class PetQueryTest {

    private PetDAO dao;
    private DatabaseRoom database;
    private User user;

    @Before
    public void setup() {
        Context intrumentationContex = InstrumentationRegistry.getInstrumentation().getContext();
        database = Room.inMemoryDatabaseBuilder(intrumentationContex, DatabaseRoom.class).build();
        dao = database.petDAO();

        //Provide one user for Tests
        database.userDAO().insert(new User("test@test", "test"));
        user = database.userDAO().getById(1);
    }

    @After
    public void finish() {
        database.close();
    }

    @Test
    public void insert() {
        long count = 0;

        Pet rowOne = new Pet("Adonis", "11/08/2019", user.getId());
        dao.insert(rowOne);
        count = dao.count();

        Assert.assertEquals(1, count);
    }

    @Test
    public void insertWithoutUser() {
        long count = 0;

        try {
            Pet rowOne = new Pet("Adonis", "11/08/2019");
            dao.insert(rowOne);
        } catch (SQLiteConstraintException e) {
            Log.i("Error", "The parameter user is null");
            Assert.assertTrue(true);
            e.printStackTrace();
        }

        count = dao.count();

        Assert.assertEquals(0, count);
    }

    @Test
    public void insertDoesNotExist() {
        long count = 0;

        try {
            Pet rowOne = new Pet("Adonis", "11/08/2019", 123);
            dao.insert(rowOne);
        } catch (SQLiteConstraintException e) {
            Log.i("Error", "Doesn't exist user");
            Assert.assertTrue(true);
            e.printStackTrace();
        }

        count = dao.count();

        Assert.assertEquals(0, count);
    }

    @Test
    public void insertsList() {
        long count = 0;

        Pet rowOne = new Pet("Adonis", "11/08/2019", user.getId());
        Pet rowTwo = new Pet("Adonis2", "12/08/2019", user.getId());
        Pet rowThree = new Pet("Adonis3", "13/08/2019", user.getId());

        List<Pet> list = Arrays.asList(rowOne, rowTwo, rowThree);
        dao.inserts(list);
        count = dao.countByUser(user.getId());

        Assert.assertEquals(3, count);
    }

    @Test
    public void insertsVarags() {
        long count = 0;

        Pet rowOne = new Pet("Adonis", "11/08/2019", user.getId());
        Pet rowTwo = new Pet("Adonis2", "12/08/2019", user.getId());
        Pet rowThree = new Pet("Adonis3", "13/08/2019", user.getId());

        dao.inserts(rowOne, rowTwo, rowThree);
        count = dao.countByUser(user.getId());

        Assert.assertEquals(3, count);
    }

    @Test
    public void update() {
        Pet rowOne = new Pet("Adonis", "11/08/2019", user.getId());

        dao.insert(rowOne);
        Pet modified = new Pet(1, "modified", "12/08/2019", user.getId());
        dao.update(modified);
        Pet userDB = dao.getById(1);

        Assert.assertEquals(modified, userDB);
    }

    @Test
    public void updatesList() {
        Pet rowOne = new Pet("Adonis", "11/08/2019", user.getId());
        Pet rowTwo = new Pet("Adonis2", "12/08/2019", user.getId());
        Pet rowThree = new Pet("Adonis3", "13/08/2019", user.getId());

        List<Pet> list = Arrays.asList(rowOne, rowTwo, rowThree);
        dao.inserts(list);

        Pet rowOneModified = new Pet(1, "AdonisM", "11/08/2019", user.getId());
        Pet rowTwoModified = new Pet(2, "Adonis2M", "12/08/2019", user.getId());
        Pet rowThreeNotModified = new Pet(3, "Adonis3", "13/08/2019", user.getId());

        Pet rowFailUpdate = new Pet(100, "Fail", "13/08/2019", user.getId());
        list = Arrays.asList(rowOneModified, rowTwoModified, rowFailUpdate);

        int aux = dao.updates(list);
        Log.i("Update", aux + "");

        list = Arrays.asList(rowOneModified, rowTwoModified, rowThreeNotModified);

        List<Pet> petDB = dao.get();

        Assert.assertArrayEquals(list.toArray(), petDB.toArray());
    }

    @Test
    public void updatessVarags() {
        Pet rowOne = new Pet("Adonis", "11/08/2019", user.getId());
        Pet rowTwo = new Pet("Adonis2", "12/08/2019", user.getId());
        Pet rowThree = new Pet("Adonis3", "13/08/2019", user.getId());

        dao.inserts(rowOne, rowTwo, rowThree);

        Pet rowOneModified = new Pet(1, "AdonisM", "11/08/2019", user.getId());
        Pet rowTwoNotModified = new Pet(2, "Adonis2", "12/08/2019", user.getId());
        Pet rowThreeModified = new Pet(3, "Adonis3M", "13/08/2019", user.getId());
        Pet rowFailUpdate = new Pet(100, "Fail", "13/08/2019", user.getId());

        int aux = dao.updates(rowOneModified, rowThreeModified, rowFailUpdate);
        Log.i("Update", aux + "");

        List<Pet> list = Arrays.asList(rowOneModified, rowTwoNotModified, rowThreeModified);

        List<Pet> petDB = dao.getByUser(user.getId());

        Assert.assertArrayEquals(list.toArray(), petDB.toArray());
    }

    @Test
    public void drop() {
        long count = 0;

        Pet rowOne = new Pet("Adonis", "11/08/2019", user.getId());
        Pet rowTwo = new Pet("Adonis2", "12/08/2019", user.getId());
        Pet rowThree = new Pet("Adonis3", "13/08/2019", user.getId());

        dao.inserts(rowOne, rowTwo, rowThree);

        dao.drop();
        count = dao.count();

        Assert.assertEquals(0, count);
    }

    @Test
    public void dropByUser() {
        long count = 0;

        Pet rowOne = new Pet("Adonis", "11/08/2019", user.getId());
        Pet rowTwo = new Pet("Adonis2", "12/08/2019", user.getId());
        Pet rowThree = new Pet("Adonis3", "13/08/2019", user.getId());

        database.userDAO().insert(new User("test2@test", "test2"));
        User otherUser = database.userDAO().getById(2);
        Pet rowFour = new Pet("Adonis4", "14/08/2019", otherUser.getId());

        dao.inserts(rowOne, rowTwo, rowThree, rowFour);

        dao.dropByUser(user.getId());
        count = dao.count();

        Assert.assertEquals(1, count);
    }

    @Test
    public void delete() {
        long count = 0;

        Pet rowOne = new Pet("Adonis", "11/08/2019", user.getId());
        Pet rowTwo = new Pet("Adonis2", "12/08/2019", user.getId());
        Pet rowThree = new Pet("Adonis3", "13/08/2019", user.getId());

        dao.inserts(rowOne, rowTwo, rowThree);

        dao.delete(new Pet(1, "Adonis", "11/08/2019", user.getId()));
        count = dao.countByUser(user.getId());

        Assert.assertEquals(2, count);
    }

    @Test
    public void get() {
        Pet rowOne = new Pet("Adonis", "11/08/2019", user.getId());
        Pet rowTwo = new Pet("Adonis2", "12/08/2019", user.getId());
        Pet rowThree = new Pet("Adonis3", "13/08/2019", user.getId());

        dao.inserts(rowOne, rowTwo, rowThree);
        List<Pet> list = dao.get();

        Assert.assertEquals(3, list.size());
    }

    @Test
    public void getByUser() {
        Pet rowOne = new Pet("Adonis", "11/08/2019", user.getId());
        Pet rowTwo = new Pet("Adonis2", "12/08/2019", user.getId());
        Pet rowThree = new Pet("Adonis3", "13/08/2019", user.getId());

        database.userDAO().insert(new User("test2@test", "test2"));
        User otherUser = database.userDAO().getById(2);
        Pet rowFour = new Pet("Adonis4", "14/08/2019", otherUser.getId());

        dao.inserts(rowOne, rowTwo, rowThree, rowFour);

        Pet petFromOtherUser = dao.getById(4);

        List<Pet> checkList = dao.getByUser(otherUser.getId());

        Assert.assertArrayEquals(new Pet[]{petFromOtherUser}, checkList.toArray());
    }
}