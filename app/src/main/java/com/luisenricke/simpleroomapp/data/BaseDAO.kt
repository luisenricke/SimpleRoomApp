package com.luisenricke.simpleroomapp.data

import androidx.room.*

interface BaseDAO<X> {
    /**
     * Insert an row in the table.
     * @param row the object to be inserted.
     * @return id of the row inserted.
     */
    @Insert
    fun insert(row: X): Long

    /**
     * Insert an vararg of objects in the table.
     * @param row the objects to be inserted.
     */
    @Insert
    fun inserts(vararg row: X)

    /**
     * Insert an list of objects in the table.
     * @param rows the objects to be inserted.
     */
    @Insert
    fun inserts(rows: List<X>)

    /**
     * Delete an row from the table.
     * @param row the object to be deleted.
     */
    @Delete
    fun delete(row: X)

    /**
     * Count rows from the table.
     * @return the total number of rows.
     * @see @Query("SELECT count(*) FROM ${DatabaseScheme.name.TABLE}")
     */
    fun count(): Int

    interface ComplementaryOperationsWithList<Y> {
        /**
         * Get an list of objects existing in the table.
         * @return list of objects requested.
         * @see @Query("SELECT * FROM ${DatabaseScheme.name.TABLE}")
         */
        fun get(): List<Y>

        /**
         * Drop all rows existing in the table.
         * @see @Query("DELETE FROM ${DatabaseScheme.name.TABLE}")
         */
        fun drop()
    }

    interface AdditionalOperationWithID<Z> {
        /**
         * Update an row with an existing ID in the table and change the data.
         * @param row the object to be updated.
         */
        @Update
        fun update(row: Z)

        /**
         * Get an object with ID existing in the table.
         * @param id of the row requested.
         * @return row the object to be requested.
         * @see @Query("SELECT * FROM ${DatabaseScheme.name.TABLE} WHERE ${DatabaseScheme.name.ID} == :id")
         */
        fun filterId(id: Long): Z

        /**
         * Delete row with ID existing in the table.
         * @param id of the row deleted.
         * @see @Query("DELETE FROM ${DatabaseScheme.name.TABLE} WHERE ${DatabaseScheme.name.ID} == :id")
         */
        fun delete(id: Long)
    }
}