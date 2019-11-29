package com.luisenricke.simpleroomapp.data_kotlin.image

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.luisenricke.simpleroomapp.data_kotlin.BaseDAO
import com.luisenricke.simpleroomapp.data_kotlin.contact.Contact
import com.luisenricke.simpleroomapp.data_kotlin.image.Image.SCHEMA

@Dao
abstract class ImageDAO : BaseDAO<Image>,
    BaseDAO.UpdateDAO<Image>,
    BaseDAO.DeleteDAO<Image>,
    BaseDAO.OperationsPrimaryKeyDAO<Image> ,
BaseDAO.OperationsForeignKeyDAO<Image,Image>{

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")
    abstract override fun count(): Int

    @Query("SELECT * FROM ${SCHEMA.TABLE}")
    abstract override fun get(): List<Image>

    @Query("DELETE FROM ${SCHEMA.TABLE}")
    abstract override fun drop()

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun get(id: Int): Image

    @Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")
    abstract override fun get(ids: LongArray): List<Image>

    @Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")
    abstract override fun delete(id: Int): Int

    @Transaction
    open fun reset(rows: List<Image>) {
        drop()
        inserts(rows)
    }

    /*
    //TODO: Terminar los querys
   abstract override fun countChild(fk: Int): Long

    @Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE} AS CHILD"
            + "INNER JOIN ${Z.SCHEMA.TABLE} AS PARENT"
            + " ON CHILD.parent_id = PARENT.id"
            + " WHERE CHILD.parent_id = :fk")
   abstract override fun countChildJoin(fk: Int): Long

   abstract override fun getChildJoin(): List<Image>?

   abstract override fun getChild(fk: Int): List<Image>?

   abstract override fun getChildJoin(fk: Int): List<Image>?

   abstract override fun drop(fk: Int)

 */
}