package com.luisenricke.simpleroomapp.data_java.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.luisenricke.simpleroomapp.data_java.BaseDAO;
import com.luisenricke.simpleroomapp.data_java.entity.DateTime;
import com.luisenricke.simpleroomapp.data_java.entity.DateTime.SCHEMA;

import java.util.List;

@Dao
public abstract class DateTimeDAO implements BaseDAO<DateTime>,
        BaseDAO.UpdateDAO<DateTime>,
        BaseDAO.DeleteDAO<DateTime>,
        BaseDAO.OperationsPrimaryKeyDAO<DateTime> {

    @Override
    @Query("SELECT COUNT(*) FROM " + SCHEMA.TABLE)
    abstract public int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    abstract public List<DateTime> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    abstract public void drop();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public DateTime get(int id);

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public abstract List<DateTime> get(long[] ids);

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public int delete(int id);
}
