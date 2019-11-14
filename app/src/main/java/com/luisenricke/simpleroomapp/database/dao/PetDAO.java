package com.luisenricke.simpleroomapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.luisenricke.simpleroomapp.database.BaseDAO;
import com.luisenricke.simpleroomapp.database.entity.Pet;
import com.luisenricke.simpleroomapp.database.entity.Pet.SCHEMA;

import java.util.List;

@Dao
public abstract class PetDAO implements BaseDAO<Pet>,
        BaseDAO.UpdateDAO<Pet>,
        BaseDAO.DeleteDAO<Pet>,
        BaseDAO.OperationsPrimaryKeyDAO<Pet>,
        BaseDAO.OperationsForeignKeyDAO<Pet> {

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
    abstract public Pet getById(int id);

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public abstract List<Pet> getByIds(long[] ids);

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public int deleteById(int id);

    @Override
    @Query("SELECT COUNT(*) FROM " + SCHEMA.TABLE + " WHERE user_id = :foreignKeyValue")
    abstract public long countByReference(int foreignKeyValue);

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE user_id = :foreignKeyValue")
    abstract public List<Pet> getByReference(int foreignKeyValue);

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE user_id = :foreignKeyValue")
    abstract public void dropByReference(int foreignKeyValue);
}
