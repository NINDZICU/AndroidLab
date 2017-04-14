package com.example.khlopunov.cityweatherdb.entities;

/**
 * Created by Anatoly on 12.04.2017.
 */

public class City {
    private int id;
    private String name;
    private int degrees;

    public City(String name, int degrees) {
        this.name = name;
        this.degrees = degrees;
    }

    public City(int id, String name, int degrees) {
        this.id = id;
        this.name = name;
        this.degrees = degrees;
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

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }
}
