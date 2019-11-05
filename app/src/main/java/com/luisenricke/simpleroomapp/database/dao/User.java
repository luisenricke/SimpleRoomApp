package com.luisenricke.simpleroomapp.database.dao;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.luisenricke.simpleroomapp.database.Schema;

@Entity(tableName = Schema.User.TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String correo;
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
}
