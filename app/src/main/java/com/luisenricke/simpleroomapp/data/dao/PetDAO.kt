package com.luisenricke.simpleroomapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.luisenricke.simpleroomapp.data.entity.Pet
import com.luisenricke.simpleroomapp.data.entity.Pet.SCHEMA
import com.luisenricke.simpleroomapp.data.entity.User

@Dao
@Suppress("unused")
abstract class PetDAO : Base<Pet>,
    Base.UpdateDAO<Pet>,
    Base.DeleteDAO<Pet>,
    Base.PrimaryKeyDAO<Pet> {


    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")
    abstract override fun count(): Int

    @Query("SELECT * FROM ${SCHEMA.TABLE}")
    abstract override fun get(): List<Pet>

    @Query("DELETE FROM ${SCHEMA.TABLE}")
    abstract override fun drop()

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun get(id: Int): Pet

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun get(ids: IntArray): List<Pet>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun delete(id: Int): Int

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun deletes(ids: IntArray): Int

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE} WHERE user_id = :fk")
    abstract fun countByUser(fk: Int): Int

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE user_id = :fk")
    abstract fun getByUser(fk: Int): List<Pet>

    @Query(
        """
            SELECT COUNT(*) FROM ${SCHEMA.TABLE} AS CHILD
            INNER JOIN ${SCHEMA.TABLE} AS PARENT
            ON CHILD.user_id = PARENT.id
        """
    )
    abstract fun countJoinByUser(): Int

    @Query(
        """
            SELECT COUNT(*) FROM ${SCHEMA.TABLE} AS CHILD
            INNER JOIN ${User.SCHEMA.TABLE} AS PARENT
            ON CHILD.user_id = PARENT.id
            WHERE user_id = :fk
        """
    )
    abstract fun countJoinByUser(fk: Int): Int

    @Query(
        """
            SELECT CHILD.* FROM ${SCHEMA.TABLE} AS CHILD
            INNER JOIN ${User.SCHEMA.TABLE} AS PARENT
            ON CHILD.user_id = PARENT.id
        """
    )
    abstract fun getJoinByUser(): List<Pet>

    @Query(
        """
            SELECT CHILD.* FROM ${SCHEMA.TABLE} AS CHILD
            INNER JOIN ${User.SCHEMA.TABLE} AS PARENT
            ON CHILD.user_id = PARENT.id
            WHERE user_id = :fk
        """
    )
    abstract fun getJoinByUser(fk: Int): List<Pet>

    @Query("DELETE FROM  ${SCHEMA.TABLE}  WHERE user_id = :fk")
    abstract fun dropByUser(fk: Int)
}