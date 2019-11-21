package com.luisenricke.simpleroomapp.data_java;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.List;

public interface BaseDAO<X> {
    /**
     * Insert an row in the table.
     *
     * @param row: the object to be inserted.
     * @return id of the row inserted.
     */
    @Insert
    long insert(X row);

    /**
     * Insert an vararg of objects in the table.
     *
     * @param rows: the objects to be inserted.
     * @return the list of id's of the rows inserted.
     */
    @Insert
    List<Long> inserts(X... rows);

    /**
     * Insert a list of objects in the table.
     *
     * @param rows: the objects to be inserted.
     * @return the list of id's of the rows inserted.
     */
    @Insert
    List<Long> inserts(List<X> rows);

    /**
     * Count rows from the table.
     * <p>@Query("SELECT COUNT(*) FROM " + SCHEMA.TABLE)</p>
     *
     * @return the total number of rows.
     */
    int count();

    /**
     * Get a list of objects existing in the table.
     * <p>@Query("SELECT * FROM " + SCHEMA.TABLE)</p>
     *
     * @return list of objects requested.
     */
    List<X> get();

    /**
     * Drop all rows existing in the table.
     * <p>@Query("DELETE FROM " + SCHEMA.TABLE)</p>
     */
    void drop();

    interface UpdateDAO<Y> {
        /**
         * Update a row with an existing ID in the table and change the data.
         *
         * @param row: the object to be updated.
         * @return the total number of rows changed.
         */
        @Update
        int update(Y row);

        /**
         * Update a vararg of rows with an existing ID in the table and change the data.
         *
         * @param rows: the objects to be updated.
         * @return the total number of rows changed.
         */
        @Update
        int updates(Y... rows);

        /**
         * Update a list of rows with an existing ID in the table and change the data.
         *
         * @param rows: the objects to be updated.
         * @return the total number of rows changed.
         */
        @Update
        int updates(List<Y> rows);
    }

    interface DeleteDAO<Y> {
        /**
         * Delete an row from the table.
         *
         * @param row: the object to be deleted.
         * @return the total number of rows dropped.
         */
        @Delete
        int delete(Y row);

        /**
         * Delete a vararg of rows from the table.
         *
         * @param rows: the objects to be deleted.
         * @return the total number of rows dropped.
         */
        @Delete
        int deletes(Y... rows);

        /**
         * Delete a list of rows from the table.
         *
         * @param rows: the objects to be deleted.
         * @return the total number of rows dropped.
         */
        @Delete
        int deletes(List<Y> rows);
    }

    interface OperationsPrimaryKeyDAO<Y> {

        /**
         * Get a object existing in the table by ID.
         * <p>@Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id = :id")</p>
         *
         * @param id: the id of the searched object.
         * @return the object requested.
         */
        Y getById(int id);

        /**
         * Get a list of object existing in the table by ID's.
         * <p>@Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE id IN(:ids)")</p>
         *
         * @param ids: the list id's of the searched objects.
         * @return the list objects requested.
         */
        List<Y> getByIds(long[] ids);

        /**
         * Delete a row existing in the table by ID.
         * <p>@Query("DELETE FROM " + SCHEMA.TABLE + " WHERE id = :id")</p>
         *
         * @param id: the id of the searched object.
         * @return the total number of rows dropped.
         */
        int deleteById(int id);
    }

    /**
     * @param <Y> is a child table.
     * @param <Z> is a parent table.
     * @deprecated This interfaces its just a reference query.
     */
    interface OperationsForeignKeyDAO<Y, Z> {
        /**
         * Count rows from the table by ID of the reference.
         * <p>@Query("SELECT COUNT(*) FROM " + SCHEMA.TABLE + " WHERE parent_id = :foreignKeyValue")</p>
         *
         * @param foreignKeyValue: the id of the reference table.
         * @return the total number of rows.
         */
        long countByReference(int foreignKeyValue);

        /**
         * Get a list of objects existing in the table by ID of the reference.
         * <p>@Query("SELECT * FROM " + SCHEMA.TABLE + " WHERE parent_id = :foreignKeyValue")</p>
         *
         * @param foreignKeyValue: the id of the reference table.
         * @return the list of objects requested.
         */
        List<Y> getByReference(int foreignKeyValue);
        // TODO: MAke other two functions with WHERE statement
        /**
         * Count rows from the table Y in Z.
         * <p>@Query("SELECT COUNT(*) FROM " + Y.SCHEMA.TABLE + " AS CHILD" </p>
         * <p>+ " INNER JOIN " + Z.SCHEMA.TABLE + " AS PARENT" </p>
         * <p>+ " ON CHILD.parent_id = PARENT.id ")</p>
         *
         * @return the total number of rows.
         */
        long countListChildByJoin();

        /**
         * Get a list of objects existing in the table Y in Z.
         * <p>@Query("SELECT CHILD.* FROM " + Y.SCHEMA.TABLE + " AS CHILD" </p>
         * <p>+ " INNER JOIN " + Z.SCHEMA.TABLE + " AS PARENT" </p>
         * <p>+ " ON CHILD.parent_id = PARENT.id ")</p>
         *
         * @return the list of objects requested.
         */
        List<Y> getListChildByJoin();

        /**
         * Drop all rows existing in the table by ID of the reference.
         * <p>@Query("DELETE FROM " + SCHEMA.TABLE + " WHERE parent_id = :foreignKeyValue")</p>
         *
         * @param foreignKeyValue: the id of the reference table.
         */
        void dropByReference(int foreignKeyValue);
    }

    interface InnerJoinDAO<A, B> {
        /**
         * Get a list of objects in the left table existing in the right table.
         * <p>@Query("SELECT * FROM  TABLEA " +
         * <p>"INNER JOIN " + SCHEMA.TABLE + " " + </p>
         * <p>"ON TABLEA.id = " + SCHEMA.TABLE + ".TABLEA_id " +</p>
         * <p>"WHERE " + SCHEMA.TABLE + ".TABLEB_id = :idRight")</p>
         *
         * @param idRight: the id of the right table.
         * @return the list of objects matches.
         */
        List<A> getLeftJoinRight(int idRight);

        /**
         * Get a list of objects in the right table existing in the left table.
         * <p>@Query("SELECT * FROM  TABLEB " +
         * <p>"INNER JOIN " + SCHEMA.TABLE + " " + </p>
         * <p>"ON TABLEB.id = " + SCHEMA.TABLE + ".TABLEB_id " +</p>
         * <p>"WHERE " + SCHEMA.TABLE + ".TABLEA_id = :idLeft")</p>
         *
         * @param idLeft: the id of the right table.
         * @return the list of objects matches.
         */
        List<B> getRightJoinLeft(int idLeft);
    }
}