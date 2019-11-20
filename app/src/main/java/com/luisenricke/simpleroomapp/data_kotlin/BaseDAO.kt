package com.luisenricke.simpleroomapp.data_kotlin

import androidx.room.*

interface BaseDAO<X> {
    /**
     * Insert an row in the table.
     *
     * @param row: the object to be inserted.
     * @return id of the row inserted.
     */
    @Insert
    fun insert(row: X): Long

    /**
     * Insert an vararg of objects in the table.
     *
     * @param rows: the objects to be inserted.
     * @return the list of id's of the rows inserted.
     */
    @Insert
    fun inserts(vararg rows: X): List<Long>

    /**
     * Insert a list of objects in the table.
     *
     * @param rows: the objects to be inserted.
     * @return the list of id's of the rows inserted.
     */
    @Insert
    fun inserts(rows: List<X>): List<Long>

    /**
     * Count rows from the table.
     *
     * ``@Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE}")``
     *
     * @return the total number of rows.
     */
    fun count(): Int

    /**
     * Get a list of objects existing in the table.
     *
     * ``@Query("SELECT * FROM ${SCHEMA.TABLE}")``
     *
     * @return list of objects requested.
     */
    fun get(): List<X>

    /**
     * Drop all rows existing in the table.
     *
     * ``@Query("DELETE FROM ${SCHEMA.TABLE}")``
     */
    fun drop()

    interface UpdateDAO<Y> {
        /**
         * Update a row with an existing ID in the table and change the data.
         *
         * @param row: the object to be updated.
         * @return the total number of rows changed.
         */
        @Update
        fun update(row: Y): Int

        /**
         * Update a vararg of rows with an existing ID in the table and change the data.
         *
         * @param rows: the objects to be updated.
         * @return the total number of rows changed.
         */
        @Update
        fun updates(vararg rows: Y): Int

        /**
         * Update a list of rows with an existing ID in the table and change the data.
         *
         * @param rows: the objects to be updated.
         * @return the total number of rows changed.
         */
        @Update
        fun updates(rows: List<Y>): Int
    }

    interface DeleteDAO<Y> {
        /**
         * Delete an row from the table.
         *
         * @param row: the object to be deleted.
         * @return the total number of rows dropped.
         */
        @Delete
        fun delete(row: Y): Int

        /**
         * Delete a vararg of rows from the table.
         *
         * @param rows: the objects to be deleted.
         * @return the total number of rows dropped.
         */
        @Delete
        fun deletes(vararg rows: Y): Int

        /**
         * Delete a list of rows from the table.
         *
         * @param rows: the objects to be deleted.
         * @return the total number of rows dropped.
         */
        @Delete
        fun deletes(rows: List<Y>): Int
    }

    interface OperationsPrimaryKeyDAO<Y> {

        /**
         * Get a object existing in the table by ID.
         *
         * ``@Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id = :id")``
         *
         * @param id: the id of the searched object.
         * @return the object requested.
         */
        fun getById(id: Int): Y

        /**
         * Get a list of object existing in the table by ID's.
         *
         *  ``@Query("SELECT * FROM ${SCHEMA.TABLE} WHERE id IN(:ids)")``
         *
         * @param ids: the list id's of the searched objects.
         * @return the list objects requested.
         *
         */
        fun getByIds(ids: LongArray): List<Y>

        /**
         * Delete a row existing in the table by ID.
         *
         * ``@Query("DELETE FROM ${SCHEMA.TABLE} WHERE id = :id")``
         *
         * @param id: the id of the searched object.
         * @return the total number of rows dropped.
         */
        fun deleteById(id: Int): Int
    }

    interface OperationsForeignKeyDAO<Y> {
        /**
         * Count rows from the table by ID of the reference.
         *
         * ``@Query("SELECT COUNT(*) FROM ${SCHEMA.TABLE} WHERE parent_id = :foreignKeyValue")``
         *
         * @param foreignKeyValue: the id of the reference table.
         * @return the total number of rows.
         */
        fun countByReference(foreignKeyValue: Int): Long

        /**
         * Get a list of objects existing in the table by ID of the reference.
         *
         * ``@Query("SELECT * FROM ${SCHEMA.TABLE} WHERE parent_id = :foreignKeyValue")``
         *
         * @param foreignKeyValue: the id of the reference table.
         * @return the list of objects requested.
         */
        fun getByReference(foreignKeyValue: Int): List<Y>

        /**
         * Drop all rows existing in the table by ID of the reference.
         *
         * ``@Query("DELETE FROM ${SCHEMA.TABLE} WHERE parent_id = :foreignKeyValue")``
         *
         * @param foreignKeyValue: the id of the reference table.
         */
        fun dropByReference(foreignKeyValue: Int)
    }

    interface InnerJoinDAO<A, B> {
        /**
         * Get a list of objects in the left table existing in the right table.
         *
         * ``@Query("SELECT * FROM  TABLEA " +
         *
         * "INNER JOIN ${SCHEMA.TABLE}  " +
         *
         * "ON TABLEA.id = ${SCHEMA.TABLE}.TABLEA_id " +
         *
         * "WHERE ${SCHEMA.TABLE}.TABLEB_id = :idRight")``
         *
         * @param idRight: the id of the right table.
         * @return the list of objects matches.
         */

        fun getLeftJoinRight(idRight: Int): List<A>

        /**
         * Get a list of objects in the right table existing in the left table.
         *
         * ``@Query("SELECT * FROM  TABLEB " +
         *
         * "INNER JOIN ${SCHEMA.TABLE}  " +
         *
         * "ON TABLEB.id = ${SCHEMA.TABLE}.TABLEB_id " +
         *
         * "WHERE ${SCHEMA.TABLE}.TABLEA_id = :idLeft")``
         *
         * @param idLeft: the id of the right table.
         * @return the list of objects matches.
         */
        fun getRightJoinLeft(idLeft: Int): List<B>
    }
}