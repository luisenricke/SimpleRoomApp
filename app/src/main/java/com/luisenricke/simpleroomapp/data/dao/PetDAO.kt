package com.luisenricke.simpleroomapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.luisenricke.room.dao.Base
import com.luisenricke.room.dao.Delete
import com.luisenricke.room.dao.PrimaryKey
import com.luisenricke.room.dao.Update
import com.luisenricke.simpleroomapp.data.entity.Pet
import com.luisenricke.simpleroomapp.data.entity.Pet.SCHEMA
import com.luisenricke.simpleroomapp.data.entity.User

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
    abstract override fun get(id: Long): Pet

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun get(ids: LongArray): List<Pet>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun delete(id: Long): Int

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun deletes(ids: LongArray): Int


    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE} WHERE ${SCHEMA.USER_ID} = :fk")
    abstract fun countByUser(fk: Long): Long

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE ${SCHEMA.USER_ID} = :fk")
    abstract fun getByUser(fk: Long): List<Pet>

    @Query(
        """
            SELECT COUNT(*) FROM ${SCHEMA.TABLE} AS CHILD
            INNER JOIN ${User.SCHEMA.TABLE} AS PARENT
            ON CHILD.${SCHEMA.USER_ID} = PARENT.${User.SCHEMA.ID}
        """
    )
    abstract fun countJoinByUser(): Long

    @Query(
        """
            SELECT COUNT(*) FROM ${SCHEMA.TABLE} AS CHILD
            INNER JOIN ${User.SCHEMA.TABLE} AS PARENT
            ON CHILD.${SCHEMA.USER_ID} = PARENT.${User.SCHEMA.ID}
            WHERE CHILD.${SCHEMA.USER_ID} = :fk
        """
    )
    abstract fun countJoinByUser(fk: Long): Long

    @Query(
        """
            SELECT CHILD.* FROM ${SCHEMA.TABLE} AS CHILD
            INNER JOIN ${User.SCHEMA.TABLE} AS PARENT
            ON CHILD.${SCHEMA.USER_ID} = PARENT.${User.SCHEMA.ID}
        """
    )
    abstract fun getJoinByUser(): List<Pet>

    @Query(
        """
            SELECT CHILD.* FROM ${SCHEMA.TABLE} AS CHILD
            INNER JOIN ${User.SCHEMA.TABLE} AS PARENT
            ON CHILD.${SCHEMA.USER_ID} = PARENT.${User.SCHEMA.ID}
            WHERE CHILD.${SCHEMA.USER_ID} = :fk
        """
    )
    abstract fun getJoinByUser(fk: Long): List<Pet>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE ${SCHEMA.USER_ID} = :fk")
    abstract fun dropByUser(fk: Long)
}
