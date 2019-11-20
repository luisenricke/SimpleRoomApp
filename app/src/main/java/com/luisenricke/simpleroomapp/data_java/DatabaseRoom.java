package com.luisenricke.simpleroomapp.data_java;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.luisenricke.simpleroomapp.data_java.converter.DateConverter;
import com.luisenricke.simpleroomapp.data_java.entity.Medicine;
import com.luisenricke.simpleroomapp.data_java.dao.MedicineDAO;
import com.luisenricke.simpleroomapp.data_java.entity.Pet;
import com.luisenricke.simpleroomapp.data_java.dao.PetDAO;
import com.luisenricke.simpleroomapp.data_java.entity.PetMedicineJoin;
import com.luisenricke.simpleroomapp.data_java.dao.PetMedicineJoinDAO;
import com.luisenricke.simpleroomapp.data_java.entity.User;
import com.luisenricke.simpleroomapp.data_java.dao.UserDAO;

@Database(entities = {
        Medicine.class,
        Pet.class,
        PetMedicineJoin.class,
        User.class},
        version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class DatabaseRoom extends RoomDatabase {

    private static final String NAME = "SimpleDatabase.db";
    private static volatile DatabaseRoom INSTANCE;

    public abstract MedicineDAO medicineDAO();

    public abstract PetDAO petDAO();

    public abstract PetMedicineJoinDAO petMedicineJoinDAO();

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
        return Room.databaseBuilder(context, DatabaseRoom.class, NAME)
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