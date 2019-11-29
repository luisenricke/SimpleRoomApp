package com.luisenricke.simpleroomapp.data_java.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.luisenricke.simpleroomapp.data_java.BaseDAO;
import com.luisenricke.simpleroomapp.data_java.entity.Medicine;
import com.luisenricke.simpleroomapp.data_java.entity.Pet;
import com.luisenricke.simpleroomapp.data_java.entity.PetMedicine;
import com.luisenricke.simpleroomapp.data_java.entity.PetMedicine.SCHEMA;

import java.util.List;

@Dao
public abstract class PetMedicineJoinDAO implements BaseDAO<PetMedicine>,
        BaseDAO.InnerJoinDAO<Pet, Medicine> {

    @Override
    @Query("SELECT COUNT(*) FROM " + SCHEMA.TABLE)
    abstract public int count();

    @Override
    @Query("SELECT * FROM PetMedicine")
    public abstract List<PetMedicine> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    abstract public void drop();

    @Override
    @Query("SELECT * FROM Pet " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON Pet.id = " + SCHEMA.TABLE + ".Pet_id " +
            "WHERE " + SCHEMA.TABLE + ".Medicine_id = :idRight")
    abstract public List<Pet> getLeftRightJoin(int idRight);

    @Override
    @Query("SELECT * FROM Medicine " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON Medicine.id = " + SCHEMA.TABLE + ".Medicine_id " +
            "WHERE " + SCHEMA.TABLE + ".Pet_id = :idLeft")
    abstract public List<Medicine> getRightLeftJoin(int idLeft);

/*
    @Query("SELECT * FROM Pet " +
            "INNER JOIN PetMedicine " +
            "ON Pet.id = PetMedicine.Pet_id " +
            "WHERE PetMedicine.Medicine_id = :id")
    public abstract List<Pet> getPetsOfMedicine(int id);


    @Query("SELECT * FROM Medicine " +
            "INNER JOIN PetMedicine " +
            "ON Medicine.id = PetMedicine.Medicine_id " +
            "WHERE PetMedicine.Pet_id = :id")
    public abstract List<Medicine> getMedicinesOfPet(int id);
 */
}
