package com.luisenricke.simpleroomapp.data.image

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.luisenricke.simpleroomapp.data.BaseDAO
import com.luisenricke.simpleroomapp.data.DatabaseScheme

@Dao
abstract class ImageDAO : BaseDAO<Image>,
    BaseDAO.ComplementaryOperations<Image>,
    BaseDAO.AdditionalOperationWithID<Image> {

    @Query("SELECT count(*) FROM ${DatabaseScheme.Image.TABLE}")
    abstract override fun count(): Int

    @Query("SELECT * FROM ${DatabaseScheme.Image.TABLE} WHERE ${DatabaseScheme.Image.ID} == :id")
    abstract override fun filterId(id: Long): Image

    @Query("SELECT * FROM ${DatabaseScheme.Image.TABLE}")
    abstract override fun get(): List<Image>

    @Query("DELETE FROM ${DatabaseScheme.Image.TABLE} WHERE ${DatabaseScheme.Image.ID} == :id")
    abstract override fun delete(id: Long)

    @Query("DELETE FROM ${DatabaseScheme.Image.TABLE}")
    abstract override fun drop()

    @Transaction
    open fun reset(rows: List<Image>) {
        drop()
        inserts(rows)
    }

    //Specific queries for the table
}