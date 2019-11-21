package com.luisenricke.simpleroomapp.data_kotlin.image

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.luisenricke.simpleroomapp.data_kotlin.BaseDAO
import com.luisenricke.simpleroomapp.data_kotlin.image.Image.SCHEMA

@Dao
abstract class ImageDAO : BaseDAO<Image>,
    BaseDAO.UpdateDAO<Image>,
    BaseDAO.DeleteDAO<Image>,
    BaseDAO.OperationsPrimaryKeyDAO<Image> {

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")
    abstract override fun count(): Int

    @Query("SELECT * FROM ${SCHEMA.TABLE}")
    abstract override fun get(): List<Image>

    @Query("DELETE FROM ${SCHEMA.TABLE}")
    abstract override fun drop()

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun getById(id: Int): Image

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun getByIds(ids: LongArray): List<Image>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun deleteById(id: Int): Int

    @Transaction
    open fun reset(rows: List<Image>) {
        drop()
        inserts(rows)
    }
}