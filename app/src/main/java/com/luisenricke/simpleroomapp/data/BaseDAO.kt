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

    interface ComplementaryOperations<Y> {
        /**
         * Count rows from the table.
         * @return the total number of rows.
         */
        fun count(): Int

        /**
         * Get an list of objects existing in the table.
         * @return list of objects requested.
         */
        fun get(): List<Y>

        /**
         * Drop all rows existing in the table.
         */
        fun drop()
    }

    interface AdditionalOperationWithID<Z> {
        /**
         * Get an object with ID existing in the table.
         * @param id of the row requested.
         * @return row the object to be requested.
         */
        fun filterId(id: Long): Z

        /**
         * Update an row with an existing ID in the table and change the data.
         * @param row the object to be updated.
         */
        @Update
        fun update(row: Z)

        /**
         * Delete row with ID existing in the table.
         * @param id of the row deleted.
         */
        fun delete(id: Long)
    }

    /*
        // Some great implementations
        @Transaction
        open fun reset(rows: List<Y>){
            drop()
            insert(rows: List<Y>)
        }
    */
}