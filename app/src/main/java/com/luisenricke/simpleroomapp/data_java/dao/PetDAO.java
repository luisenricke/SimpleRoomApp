package com.luisenricke.simpleroomapp.data_java.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.luisenricke.simpleroomapp.data_java.BaseDAO;
import com.luisenricke.simpleroomapp.data_java.entity.Pet;
import com.luisenricke.simpleroomapp.data_java.entity.Pet.SCHEMA;
import com.luisenricke.simpleroomapp.data_java.entity.User;

import java.util.List;

@Dao
public abstract class PetDAO implements BaseDAO<Pet>,
        BaseDAO.UpdateDAO<Pet>,
        BaseDAO.DeleteDAO<Pet>,
        BaseDAO.OperationsPrimaryKeyDAO<Pet> {

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

    // Foreign key operations
    @Query("SELECT COUNT(*) FROM " + Pet.SCHEMA.TABLE + " AS CHILD"
            + " INNER JOIN " + User.SCHEMA.TABLE + " AS PARENT"
            + " ON CHILD.user_id = PARENT.id ")
    public abstract long countChildJoin();

    @Query("SELECT COUNT(*) FROM " + SCHEMA.TABLE + " WHERE user_id = :fk")
    public abstract long countChild(int fk);

    @Query("SELECT COUNT(*) FROM " + Pet.SCHEMA.TABLE + " AS CHILD"
            + " INNER JOIN " + User.SCHEMA.TABLE + " AS PARENT"
            + " ON CHILD.user_id = PARENT.id"
            + " WHERE CHILD.user_id = :fk")
    public abstract long countChildJoin(int fk);

    @Query("SELECT CHILD.* FROM " + Pet.SCHEMA.TABLE + " AS CHILD"
            + " INNER JOIN " + User.SCHEMA.TABLE + " AS PARENT"
            + " ON CHILD.user_id = PARENT.id ")
    public abstract List<Pet> getChildJoin();

    @Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE user_id = :fk")
    public abstract List<Pet> getChild(int fk);

    @Query("SELECT CHILD.* FROM " + Pet.SCHEMA.TABLE + " AS CHILD"
            + " INNER JOIN " + User.SCHEMA.TABLE + " AS PARENT"
            + " ON CHILD.user_id = PARENT.id"
            + " WHERE CHILD.user_id = :fk")
    public abstract List<Pet> getChildJoin(int fk);

    @Query("DELETE FROM " + SCHEMA.TABLE + " WHERE user_id = :fk")
    public abstract void drop(int fk);
}
