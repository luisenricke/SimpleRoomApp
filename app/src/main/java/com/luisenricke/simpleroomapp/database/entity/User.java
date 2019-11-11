package com.luisenricke.simpleroomapp.database.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static com.luisenricke.simpleroomapp.database.entity.User.COLUMN.*;

@Entity(tableName = TABLE)
public class User {
    public interface COLUMN {
        String TABLE = "User";
        String ID = "id";
        String EMAIL = "email";
        String PASSWORD = "password";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = EMAIL)
    private String email;
    @ColumnInfo(name = PASSWORD)
    private String password;

    @Ignore
    public User() {
    }

    @Ignore
    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return cast.id == getId() && cast.email.equals(getEmail()) && cast.password.equals(getPassword());
    }
}
