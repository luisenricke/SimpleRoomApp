package com.luisenricke.simpleroomapp.data_java;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.luisenricke.simpleroomapp.data_java.dao.DateTimeDAO;
import com.luisenricke.simpleroomapp.data_java.entity.DateTime;
import com.luisenricke.simpleroomapp.utils.DateOperation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DateTimeQueryTest {
    private DateTimeDAO dateTime;
    private DatabaseRoom database;

    @Before
    public void setup() {
        Context intrumentationContex = InstrumentationRegistry.getInstrumentation().getContext();
        database = Room.inMemoryDatabaseBuilder(intrumentationContex, DatabaseRoom.class).build();
        dateTime = database.dateTimeDAO();
    }

    @After
    public void finish() {
        database.close();
    }

    @Test
    public void checkDate() {
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        Calendar c = Calendar.getInstance();
        c.set(2019, 2, 3, 4, 5, 6);
        date = new Date(c.getTime().getTime());
        DateTime rowOne = new DateTime(date, date, date);
        dateTime.insert(rowOne);
        Date get = dateTime.get(1).getDate();
        List<Integer> check = Arrays.asList(2019, 2, 3);
        List<Integer> rowList = Arrays.asList(DateOperation.getYear(get),
                DateOperation.getMonth(get),
                DateOperation.getDay(get));

        Assert.assertArrayEquals(check.toArray(), rowList.toArray());
    }

    @Test
    public void checkTime() {
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        Calendar c = Calendar.getInstance();
        c.set(2019, 2, 3, 4, 5, 6);
        date = new Date(c.getTime().getTime());
        DateTime rowOne = new DateTime(date, date, date);
        dateTime.insert(rowOne);
        Date get = dateTime.get(1).getDate();
        List<Integer> check = Arrays.asList(4, 5, 6);
        List<Integer> rowList = Arrays.asList(DateOperation.getHour(get),
                DateOperation.getMinute(get),
                DateOperation.getSecond(get));

        Assert.assertArrayEquals(check.toArray(), rowList.toArray());
    }

    @Test
    public void checkDateTime() {
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        Calendar c = Calendar.getInstance();
        c.set(2019, 2, 3, 4, 5, 6);
        date = new Date(c.getTime().getTime());
        DateTime rowOne = new DateTime(date, date, date);
        dateTime.insert(rowOne);
        Date get = dateTime.get(1).getDate();
        List<Integer> check = Arrays.asList(2019, 2, 3, 4, 5, 6);
        List<Integer> rowList = Arrays.asList(DateOperation.getYear(get),
                DateOperation.getMonth(get),
                DateOperation.getDay(get),
                DateOperation.getHour(get),
                DateOperation.getMinute(get),
                DateOperation.getSecond(get));

        Assert.assertArrayEquals(check.toArray(), rowList.toArray());
    }
}
