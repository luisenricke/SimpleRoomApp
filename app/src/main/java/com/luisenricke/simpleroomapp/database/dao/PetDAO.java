package com.luisenricke.simpleroomapp.database.pet;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract class PetDAO {

    @Insert
    public abstract long insert(Pet row);

    @Insert
    public abstract void inserts(Pet... row);

    @Insert
    public abstract void inserts(List<Pet> rows);

    @Update
    public abstract void update(Pet row);

    @Update
    public abstract int updates(Pet... rows);

    @Update
    public abstract int updates(List<Pet> rows);

    @Delete
    public abstract void delete(Pet row);

    @Query("DELETE FROM Pet")
    public abstract void drop();

    @Query("DELETE FROM Pet WHERE user_id = :user_id")
    public abstract void dropByUser(int user_id);

    @Query("SELECT * FROM Pet")
    public abstract List<Pet> get();

    @Query("SELECT * FROM Pet WHERE user_id = :user_id")
    public abstract List<Pet> getByUser(int user_id);

    @Query("SELECT * FROM Pet WHERE id = :id")
    public abstract Pet getById(int id);

    @Query("SELECT COUNT(*) FROM Pet")
    public abstract long count();

    @Query("SELECT COUNT(*) FROM Pet where user_id = :user_id")
    public abstract long countByUser(int user_id);
}
