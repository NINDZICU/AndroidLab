package com.example.khlopunov.cityweatherdb.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.khlopunov.cityweatherdb.entities.City;
import com.example.khlopunov.cityweatherdb.providers.MyCityProvider;

import static com.example.khlopunov.cityweatherdb.tables.CityContract.CityEntry.COLUMN_CITY_NAME;
import static com.example.khlopunov.cityweatherdb.tables.CityContract.CityEntry.COLUMN_DEGREES;
import static com.example.khlopunov.cityweatherdb.tables.CityContract.CityEntry._ID;

/**
 * Created by Anatoly on 12.04.2017.
 */

public class CityContract {
    public static final String TABLE_NAME = "cities";

    public void createTable(@NonNull SQLiteDatabase database) {
        TableBuilder.create(TABLE_NAME)
                .intColumn(_ID)
                .textColumn(COLUMN_CITY_NAME)
                .textColumn(COLUMN_DEGREES)
                .primaryKey(COLUMN_CITY_NAME)
                .execute(database);
    }

    public static ContentValues toContentValues(@NonNull City city){
        ContentValues values = new ContentValues();
        values.put(CityEntry._ID, city.getId());
        values.put(CityEntry.COLUMN_CITY_NAME, city.getName());
        values.put(CityEntry.COLUMN_DEGREES, city.getDegrees());
        return values;
    }

    public static City fromCursor(@Nullable Cursor cursor){
        if(cursor== null){
            return null;
        }
        int cityId = cursor.getColumnIndex(_ID);
        int cityName = cursor.getColumnIndex(CityEntry.COLUMN_CITY_NAME);
        int cityDegrees = cursor.getColumnIndex(CityEntry.COLUMN_DEGREES);
        int id = cursor.getInt(cityId);
        String name = cursor.getString(cityName);
        int degrees = cursor.getInt(cityDegrees);
        City mCity = new City(id,name, degrees);
        return mCity;
    }
    public static Uri getBaseUri(){
        return MyCityProvider.baseUri.buildUpon().appendPath(TABLE_NAME).build();
    }


    public class CityEntry implements BaseColumns {
        public static final String COLUMN_CITY_NAME = "city";
        public static final String COLUMN_DEGREES = "degrees";
        public static final String _ID = BaseColumns._ID;
    }
}
