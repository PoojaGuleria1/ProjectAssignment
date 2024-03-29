package com.example.projectassignment.data.models;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable {

    @SerializedName("lat")
    @ColumnInfo(name = "lat")
    private double lat;

    @SerializedName("lng")
    @ColumnInfo(name = "lng")
    private double lng;

    @SerializedName("address")
    @ColumnInfo(name = "address")
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
