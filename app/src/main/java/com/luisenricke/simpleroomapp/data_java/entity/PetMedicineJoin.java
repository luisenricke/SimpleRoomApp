package com.luisenricke.simpleroomapp.data_java.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import java.util.Date;

import static com.luisenricke.simpleroomapp.data_java.entity.PetMedicineJoin.SCHEMA.*;

@Entity(tableName = TABLE,
        primaryKeys = {PET,
                MEDICINE},
        foreignKeys = {
                @ForeignKey(entity = Pet.class,
                        parentColumns = Pet.SCHEMA.ID,
                        childColumns = PET,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Medicine.class,
                        parentColumns = Medicine.SCHEMA.ID,
                        childColumns = MEDICINE,
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)}
)
public class PetMedicineJoin {
    public interface SCHEMA {
        String TABLE = "PetMedicineJoin";
        String PET = "pet_id";
        String MEDICINE = "medicine_id";
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
    }

    @ColumnInfo(name = PET)
    private int petId;
    @ColumnInfo(name = MEDICINE)
    private int medicineId;
    @ColumnInfo(name = CREATED_AT)
    private Date created;
    @ColumnInfo(name = UPDATED_AT)
    private Date updated;

    @Ignore
    public PetMedicineJoin() {
    }

    public PetMedicineJoin(int petId, int medicineId) {
        this.petId = petId;
        this.medicineId = medicineId;
        long time = System.currentTimeMillis();
        created = new Date(time);
        updated = new Date(time);
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != PetMedicineJoin.class) return false;
        PetMedicineJoin cast = (PetMedicineJoin) obj;
        return cast.medicineId == getMedicineId() && cast.petId == getPetId();
    }
}
