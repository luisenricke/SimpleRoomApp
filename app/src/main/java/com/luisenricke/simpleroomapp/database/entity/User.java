package com.luisenricke.simpleroomapp.database.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.luisenricke.simpleroomapp.database.Schema;

@Entity(tableName = Schema.User.TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Schema.User.ID)
    private int id;
    @ColumnInfo(name = Schema.User.EMAIL)
    private String correo;
    @ColumnInfo(name = Schema.User.PASSWORD)
    private String password;

    @Ignore
    public User() {
    }

    @Ignore
    public User(int id, String correo, String password) {
        this.id = id;
        this.correo = correo;
        this.password = password;
    }

    public User(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != User.class) return false;
        User cast = (User) obj;
        return cast.id == getId() && cast.correo.equals(getCorreo()) && cast.password.equals(getPassword());
    }
}
