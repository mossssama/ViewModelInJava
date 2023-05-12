package com.example.viewmodelexample.Stop;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "stops")
public class Stop {

    @NonNull
    @PrimaryKey
    private String stopType;
    private double lat;
    private double lng;

    public Stop(String stopType, double lat, double lng) {
        this.stopType = stopType;
        this.lat = lat;
        this.lng = lng;
    }

    public String getStopType() {
        return stopType;
    }
    public void setStopType(String stopType) {
        this.stopType = stopType;
    }

    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
}
