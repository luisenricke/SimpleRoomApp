package com.luisenricke.simpleroomapp.database;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.luisenricke.simpleroomapp.database.entity.Pet;
import com.luisenricke.simpleroomapp.database.entity.User;
import com.luisenricke.simpleroomapp.database.dao.UserDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
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
        int count = 0;
        User rowOne = new User("test@test", "test");

        dao.insert(rowOne);
        User modified = new User(1, "modified", "modified");
        count = dao.update(modified);

        Assert.assertEquals(1, count);
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
        System.out.println(aux + "");

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
        System.out.println(aux + "");

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
        int deletes = 0;

        User rowOne = new User("test@test", "test");
        User rowTwo = new User("test2@test", "test2");
        User rowThree = new User("test3@test", "test3");

        dao.inserts(rowOne, rowTwo, rowThree);

        deletes = dao.delete(new User(2, "test2@test", "test2"));
        count = dao.count();
        System.out.println("Count of deletes: " + deletes);

        Assert.assertEquals(2, count);
    }

    @Test
    public void deletesList() {
        long count = 0;
        int deletes = 0;

        User rowOne = new User("test@test", "test");
        User rowTwo = new User("test2@test", "test2");
        User rowThree = new User("test3@test", "test3");

        dao.inserts(rowOne, rowTwo, rowThree);

        List<User> deleteRows = Arrays.asList(new User(1, "test@test", "test"),
                new User(2, "test2@test", "test2"),
                new User(3, "test3@test", "test3"));
        deletes = dao.deletes(deleteRows);
        count = dao.count();
        System.out.println("Count of deletes: " + deletes);

        Assert.assertEquals(0, count);
    }

    @Test
    public void deletesVarags() {
        long count = 0;
        int deletes = 0;

        User rowOne = new User("test@test", "test");
        User rowTwo = new User("test2@test", "test2");
        User rowThree = new User("test3@test", "test3");

        dao.inserts(rowOne, rowTwo, rowThree);

        deletes = dao.deletes(new User(1, "test@test", "test"),
                new User(2, "test2@test", "test2"),
                new User(100, "random", "random"));
        count = dao.count();
        System.out.println("Count of deletes: " + deletes);

        Assert.assertEquals(1, count);
    }

    @Test
    public void deleteById() {
        long count = 0;

        User rowOne = new User("test@test", "test");
        User rowTwo = new User("test2@test", "test2");
        User rowThree = new User("test3@test", "test3");

        dao.inserts(rowOne, rowTwo, rowThree);

        count = dao.deleteById(1);

        Assert.assertEquals(1, count);
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

    @Test
    public void getByIds() {
        User rowOne = new User("test@test", "test");
        User rowTwo = new User("test2@test", "test2");
        User rowThree = new User("test3@test", "test3");

        dao.inserts(rowOne, rowTwo, rowThree);
        List<User> list = dao.getByIds(new long[]{1, 2, 3});
        List<User> check = dao.get();

        Assert.assertArrayEquals(check.toArray(), list.toArray());
    }
}
