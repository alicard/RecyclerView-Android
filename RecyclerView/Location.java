package com.blogcahti.recyclerview;

import com.google.gson.annotations.SerializedName;

// Global Variable Declare Here
public class Location{

    @SerializedName("id") private int Id;
    @SerializedName("name") private String Name;
    @SerializedName("lat") private String Lat;
    @SerializedName("lng") private String Lng;

    // Getter
    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
    public String getLat() {
        return Lat;
    }
    public String getLng() {
        return Lng;
    }

    // Setter]
    public void setLat(String Lat){
        this.Lat = Lat;
    }
    public void setLng(String Lng){
        this.Lng = Lng;
    }
}
