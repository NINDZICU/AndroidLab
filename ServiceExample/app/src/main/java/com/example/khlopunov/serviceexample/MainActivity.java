package com.example.khlopunov.serviceexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.khlopunov.serviceexample.services.MyIntentService;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    public static final String MY_ACTION = "com.example.khlopunov.serviceexample.MY_ACTION";
    public static final String ERROR_ACTION = "com.example.khlopunov.serviceexample.ERROR_ACTION";

    private RecyclerView rv;
    private MyBroadcast broadcast;
    private ErrorBroadcast errorBroadcast;
    private Button btnRefresh;
    private ContactRecyclerAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcast = new MyBroadcast();
        errorBroadcast = new ErrorBroadcast();
        btnRefresh = (Button) findViewById(R.id.btn_refresh);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        rv = (RecyclerView) findViewById(R.id.rv_contacts);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        adapter = new ContactRecyclerAdapter(MainActivity.this);
        rv.setAdapter(adapter);

        registerReceiver(broadcast, new IntentFilter(MY_ACTION));
        registerReceiver(errorBroadcast, new IntentFilter(ERROR_ACTION));

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyIntentService.class);
                rv.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                startService(intent);
            }
        });

    }


    public class MyBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            rv.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            adapter.refresh();
        }
    }
    public class ErrorBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            btnRefresh.callOnClick();
        }
    }
}
