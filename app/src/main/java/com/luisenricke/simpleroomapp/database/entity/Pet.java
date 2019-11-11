package com.luisenricke.simpleroomapp.database.pet;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.luisenricke.simpleroomapp.database.Schema;
import com.luisenricke.simpleroomapp.database.user.User;

@Entity(tableName = Schema.Pet.TABLE,
        foreignKeys = {@ForeignKey(
                entity = User.class,
                parentColumns = Schema.User.ID,
                childColumns = Schema.Pet.USER,
                onDelete = ForeignKey.CASCADE
        )}
)
public class Pet {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Schema.Pet.ID)
    private int id;
    @ColumnInfo(name = Schema.Pet.NOMBRE)
    private String nombre;
    @ColumnInfo(name = Schema.Pet.NACIMIENTO)
    private String nacimiento;
    @ColumnInfo(name = Schema.Pet.USER)
    private int userId;

    @Ignore
    public Pet() {
    }

    @Ignore
    public Pet(int id, String nombre, String nacimiento, int userId) {
        this.id = id;
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.userId = userId;
    }

    @Ignore
    public Pet(String nombre, String nacimiento) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
    }

    public Pet(String nombre, String nacimiento, int userId) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != Pet.class) return false;
        Pet cast = (Pet) obj;
        return cast.id == getId() && cast.nombre.equals(getNombre()) && cast.nacimiento.equals(getNacimiento()) && cast.userId == getUserId();
    }
}
