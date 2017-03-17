package com.example.khlopunov.serviceexample.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.khlopunov.serviceexample.MainActivity;
import com.example.khlopunov.serviceexample.model.Example;
import com.example.khlopunov.serviceexample.model.User;
import com.example.khlopunov.serviceexample.providers.UserProvider;

import java.io.IOException;
import java.util.List;

import interfaces.RandomUserApi;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Anatoly on 17.03.2017.
 */

public class MyIntentService extends IntentService {
    private List<User> users = null;
    private MainActivity.MyBroadcast broadcast;


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("Start", "Start!!!!!");
        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://randomuser.me/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Call<Example> exampleCall = null;
            RandomUserApi randomUserApi = retrofit.create(RandomUserApi.class);
            exampleCall = randomUserApi.getUsers(RandomUserApi.RESULTS);
            try {
                Response<Example> response = exampleCall.execute();
                Example example = response.body();
                users = example.getResults();
                UserProvider.getInstance(this).saveUsers(users);
                Log.d("success", "SUCCESS");
                handleIntent();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            System.out.println("Internet Connection Error");
            sendBroadcast(new Intent(MainActivity.ERROR_ACTION));
        }

    }
    void handleIntent() {
        sendBroadcast(new Intent(MainActivity.MY_ACTION));
    }
}
