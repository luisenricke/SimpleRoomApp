package com.luisenricke.simpleroomapp.data_java.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.luisenricke.simpleroomapp.data_java.BaseDAO;
import com.luisenricke.simpleroomapp.data_java.entity.Medicine;
import com.luisenricke.simpleroomapp.data_java.entity.Medicine.SCHEMA;

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
    abstract public Medicine get(int id);

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public abstract List<Medicine> get(long[] ids);

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public int delete(int id);
}
