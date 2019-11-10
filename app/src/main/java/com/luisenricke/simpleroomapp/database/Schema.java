package com.luisenricke.simpleroomapp.database;

public interface Schema {
    String NAME = "SimpleDatabase.db";

    interface User{
        String TABLE = "User";
        String ID = "id";
        String EMAIL = "correo";
        String PASSWORD = "password";
    }

    interface Pet{
        String TABLE = "Pet";
        String ID = "id";
        String NOMBRE = "nombre";
        String NACIMIENTO = "nacimiento";
        String USER = "user_id";
    }
}
