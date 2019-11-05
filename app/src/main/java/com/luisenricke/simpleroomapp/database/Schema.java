package com.luisenricke.simpleroomapp.database;

public interface Schema {
    String NAME = "SimpleDatabase.db";

    interface Query{
        String SELECT_COUNT = "SELECT COUNT(*) FROM ";
        String SELECT_ALL = "SELECT * FROM ";
    }
    interface User{
        String TABLE = "User";
    }

}
