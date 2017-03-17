package com.example.khlopunov.mvpcontactlist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private RecyclerView rv;
    private MyBroadcast myBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myBroadcast = new MyBroadcast();

        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv_contacts);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        RecyclerContactAdapter adapter = new RecyclerContactAdapter(MainActivity.this);
        rv.setAdapter(adapter);

        registerReceiver(myBroadcast, new IntentFilter(MY_ACTION));

    }

    void handleIntent(){
        sendBroadcast(new Intent(MY_ACTION));
    }


    public class MyBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "GREETINGS", Toast.LENGTH_LONG).show();
        }
    }
}
