package com.iut.app.android.accidentreference.model;

import androidx.annotation.NonNull;

public class Department {
    public int number;
    public String name;
    public Double latitude;
    public Double longitude;

    public Department(int number, String name, Double latitude, Double longitude) {
        this.number = number;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
