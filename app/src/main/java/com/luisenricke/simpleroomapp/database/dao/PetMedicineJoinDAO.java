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

    @Query("SELECT * FROM Pet " +
            "INNER JOIN PetMedicineJoin " +
            "ON Pet.id = PetMedicineJoin.pet_id " +
            "WHERE PetMedicineJoin.medicine_id = :medicine_id")
    public abstract List<Pet> getPetsForMedicine(int medicine_id);

    @Query("SELECT * FROM Medicine " +
            "INNER JOIN PetMedicineJoin " +
            "ON Medicine.id = PetMedicineJoin.medicine_id " +
            "WHERE PetMedicineJoin.pet_id = :pet_id")
    public abstract List<Medicine> getMedicineForMedicine(int pet_id);
}
