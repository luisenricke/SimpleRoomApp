package com.luisenricke.simpleroomapp.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.luisenricke.simpleroomapp.database.BaseDAO;
import com.luisenricke.simpleroomapp.database.entity.User;
import com.luisenricke.simpleroomapp.database.entity.User.SCHEMA;

import java.util.List;


@Dao
public abstract class UserDAO implements BaseDAO<User>,
        BaseDAO.UpdateDAO<User>,
        BaseDAO.DeleteDAO<User>,
        BaseDAO.OperationsPrimaryKeyDAO<User> {

    @Override
    @Query("SELECT COUNT(*) FROM " + SCHEMA.TABLE)
    abstract public int count();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE)
    abstract public List<User> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    abstract public void drop();

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public User getById(int id);

    @Override
    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")
    public abstract List<User> getByIds(long[] ids);

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")
    abstract public int deleteById(int id);
}
