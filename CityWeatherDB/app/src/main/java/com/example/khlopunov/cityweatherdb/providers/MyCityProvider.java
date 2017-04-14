package com.example.khlopunov.cityweatherdb.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.khlopunov.cityweatherdb.data.CityOpenHelper;
import com.example.khlopunov.cityweatherdb.tables.CityContract;

/**
 * Created by Anatoly on 13.04.2017.
 */

public class MyCityProvider extends ContentProvider {

    private CityOpenHelper cityOpenHelper;

    public static final int CITIES_URI_KEY = 101;

    public static final String BASE_CONTENT_AUTHORITY = "com.example.khlopunov.cityweatherdb";

    private final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static Uri baseUri;
    @Override
    public boolean onCreate() {
        if(getContext()!=null){
            cityOpenHelper = new CityOpenHelper(getContext());
            baseUri = Uri.parse("content://" + BASE_CONTENT_AUTHORITY);
            uriMatcher.addURI(BASE_CONTENT_AUTHORITY, CityContract.TABLE_NAME, CITIES_URI_KEY);
            return true;
        }
        else {
            return false;
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String tableName = getType(uri);
        Cursor mCursor = cityOpenHelper.getReadableDatabase().query(tableName, projection, selection, selectionArgs, null, null, null);
        return mCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int matchCode = uriMatcher.match(uri);
        switch (matchCode) {
            case CITIES_URI_KEY:
                return CityContract.TABLE_NAME;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String tableName = getType(uri);
        long id = cityOpenHelper.getWritableDatabase().insert(tableName, null, values);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getType(uri);
        int count = cityOpenHelper.getWritableDatabase().delete(tableName, selection, selectionArgs );
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getType(uri);
        return cityOpenHelper.getWritableDatabase().update(tableName, values,selection, selectionArgs);
    }
}
