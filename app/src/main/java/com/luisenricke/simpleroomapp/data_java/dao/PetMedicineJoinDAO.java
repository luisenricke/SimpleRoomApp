package com.luisenricke.simpleroomapp.data_java.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.luisenricke.simpleroomapp.data_java.BaseDAO;
import com.luisenricke.simpleroomapp.data_java.entity.Medicine;
import com.luisenricke.simpleroomapp.data_java.entity.Pet;
import com.luisenricke.simpleroomapp.data_java.entity.PetMedicineJoin;
import com.luisenricke.simpleroomapp.data_java.entity.PetMedicineJoin.SCHEMA;

import java.util.List;

@Dao
public abstract class PetMedicineJoinDAO implements BaseDAO<PetMedicineJoin>,
        BaseDAO.InnerJoinDAO<Pet, Medicine> {

    @Override
    @Query("SELECT COUNT(*) FROM " + SCHEMA.TABLE)
    abstract public int count();

    @Override
    @Query("SELECT * FROM PetMedicineJoin")
    public abstract List<PetMedicineJoin> get();

    @Override
    @Query("DELETE FROM " + SCHEMA.TABLE)
    abstract public void drop();

    @Override
    @Query("SELECT * FROM Pet " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON Pet.id = " + SCHEMA.TABLE + ".Pet_id " +
            "WHERE " + SCHEMA.TABLE + ".Medicine_id = :idRight")
    abstract public List<Pet> getLeftJoinRight(int idRight);

    @Override
    @Query("SELECT * FROM Medicine " +
            "INNER JOIN " + SCHEMA.TABLE + " " +
            "ON Medicine.id = " + SCHEMA.TABLE + ".Medicine_id " +
            "WHERE " + SCHEMA.TABLE + ".Pet_id = :idLeft")
    abstract public List<Medicine> getRightJoinLeft(int idLeft);

/*
    @Query("SELECT * FROM Pet " +
            "INNER JOIN PetMedicineJoin " +
            "ON Pet.id = PetMedicineJoin.Pet_id " +
            "WHERE PetMedicineJoin.Medicine_id = :id")
    public abstract List<Pet> getPetsOfMedicine(int id);


    @Query("SELECT * FROM Medicine " +
            "INNER JOIN PetMedicineJoin " +
            "ON Medicine.id = PetMedicineJoin.Medicine_id " +
            "WHERE PetMedicineJoin.Pet_id = :id")
    public abstract List<Medicine> getMedicinesOfPet(int id);
 */
}
