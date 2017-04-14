package com.example.khlopunov.cityweatherdb.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anatoly on 14.04.2017.
 */

public class List {

    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("weather")
    @Expose
    private java.util.List<WeatherEntry> weather = null;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    /**
     *
     * @return
     *     weather
     */
    public java.util.List<WeatherEntry> getWeather() {
        return weather;
    }

    /**
     *
     * @param weather
     *     weather
     */
    public void setWeather(java.util.List<WeatherEntry> weather) {
        this.weather = weather;
    }

    /**
     *
     * @return
     *     main
     */
    public Main getMain() {
        return main;
    }

    /**
     *
     * @param main
     *     main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     name
     */
    public void setName(String name) {
        this.name = name;
    }
}
