package com.example.khlopunov.cityweatherdb.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.khlopunov.cityweatherdb.tables.CityContract;

/**
 * Created by Anatoly on 12.04.2017.
 */

public class CityOpenHelper extends SQLiteOpenHelper {

    public static final String CITY_DB_NAME ="city.db";
    public static final int CURRENT_VERSION = 1;

    public CityOpenHelper(Context context) {
        super(context, CITY_DB_NAME, null, CURRENT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        new CityContract().createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
