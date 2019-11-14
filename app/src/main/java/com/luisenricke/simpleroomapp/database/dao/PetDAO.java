package com.luisenricke.simpleroomapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.luisenricke.simpleroomapp.database.entity.Pet;

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
    public abstract int update(Pet row);

    @Update
    public abstract int updates(Pet... rows);

    @Update
    public abstract int updates(List<Pet> rows);

    @Delete
    public abstract int delete(Pet row);

    @Delete
    public abstract int deletes(Pet... row);

    @Delete
    public abstract int deletes(List<Pet> rows);

    @Query("DELETE FROM Pet WHERE id = :id")
    public abstract int deleteById(int id);

    @Query("DELETE FROM Pet")
    public abstract void drop();

    @Query("DELETE FROM Pet WHERE user_id = :idUser")
    public abstract void dropByUser(int idUser);

    @Query("SELECT * FROM Pet")
    public abstract List<Pet> get();

    @Query("SELECT * FROM Pet WHERE user_id = :idUser")
    public abstract List<Pet> getByUser(int idUser);

    @Query("SELECT * FROM Pet WHERE id = :id")
    public abstract Pet getById(int id);

    @Query("SELECT COUNT(*) FROM Pet")
    public abstract long count();

    @Query("SELECT COUNT(*) FROM Pet where user_id = :idUser")
    public abstract long countByUser(int idUser);
}
