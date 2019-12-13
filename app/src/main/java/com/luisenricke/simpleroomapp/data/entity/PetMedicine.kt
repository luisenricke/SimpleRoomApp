package com.luisenricke.simpleroomapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Suppress("SpellCheckingInspection")
@Entity(
    tableName = PetMedicine.SCHEMA.TABLE,
    foreignKeys = [
        ForeignKey(
            entity = Pet::class,
            parentColumns = arrayOf(Pet.SCHEMA.ID),
            childColumns = arrayOf(PetMedicine.SCHEMA.PET_ID),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Medicine::class,
            parentColumns = arrayOf(Medicine.SCHEMA.ID),
            childColumns = arrayOf(PetMedicine.SCHEMA.MEDICINE_ID),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PetMedicine(
    @ColumnInfo(name = SCHEMA.PET_ID)
    val petId: Int,
    @ColumnInfo(name = SCHEMA.MEDICINE_ID)
    val medicineId: Int,
    @ColumnInfo(name = SCHEMA.CREATED_AT)
    val createdAt: Date,
    @ColumnInfo(name = SCHEMA.UPDATED_AT)
    val updatedAt: Date,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SCHEMA.ID)
    val id: Long = 0
) {
    object SCHEMA {
        const val TABLE = "PetMedecine"
        const val ID = "id"
        const val PET_ID = "pet_id"
        const val MEDICINE_ID = "medicine_id"
        const val CREATED_AT = "created_at"
        const val UPDATED_AT = "updated_at"
    }
}