package com.luisenricke.simpleroomapp.data_java.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.luisenricke.simpleroomapp.data_java.BaseDAO;
import com.luisenricke.simpleroomapp.data_java.entity.Pet;
import com.luisenricke.simpleroomapp.data_java.entity.Pet.SCHEMA;

import java.util.List;

@Dao
public abstract class PetDAO implements BaseDAO<Pet>,
        BaseDAO.UpdateDAO<Pet>,
        BaseDAO.DeleteDAO<Pet>,
        BaseDAO.OperationsPrimaryKeyDAO<Pet>{

    @Override
    @Query("SELECT COUNT(*) FROM " + SCHEMA.TABLE)
    abstract public int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    abstract public List<Pet> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    abstract public void drop();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public Pet get(int id);

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public abstract List<Pet> get(long[] ids);

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public int delete(int id);
}
