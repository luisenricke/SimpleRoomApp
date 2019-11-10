package com.luisenricke.simpleroomapp.database.user;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract class UserDAO {

    @Insert
    public abstract long insert(User row);

    @Insert
    public abstract void inserts(User... row);

    @Insert
    public abstract void inserts(List<User> rows);

    @Update
    public abstract void update(User row);

    @Update
    public abstract int updates(User... rows);

    @Update
    public abstract int updates(List<User> rows);

    @Delete
    public abstract void delete(User row);

    @Query("DELETE FROM User")
    public abstract void drop();

    @Query("SELECT * FROM User")
    public abstract List<User> get();

    @Query("SELECT * FROM User WHERE id = :id")
    public abstract User getById(int id);

    @Query("SELECT COUNT(*) FROM User")
    public abstract long count();
}
