package com.luisenricke.simpleroomapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.luisenricke.simpleroomapp.database.entity.Medicine;

import java.util.List;

@Dao
public abstract class MedicineDAO {

    @Insert
    public abstract long insert(Medicine row);

    @Insert
    public abstract void inserts(Medicine... row);

    @Insert
    public abstract void inserts(List<Medicine> rows);

    @Update
    public abstract int update(Medicine row);

    @Update
    public abstract int updates(Medicine... rows);

    @Update
    public abstract int updates(List<Medicine> rows);

    @Delete
    public abstract void delete(Medicine row);

    @Delete
    public abstract void deletes(Medicine... row);

    @Delete
    public abstract void deletes(List<Medicine> rows);

    @Query("DELETE FROM Medicine WHERE id = :id")
    public abstract int deleteById(int id);

    @Query("DELETE FROM Medicine")
    public abstract void drop();

    @Query("SELECT * FROM Medicine")
    public abstract List<Medicine> get();

    @Query("SELECT * FROM Medicine WHERE id = :id")
    public abstract Medicine getById(int id);

    @Query("SELECT COUNT(*) FROM Medicine")
    public abstract long count();
}
