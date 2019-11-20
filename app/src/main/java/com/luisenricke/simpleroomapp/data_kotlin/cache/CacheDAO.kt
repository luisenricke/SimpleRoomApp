package com.luisenricke.simpleroomapp.data_kotlin.cache

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.luisenricke.simpleroomapp.data_kotlin.BaseDAO
import com.luisenricke.simpleroomapp.data_kotlin.cache.Cache.SCHEMA

@Dao
abstract class CacheDAO :  BaseDAO<Cache>,
    BaseDAO.UpdateDAO<Cache>,
    BaseDAO.DeleteDAO<Cache>,
    BaseDAO.OperationsPrimaryKeyDAO<Cache> {

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")
    abstract override fun count(): Int

    @Query("SELECT * FROM ${SCHEMA.TABLE}")
    abstract override fun get(): List<Cache>

    @Query("DELETE FROM ${SCHEMA.TABLE}")
    abstract override fun drop()

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun getById(id: Int): Cache

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun getByIds(ids: LongArray): List<Cache>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun deleteById(id: Int): Int

    @Query("SELECT * FROM ${SCHEMA.TABLE} ORDER BY ${SCHEMA.ID} LIMIT 1")
    abstract fun last(): Cache?

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = (SELECT MAX(id) FROM ${SCHEMA.TABLE})")
    abstract fun deleteLast()

    @Transaction
    open fun push(row: Cache) {
        insert(row)
    }

    @Transaction
    open fun pop(): Cache? {
        val image: Cache? = last()
        deleteLast()
        return image
    }
}