package com.luisenricke.simpleroomapp.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.luisenricke.simpleroomapp.database.BaseDAO;
import com.luisenricke.simpleroomapp.database.entity.Medicine;
import com.luisenricke.simpleroomapp.database.entity.Medicine.SCHEMA;

import java.util.List;

@Dao
public abstract class MedicineDAO implements BaseDAO<Medicine>,
        BaseDAO.UpdateDAO<Medicine>,
        BaseDAO.DeleteDAO<Medicine>,
        BaseDAO.OperationsPrimaryKeyDAO<Medicine> {

    @Override
    @Query("SELECT COUNT(*) FROM " + SCHEMA.TABLE)
    abstract public int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    abstract public List<Medicine> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    abstract public void drop();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public Medicine getById(int id);

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public int deleteById(int id);
}
