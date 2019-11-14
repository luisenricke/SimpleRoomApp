package com.luisenricke.simpleroomapp.database.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static com.luisenricke.simpleroomapp.database.entity.Medicine.SCHEMA.*;

@Entity(tableName = TABLE)
public class Medicine {
    public interface SCHEMA {
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
    private String name;
    @ColumnInfo(name = DOSE)
    private int dose;
    @ColumnInfo(name = APPLICATION_METHOD)
    private String application;
    @ColumnInfo(name = PRODUCER)
    private String producer;

    @Ignore
    public Medicine() {
    }

    @Ignore
    public Medicine(int id, String name, int dose, String application, String producer) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.application = application;
        this.producer = producer;
    }

    public Medicine(String name, int dose, String application, String producer) {
        this.name = name;
        this.dose = dose;
        this.application = application;
        this.producer = producer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != Medicine.class) return false;
        Medicine cast = (Medicine) obj;
        return cast.id == getId() && cast.name.equals(getName()) && cast.dose == getDose() && cast.application.equals(getApplication()) && cast.producer.equals(getProducer());
    }
}
