package com.luisenricke.simpleroomapp.data_java.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import static com.luisenricke.simpleroomapp.data_java.entity.DateTime.SCHEMA.*;

@Entity(tableName = TABLE)
public class DateTime {
    public interface SCHEMA {
        String TABLE = "DateTime";
        String ID = "id";
        String DATE = "date";
        String TIME = "time";
        String DATE_TIME = "date_time";
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    private int id;
    @ColumnInfo(name = DATE)
    private Date date;
    @ColumnInfo(name = TIME)
    private Date time;
    @ColumnInfo(name = DATE_TIME)
    private Date dateTime;
    @ColumnInfo(name = CREATED_AT)
    private Date created;
    @ColumnInfo(name = UPDATED_AT)
    private Date updated;

    @Ignore
    public DateTime() {
    }

    @Ignore
    public DateTime(int id, Date date, Date time, Date dateTime) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.dateTime = dateTime;
    }

    public DateTime(Date date, Date time, Date dateTime) {
        this.date = date;
        this.time = time;
        this.dateTime = dateTime;
        long timestamp = System.currentTimeMillis();
        created = new Date(timestamp);
        updated = new Date(timestamp);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != DateTime.class) return false;
        DateTime cast = (DateTime) obj;
        return cast.id == getId()
                && cast.date.equals(getDate())
                && cast.time.equals(getTime())
                && cast.dateTime.equals(getDateTime())
                && cast.created.equals(getCreated())
                && cast.updated.equals(getUpdated());
    }
}
