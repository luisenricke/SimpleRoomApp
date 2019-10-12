package com.luisenricke.simpleroomapp.data.cache

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.luisenricke.simpleroomapp.data.BaseDAO
import com.luisenricke.simpleroomapp.data.DatabaseScheme

@Dao
abstract class CacheDAO : BaseDAO<Cache> {

    @Query("DELETE FROM ${DatabaseScheme.Cache.TABLE}")
    abstract override fun drop()

    //Specific queries for the table
    @Query("SELECT count(*) FROM ${DatabaseScheme.Cache.TABLE}")
    abstract fun count(): Int

    @Query("SELECT * FROM ${DatabaseScheme.Cache.TABLE} ORDER BY ${DatabaseScheme.Cache.ID} LIMIT 1")
    abstract fun get(): Cache?

    @Transaction
    open fun push(row: Cache) {
        insert(row)
    }

    @Transaction
    open fun pop(): Cache? {
        val image: Cache? =  get()
        drop()
        return image
    }
}