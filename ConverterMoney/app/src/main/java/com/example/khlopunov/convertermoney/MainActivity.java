package com.example.khlopunov.convertermoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khlopunov.convertermoney.POJO.Rates;
import com.example.khlopunov.convertermoney.fragments.LoaderRateFragment;
import com.example.khlopunov.convertermoney.interfaces.TaskInterface;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskInterface {

    private TextView tvRate;
    private Button btnConvert;
    private ProgressBar progressBar;
    private EditText etDate;
    private TextView tv_error;
    private Spinner spinner1;
    private Spinner spinner2;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRate = (TextView) findViewById(R.id.tv_rate);
        btnConvert = (Button) findViewById(R.id.btn_convert);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        etDate = (EditText) findViewById(R.id.et_date);
        tv_error = (TextView) findViewById(R.id.tv_error);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

//        if (getAsyncFragment().isRunning()) {
//            progressBar.setVisibility(View.VISIBLE);
//        } else progressBar.setVisibility(View.GONE);
        List<String> values =new ArrayList<>();
        for(Field field:Rates.class.getDeclaredFields()){
            values.add(field.getName());
        }
        values.remove(values.size()-1);
        values.remove(values.size()-1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_error.setVisibility(View.GONE);
                String date = etDate.getText().toString();
                if (date.matches("((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])")) {
                    String currency1 = spinner1.getSelectedItem().toString();
                    String currency2 = spinner2.getSelectedItem().toString();
                    getAsyncFragment().getRate(date,currency1,currency2);
                } else {
                    tv_error.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private LoaderRateFragment getAsyncFragment() {
        LoaderRateFragment fragment = (LoaderRateFragment) getSupportFragmentManager().findFragmentByTag(LoaderRateFragment.class.getName());
        if (fragment == null) {
            fragment = new LoaderRateFragment();
            getSupportFragmentManager().beginTransaction().add(fragment, LoaderRateFragment.class.getName()).commit();
        }
        return fragment;
    }

    @Override
    public void onTaskFinish(String rate) {
        getAsyncFragment().stopAsync();
        progressBar.setVisibility(View.GONE);
        tvRate.setText(rate);
        Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTaskStarted() {
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Start!!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTaskProgress() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("rate", tvRate.getText().toString());
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvRate.setText(savedInstanceState.getString("rate"));
    }
}
