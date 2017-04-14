package com.example.khlopunov.cityweatherdb.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.khlopunov.cityweatherdb.entities.City;
import com.example.khlopunov.cityweatherdb.models.WeatherPojo;
import com.example.khlopunov.cityweatherdb.providers.MyCityProvider;
import com.example.khlopunov.cityweatherdb.retrofit.OpenWeatherMap;
import com.example.khlopunov.cityweatherdb.tables.CityContract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Anatoly on 13.04.2017.
 */

public class CitiesService extends IntentService {
    public static final String CITYNAME_KEY = "cityname_key";
    public static final String CITYDEGREES_KEY = "citydegrees_key";

    public static final String CRUD_KEY = "crud_key";
    public static final String ADD_CITY_KEY = "add_city_key";
    public static final String UPDATE_CITY_KEY = "update_city_key";

    public CitiesService() {
        super(CitiesService.class.getSimpleName());
    }

    public static void start(@NonNull Context context, @NonNull String nameCity) {
        Intent mIntent = new Intent(context, CitiesService.class);
        mIntent.putExtra(CRUD_KEY, ADD_CITY_KEY);
        mIntent.putExtra(CITYNAME_KEY, nameCity);
        context.startService(mIntent);
    }

    public static void startUpdate(@NonNull Context context) {
        Intent mIntent = new Intent(context, CitiesService.class);
        mIntent.putExtra(CRUD_KEY, UPDATE_CITY_KEY);
        context.startService(mIntent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String crud = intent.getStringExtra(CRUD_KEY);
        System.out.println("Start Service");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org").addConverterFactory(GsonConverterFactory.create())
                .build();
        OpenWeatherMap openWeatherMap = retrofit.create(OpenWeatherMap.class);
        if (crud.equals(ADD_CITY_KEY)) {
            String name = intent.getStringExtra(CITYNAME_KEY);
            Call<WeatherPojo> weatherCall = openWeatherMap.getWeather(name, OpenWeatherMap.API_KEY);
            int temp = 404;
            String nameCity = null;

            try {
                Response<WeatherPojo> response = weatherCall.execute();
                if (response.errorBody() != null) {                                //обработка ошибки

                } else {
                    WeatherPojo weatherMap = response.body();
                    System.out.println(weatherMap.getName());
                    temp = (int) (weatherMap.getMain().getTemp() - 273);
                    nameCity = weatherMap.getName();
                    int id = weatherMap.getId();

                    City mCity = new City(id, nameCity, temp);
                    getContentResolver().insert(CityContract.getBaseUri(), CityContract.toContentValues(mCity));
                    getContentResolver().notifyChange(CityContract.getBaseUri(), null);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (crud.equals(UPDATE_CITY_KEY)) {
            String citiesId = "";
            List<City> mCities;
            Cursor mCursor = getContentResolver().query(CityContract.getBaseUri(),
                    null, null, null, null);
            if (mCursor != null) {
                mCities = new ArrayList<>();
                while (mCursor.moveToNext()) {
                    City changedCity = CityContract.fromCursor(mCursor);
                    mCities.add(changedCity);
                }
                mCursor.close();
                for (City iterableCity : mCities) {
                    citiesId += iterableCity.getId() + ",";
                }
            }
            if (!citiesId.equals("")) {
                citiesId = citiesId.substring(0, citiesId.length() - 1);
                System.out.println("CITIES ID" + citiesId);
                Call<WeatherPojo> weatherCall = openWeatherMap.updateWeather(citiesId, OpenWeatherMap.API_KEY);

                try {
                    Response<WeatherPojo> response = weatherCall.execute();
                    if (response.errorBody() != null) {                                //обработка ошибки

                    } else {
                        WeatherPojo weatherMap = response.body();
                        for (com.example.khlopunov.cityweatherdb.models.List getCities : weatherMap.getList()) {
                            int degrees = (int) (getCities.getMain().getTemp() - 300);
                            int id = getCities.getId();
                            City mCity = new City(id, getCities.getName(), degrees);
                            getContentResolver().update(CityContract.getBaseUri(), CityContract.toContentValues(mCity),
                                    CityContract.CityEntry._ID + "=?", new String[]{String.valueOf(id)});
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
