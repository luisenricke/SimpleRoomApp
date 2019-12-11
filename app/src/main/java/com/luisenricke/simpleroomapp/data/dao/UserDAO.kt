package com.luisenricke.simpleroomapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.luisenricke.simpleroomapp.data.entity.User
import com.luisenricke.simpleroomapp.data.entity.User.SCHEMA

@Dao
@Suppress("unused")
abstract class UserDAO : Base<User>,
    Base.UpdateDAO<User>,
    Base.DeleteDAO<User>,
    Base.PrimaryKeyDAO<User> {

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")
    abstract override fun count(): Int

    @Query("SELECT * FROM ${SCHEMA.TABLE}")
    abstract override fun get(): List<User>

    @Query("DELETE FROM ${SCHEMA.TABLE}")
    abstract override fun drop()

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun get(id: Int): User

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun get(ids: IntArray): List<User>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun delete(id: Int): Int

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun deletes(ids: IntArray): Int
}