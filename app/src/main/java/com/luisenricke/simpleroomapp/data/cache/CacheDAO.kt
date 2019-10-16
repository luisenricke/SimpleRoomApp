package com.luisenricke.simpleroomapp.data.cache

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.luisenricke.simpleroomapp.data.BaseDAO
import com.luisenricke.simpleroomapp.data.DatabaseScheme

@Dao
abstract class CacheDAO : BaseDAO<Cache>,
    BaseDAO.ComplementaryOperationsWithList<Cache> {

    @Query("SELECT count(*) FROM ${DatabaseScheme.Cache.TABLE}")
    abstract override fun count(): Int

    @Query("SELECT * FROM ${DatabaseScheme.Cache.TABLE}")
    abstract override fun get(): List<Cache>

    @Query("DELETE FROM ${DatabaseScheme.Cache.TABLE}")
    abstract override fun drop()

    //Specific queries for the table
    @Query("SELECT * FROM ${DatabaseScheme.Cache.TABLE} ORDER BY ${DatabaseScheme.Cache.ID} LIMIT 1")
    abstract fun last(): Cache?

    @Query("DELETE FROM ${DatabaseScheme.Cache.TABLE} WHERE id = (SELECT MAX(id) FROM ${DatabaseScheme.Cache.TABLE})")
    abstract fun delete()

    @Transaction
    open fun push(row: Cache) {
        insert(row)
    }

    @Transaction
    open fun pop(): Cache? {
        val image: Cache? = last()
        delete()
        return image
    }
}