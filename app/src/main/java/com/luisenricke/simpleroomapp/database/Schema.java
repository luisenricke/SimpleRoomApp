package com.luisenricke.simpleroomapp.database;

public interface Schema {
    String NAME = "SimpleDatabase.db";

    interface User {
        String TABLE = "User";
        String ID = "id";
        String EMAIL = "correo";
        String PASSWORD = "password";
    }

    interface Pet {
        String TABLE = "Pet";
        String ID = "id";
        String NOMBRE = "nombre";
        String NACIMIENTO = "nacimiento";
        String USER = "user_id";
    }

    interface Medicine {
        String TABLE = "Medicine";
        String ID = "id";
        String NOMBRE = "nombre";
        String CANTIDAD = "cantidad";
        String APLICACION = "forma_aplicacion";
        String FABRICANTE = "fabricante";
    }

    interface PetMedicineJoin {
        String TABLE = "PetMedicineJoin";
        String PET = "pet_id";
        String MEDICINE = "medicine_id";
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
    }
}
