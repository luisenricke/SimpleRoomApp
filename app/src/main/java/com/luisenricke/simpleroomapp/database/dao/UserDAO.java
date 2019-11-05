package com.luisenricke.simpleroomapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.luisenricke.simpleroomapp.database.Schema;

import java.util.List;

@Dao
public abstract class UserDAO implements Schema.Query, Schema.User {

    @Insert
    abstract long insert(User row);

    @Insert
    abstract void inserts(User... row);

    @Insert
    abstract void inserts(List<User> rows);

    @Delete
    abstract void delete(User row);

    @Query(SELECT_COUNT + TABLE)
    abstract long count();
}
