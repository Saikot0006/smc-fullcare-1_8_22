package com.gs.smc_mmc.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "location")
public class LocationModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private long id;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "latitude")
    private double latitude;
    @ColumnInfo(name = "longitude")
    private double longitude;


    public LocationModel(String date, double latitude, double longitude) {
        this.id = id;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
