package com.example.khlopunov.cityweatherdb;

import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.khlopunov.cityweatherdb.adapters.RecyclerCitiesAdapter;
import com.example.khlopunov.cityweatherdb.data.CityOpenHelper;
import com.example.khlopunov.cityweatherdb.entities.City;
import com.example.khlopunov.cityweatherdb.services.CitiesService;
import com.example.khlopunov.cityweatherdb.tables.CityContract;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etNameCity;
    private Button btnAddCity;
    private Button btnRefresh;
    private RecyclerView rv;

    private RecyclerCitiesAdapter adapter;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private final ContentObserver newObserver = new ContentObserver(mHandler) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            cityDataChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNameCity = (EditText) findViewById(R.id.et_add_city);
        btnAddCity = (Button) findViewById(R.id.btn_add);
        btnRefresh = (Button) findViewById(R.id.btn_update);
        rv = (RecyclerView) findViewById(R.id.rv_cities);

        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        adapter = new RecyclerCitiesAdapter(MainActivity.this);
        rv.setAdapter(adapter);

        btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNameCity.getText().length() != 0) {
                    CitiesService.start(MainActivity.this, etNameCity.getText().toString());
                    etNameCity.setText("");
                }
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CitiesService.startUpdate(MainActivity.this);
                adapter.onUpdate();
            }
        });

        CityOpenHelper helper = new CityOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        System.out.println(db);

        getContentResolver().registerContentObserver(CityContract.getBaseUri(), false, newObserver);
    }

    void cityDataChanged() {
        adapter.onUpdate();
    }
}
