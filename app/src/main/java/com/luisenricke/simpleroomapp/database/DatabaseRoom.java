package com.luisenricke.simpleroomapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.luisenricke.simpleroomapp.database.dao.User;
import com.luisenricke.simpleroomapp.database.dao.UserDAO;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class DatabaseRoom extends RoomDatabase {

    private static volatile DatabaseRoom INSTANCE;

    public abstract UserDAO userDAO();

    public static DatabaseRoom getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseRoom.class) {
                if (INSTANCE == null) INSTANCE = build(context);
            }
        }

        return INSTANCE;
    }

    private static DatabaseRoom build(Context context) {
        return Room.databaseBuilder(context, DatabaseRoom.class, Schema.NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                    }

                    @Override
                    public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                        super.onDestructiveMigration(db);
                    }
                })
                .allowMainThreadQueries() // Uncomment if threads available in MainThread
                .build();
    }

    public void close() {
        if (INSTANCE == null) return;
        if (INSTANCE.isOpen()) INSTANCE.close();
        INSTANCE = null;
    }
}