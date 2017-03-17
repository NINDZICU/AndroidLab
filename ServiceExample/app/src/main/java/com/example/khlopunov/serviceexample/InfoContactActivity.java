package com.example.khlopunov.serviceexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.khlopunov.serviceexample.R;
import com.example.khlopunov.serviceexample.model.User;

public class InfoContactActivity extends AppCompatActivity {
    private TextView tvName;
    private TextView tvSurname;
    private TextView tvMail;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_contact);

        tvName = (TextView) findViewById(R.id.tv_info_name);
        tvSurname = (TextView) findViewById(R.id.tv_info_surname);
        tvMail = (TextView) findViewById(R.id.tv_info_mail);

        user = (User) getIntent().getSerializableExtra("user");
        tvName.setText(user.getName().getFirst());
        tvSurname.setText(user.getName().getLast());
        tvMail.setText(user.getEmail());

    }

}
