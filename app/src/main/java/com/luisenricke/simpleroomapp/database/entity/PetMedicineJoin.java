package com.luisenricke.simpleroomapp.database.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import java.util.Date;

import static com.luisenricke.simpleroomapp.database.entity.PetMedicineJoin.COLUMN.*;

@Entity(tableName = TABLE,
        primaryKeys = {PET,
                MEDICINE},
        foreignKeys = {
                @ForeignKey(entity = Pet.class,
                        parentColumns = Pet.COLUMN.ID,
                        childColumns = PET),
                @ForeignKey(entity = Medicine.class,
                        parentColumns = Medicine.COLUMN.ID,
                        childColumns = MEDICINE)}
)
public class PetMedicineJoin {
    public interface COLUMN {
        String TABLE = "PetMedicineJoin";
        String PET = "pet_id";
        String MEDICINE = "medicine_id";
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
    }

    @ColumnInfo(name = PET)
    private int pet_id;
    @ColumnInfo(name = MEDICINE)
    private int medicine_id;
    @ColumnInfo(name = CREATED_AT, defaultValue = "CURRENT_TIMESTAMP")
    private Date created_at;
    @ColumnInfo(name = UPDATED_AT, defaultValue = "CURRENT_TIMESTAMP")
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
