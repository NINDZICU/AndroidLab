package com.example.khlopunov.cityweatherdb.retrofit;

import com.example.khlopunov.cityweatherdb.models.WeatherPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Anatoly on 13.04.2017.
 */

public interface OpenWeatherMap {
    String API_KEY ="1d3d1ee8dd65190c320269c3713e4ecc";

    @GET("data/2.5/weather")
    Call<WeatherPojo> getWeather(@Query("q") String city, @Query("appid") String id);

    @GET("http://api.openweathermap.org/data/2.5/group")
    Call<WeatherPojo> updateWeather(@Query("id") String idCities,@Query("appid") String id);
}
