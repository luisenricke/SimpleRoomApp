package com.luisenricke.simpleroomapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.luisenricke.room.dao.Base
import com.luisenricke.room.dao.Delete
import com.luisenricke.room.dao.PrimaryKey
import com.luisenricke.room.dao.Update
import com.luisenricke.room.dao.InnerJoin
import com.luisenricke.simpleroomapp.data.entity.Medicine
import com.luisenricke.simpleroomapp.data.entity.Pet
import com.luisenricke.simpleroomapp.data.entity.PetMedicine
import com.luisenricke.simpleroomapp.data.entity.PetMedicine.SCHEMA

@Suppress("unused")
@Dao
abstract class PetMedicineDAO : Base<PetMedicine>, Update<PetMedicine>, Delete<PetMedicine>, PrimaryKey<PetMedicine> {

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")
    abstract override fun count(): Long

    @Query("SELECT * FROM ${SCHEMA.TABLE}")
    abstract override fun get(): List<PetMedicine>

    @Query("DELETE FROM ${SCHEMA.TABLE}")
    abstract override fun drop()

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun get(id: Long): PetMedicine?

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun get(ids: LongArray): List<PetMedicine>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun delete(id: Long): Int

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun deletes(ids: LongArray): Int

    @Query(
        """
            SELECT * FROM ${Pet.SCHEMA.TABLE} AS LEFT_ 
            INNER JOIN ${SCHEMA.TABLE} AS RIGHT_ 
            ON LEFT_.${Pet.SCHEMA.ID} = RIGHT_.${SCHEMA.PET_ID} 
            WHERE RIGHT_.${SCHEMA.MEDICINE_ID} = :idMedicine 
        """
    )
    abstract fun getPets(idMedicine: Long): List<Pet>

    @Query(
        """
            SELECT * FROM ${Medicine.SCHEMA.TABLE} AS RIGHT_ 
            INNER JOIN ${SCHEMA.TABLE} AS LEFT_ 
            ON RIGHT_.${Medicine.SCHEMA.ID} = LEFT_.${SCHEMA.MEDICINE_ID} 
            WHERE LEFT_.${SCHEMA.PET_ID} = :idPet 
        """
    )
    abstract fun getMedicines(idPet: Long): List<Medicine>
}
