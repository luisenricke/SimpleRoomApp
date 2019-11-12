package com.luisenricke.simpleroomapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.luisenricke.simpleroomapp.database.entity.Medicine;
import com.luisenricke.simpleroomapp.database.entity.Pet;
import com.luisenricke.simpleroomapp.database.entity.PetMedicineJoin;

import java.util.List;

@Dao
public abstract class PetMedicineJoinDAO {

    @Insert
    public abstract void insert(PetMedicineJoin row);

    @Insert
    public abstract void inserts(PetMedicineJoin... row);

    @Insert
    public abstract void inserts(List<PetMedicineJoin> row);

    @Query("SELECT * FROM PetMedicineJoin")
    public abstract List<PetMedicineJoin> get();

    @Query("SELECT * FROM Pet " +
            "INNER JOIN PetMedicineJoin " +
            "ON Pet.id = PetMedicineJoin.pet_id " +
            "WHERE PetMedicineJoin.medicine_id = :id")
    public abstract List<Pet> getPetsOfMedicine(int id);

    @Query("SELECT * FROM Medicine " +
            "INNER JOIN PetMedicineJoin " +
            "ON Medicine.id = PetMedicineJoin.medicine_id " +
            "WHERE PetMedicineJoin.pet_id = :id")
    public abstract List<Medicine> getMedicinesOfPet(int id);
}
