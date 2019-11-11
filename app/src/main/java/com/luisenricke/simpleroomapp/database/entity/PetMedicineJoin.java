package com.luisenricke.simpleroomapp.database.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import com.luisenricke.simpleroomapp.database.Schema;

import java.util.Date;

@Entity(tableName = Schema.PetMedicineJoin.TABLE,
        primaryKeys = {Schema.PetMedicineJoin.PET,
                Schema.PetMedicineJoin.MEDICINE},
        foreignKeys = {
                @ForeignKey(entity = Pet.class,
                        parentColumns = Schema.Pet.ID,
                        childColumns = Schema.PetMedicineJoin.PET),
                @ForeignKey(entity = Medicine.class,
                        parentColumns = Schema.Medicine.ID,
                        childColumns = Schema.PetMedicineJoin.MEDICINE)}
)
public class PetMedicineJoin {
    @ColumnInfo(name = Schema.PetMedicineJoin.PET)
    private int pet_id;
    @ColumnInfo(name = Schema.PetMedicineJoin.MEDICINE)
    private int medicine_id;
    @ColumnInfo(name = Schema.PetMedicineJoin.CREATED_AT, defaultValue = "CURRENT_TIMESTAMP")
    private Date created_at;
    @ColumnInfo(name = Schema.PetMedicineJoin.UPDATED_AT, defaultValue = "CURRENT_TIMESTAMP")
    private Date updated_at;

    @Ignore
    public PetMedicineJoin() {
    }

    public PetMedicineJoin(int pet_id, int medicine_id) {
        this.pet_id = pet_id;
        this.medicine_id = medicine_id;
    }

    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != PetMedicineJoin.class) return false;
        PetMedicineJoin cast = (PetMedicineJoin) obj;
        return cast.medicine_id == getMedicine_id() && cast.pet_id == getPet_id();
    }
}
