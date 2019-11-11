package com.luisenricke.simpleroomapp.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.luisenricke.simpleroomapp.database.entity.User;
import com.luisenricke.simpleroomapp.database.dao.UserDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class UserQueryTest {

    private UserDAO dao;
    private DatabaseRoom database;

    @Before
    public void setup() {
        Context intrumentationContex = InstrumentationRegistry.getInstrumentation().getContext();
        database = Room.inMemoryDatabaseBuilder(intrumentationContex, DatabaseRoom.class).build();
        dao = database.userDAO();
    }

    @After
    public void finish() {
        database.close();
    }

    @Test
    public void insert() {
        long count = 0;

        User rowOne = new User("test@test", "test");

        dao.insert(rowOne);
        count = dao.count();

        Assert.assertEquals(1, count);
    }

    @Test
    public void insertsList() {
        long count = 0;

        User rowOne = new User("test@test", "test");
        User rowTwo = new User("test2@test", "test2");
        User rowThree = new User("test3@test", "test3");

        List<User> list = Arrays.asList(rowOne, rowTwo, rowThree);
        dao.inserts(list);
        count = dao.count();

        Assert.assertEquals(3, count);
    }

    @Test
    public void insertsVarags() {
        long count = 0;

        User rowOne = new User("test@test", "test");
        User rowTwo = new User("test2@test", "test2");
        User rowThree = new User("test3@test", "test3");

        dao.inserts(rowOne, rowTwo, rowThree);
        count = dao.count();

        Assert.assertEquals(3, count);
    }

    @Test
    public void update() {
        User rowOne = new User("test@test", "test");

        dao.insert(rowOne);
        User modified = new User(1, "modified", "modified");
        dao.update(modified);
        User userDB = dao.getById(1);

        Assert.assertEquals(modified, userDB);
    }

    @Test
    public void updatesList() {
        User rowOne = new User("test@test", "test");
        User rowTwo = new User("test2@test", "test2");
        User rowThree = new User("test3@test", "test3");

        List<User> list = Arrays.asList(rowOne, rowTwo, rowThree);
        dao.inserts(list);

        User rowOneModified = new User(1, "test@testM", "test");
        User rowTwoModified = new User(2, "test2@testM", "test2");
        User rowThreeNotModified = new User(3, "test3@test", "test3");
        User rowFailUpdate = new User(100, "fail", "fail");
        list = Arrays.asList(rowOneModified, rowTwoModified, rowFailUpdate);

        int aux = dao.updates(list);
        Log.i("Update", aux + "");

        list = Arrays.asList(rowOneModified, rowTwoModified, rowThreeNotModified);

        List<User> usersDB = dao.get();

        Assert.assertArrayEquals(list.toArray(), usersDB.toArray());
    }

    @Test
    public void updatessVarags() {
        User rowOne = new User("test@test", "test");
        User rowTwo = new User("test2@test", "test2");
        User rowThree = new User("test3@test", "test3");

        dao.inserts(rowOne, rowTwo, rowThree);

        User rowOneModified = new User(1, "test@testM", "test");
        User rowTwoNotModified = new User(2, "test2@test", "test2");
        User rowThreeModified = new User(3, "test3@testM", "test3");
        User rowFailUpdate = new User(100, "fail", "fail");

        int aux = dao.updates(rowOneModified, rowThreeModified, rowFailUpdate);
        Log.i("Update", aux + "");

        List<User> list = Arrays.asList(rowOneModified, rowTwoNotModified, rowThreeModified);

        List<User> usersDB = dao.get();

        Assert.assertArrayEquals(list.toArray(), usersDB.toArray());
    }

    @Test
    public void drop() {
        long count = 0;

        User rowOne = new User("test@test", "test");
        User rowTwo = new User("test2@test", "test2");
        User rowThree = new User("test3@test", "test3");

        dao.inserts(rowOne, rowTwo, rowThree);

        dao.drop();
        count = dao.count();

        Assert.assertEquals(0, count);
    }

    @Test
    public void delete() {
        long count = 0;

        User rowOne = new User("test@test", "test");
        User rowTwo = new User("test2@test", "test2");
        User rowThree = new User("test3@test", "test3");

        dao.inserts(rowOne, rowTwo, rowThree);

        dao.delete(new User(2, "test2@test", "test2"));
        count = dao.count();

        Assert.assertEquals(2, count);
    }

    @Test
    public void get() {
        User rowOne = new User("test@test", "test");
        User rowTwo = new User("test2@test", "test2");
        User rowThree = new User("test3@test", "test3");

        dao.inserts(rowOne, rowTwo, rowThree);
        List<User> list = dao.get();

        Assert.assertEquals(3, list.size());
    }
}
