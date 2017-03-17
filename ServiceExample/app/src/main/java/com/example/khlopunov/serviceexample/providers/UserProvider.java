package com.example.khlopunov.serviceexample.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.khlopunov.serviceexample.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anatoly on 18.03.2017.
 */

public class UserProvider {
    public static final String USER_PREFERENCES = "UsersList";
    public static final String PREFERENCES_NAME = "UserName";

    private static UserProvider sInstance;
    private Context context;

    public static UserProvider getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new UserProvider(context.getApplicationContext());
        }
        return sInstance;
    }

    public UserProvider(Context context) {
        this.context = context;
    }

    public List<User> getContacts() {
        SharedPreferences preferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
        if (preferences.contains(PREFERENCES_NAME)) {
            Gson gson = new Gson();
            String jsonText = preferences.getString(PREFERENCES_NAME, "");
            Type listType = new TypeToken<List<User>>() {
            }.getType();
            List<User> users = gson.fromJson(jsonText, listType);
            return users;
        } else {
            List<User> users = new ArrayList<>();
            saveUsers(users);
            return users;
        }
    }

    public void saveUsers(List<User> users) {
        SharedPreferences preferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<User>>() {
        }.getType();
        String jsonText = gson.toJson(users, listType);
        editor.putString(PREFERENCES_NAME, jsonText);
        editor.commit();
    }
}
