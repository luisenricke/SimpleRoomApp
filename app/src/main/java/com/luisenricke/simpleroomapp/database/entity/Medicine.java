package com.luisenricke.simpleroomapp.database.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static com.luisenricke.simpleroomapp.database.entity.Medicine.COLUMN.*;

@Entity(tableName = TABLE)
public class Medicine {
    public interface COLUMN {
        String TABLE = "Medicine";
        String ID = "id";
        String NAME = "name";
        String DOSE = "dose";
        String APPLICATION_METHOD = "application_method";
        String PRODUCER = "producer";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = NAME)
    private String nombre;
    @ColumnInfo(name = DOSE)
    private int cantidad;
    @ColumnInfo(name = APPLICATION_METHOD)
    private String aplicacion;
    @ColumnInfo(name = PRODUCER)
    private String fabricante;

    @Ignore
    public Medicine() {
    }

    @Ignore
    public Medicine(int id, String nombre, int cantidad, String aplicacion, String fabricante) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.aplicacion = aplicacion;
        this.fabricante = fabricante;
    }

    public Medicine(String nombre, int cantidad, String aplicacion, String fabricante) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.aplicacion = aplicacion;
        this.fabricante = fabricante;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != Medicine.class) return false;
        Medicine cast = (Medicine) obj;
        return cast.id == getId() && cast.nombre.equals(getNombre()) && cast.cantidad == getCantidad() && cast.aplicacion.equals(getAplicacion()) && cast.fabricante.equals(getFabricante());
    }
}
