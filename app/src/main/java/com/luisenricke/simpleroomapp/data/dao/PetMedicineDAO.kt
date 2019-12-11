package com.luisenricke.simpleroomapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.luisenricke.simpleroomapp.data.entity.Medicine
import com.luisenricke.simpleroomapp.data.entity.Pet
import com.luisenricke.simpleroomapp.data.entity.PetMedicine
import com.luisenricke.simpleroomapp.data.entity.PetMedicine.SCHEMA

@Dao
@Suppress("unused")
abstract class PetMedicineDAO : Base<PetMedicine>,
    Base.UpdateDAO<PetMedicine>,
    Base.DeleteDAO<PetMedicine>,
    Base.PrimaryKeyDAO<PetMedicine> {

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")
    abstract override fun count(): Int

    @Query("SELECT * FROM ${SCHEMA.TABLE}")
    abstract override fun get(): List<PetMedicine>

    @Query("DELETE FROM ${SCHEMA.TABLE}")
    abstract override fun drop()

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun get(id: Int): PetMedicine

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun get(ids: IntArray): List<PetMedicine>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun delete(id: Int): Int

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun deletes(ids: IntArray): Int

    @Query(
        """
            SELECT * FROM ${Pet.SCHEMA.TABLE}
            INNER JOIN ${SCHEMA.TABLE}
            ON Pet.id = ${SCHEMA.TABLE}.pet_id
            WHERE ${SCHEMA.TABLE}.medicine_id = :idMedicine
        """
    )
    abstract fun getJoinPets(idMedicine: Int): List<Pet>

    @Query(
        """
            SELECT * FROM ${Medicine.SCHEMA.TABLE}
            INNER JOIN ${SCHEMA.TABLE}
            ON Medicine.id = ${SCHEMA.TABLE}.medicine_id
            WHERE ${SCHEMA.TABLE}.pet_id = :idPet
        """
    )
    abstract fun getJoinMedicines(idPet: Int): List<Medicine>

}