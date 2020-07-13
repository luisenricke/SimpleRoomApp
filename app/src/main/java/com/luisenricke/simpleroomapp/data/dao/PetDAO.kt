package com.luisenricke.simpleroomapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.luisenricke.room.dao.Base
import com.luisenricke.room.dao.Delete
import com.luisenricke.room.dao.PrimaryKey
import com.luisenricke.room.dao.Update
import com.luisenricke.simpleroomapp.data.entity.Pet
import com.luisenricke.simpleroomapp.data.entity.Pet.SCHEMA

@Suppress("unused")
@Dao
abstract class PetDAO : Base<Pet>, Update<Pet>, Delete<Pet>, PrimaryKey<Pet> {

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")
    abstract override fun count(): Long

    @Query("SELECT * FROM ${SCHEMA.TABLE}")
    abstract override fun get(): List<Pet>

    @Query("DELETE FROM ${SCHEMA.TABLE}")
    abstract override fun drop()

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun get(id: Long): Pet?

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun get(ids: LongArray): List<Pet>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun delete(id: Long): Int

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun deletes(ids: LongArray): Int

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE} WHERE user_id IS NOT NULL")
    abstract fun countByUser(): Long

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE} WHERE user_id IS NULL")
    abstract fun countNotByUser(): Long

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE} WHERE user_id = :fk")
    abstract fun countByUser(fk: Long): Long

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE user_id IS NOT NULL")
    abstract fun getByUser(): List<Pet>

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE user_id IS NULL")
    abstract fun getNotByUser(): List<Pet>

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE user_id = :fk")
    abstract fun getByUser(fk: Long): List<Pet>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE user_id = :fk")
    abstract fun dropByUser(fk: Long)
}
