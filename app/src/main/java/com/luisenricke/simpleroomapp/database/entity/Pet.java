package com.luisenricke.simpleroomapp.database.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static com.luisenricke.simpleroomapp.database.entity.Pet.COLUMN.*;

@Entity(tableName = TABLE,
        foreignKeys = {@ForeignKey(
                entity = User.class,
                parentColumns = User.COLUMN.ID,
                childColumns = USER,
                onDelete = ForeignKey.CASCADE
        )}
)
public class Pet {
    public interface COLUMN {
        String TABLE = "Pet";
        String ID = "id";
        String NAME = "name";
        String BORN_DAY = "born";
        String USER = "user_id";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = NAME)
    private String nombre;
    @ColumnInfo(name = BORN_DAY)
    private String nacimiento;
    @ColumnInfo(name = USER)
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


