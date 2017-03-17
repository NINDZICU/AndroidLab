package com.example.khlopunov.mvpcontactlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.khlopunov.mvpcontactlist.entities.Contact;
import com.example.khlopunov.mvpcontactlist.presenters.EditPresenter;
import com.example.khlopunov.mvpcontactlist.repositories.ContactsProvider;

public class EditActivity extends AppCompatActivity implements EditInterface {
    private EditText etName;
    private EditText etSurname;
    private EditText etMail;
    private ImageButton btnName;
    private ImageButton btnSurname;
    private ImageButton btnMail;
    private EditPresenter editPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etName = (EditText) findViewById(R.id.et_name);
        etSurname = (EditText) findViewById(R.id.et_surname);
        etMail = (EditText) findViewById(R.id.et_mail);
        btnName = (ImageButton) findViewById(R.id.btn_save_name);
        btnSurname = (ImageButton) findViewById(R.id.btn_save_surname);
        btnMail = (ImageButton) findViewById(R.id.btn_save_mail);
        editPresenter = new EditPresenter(this);
        final Contact contact = getIntent().getParcelableExtra("contact");
        final int position = getIntent().getIntExtra("position", 0);
        setDataContact(contact);


        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPresenter.onClickSaveName(contact, position);
            }
        });
        btnSurname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPresenter.onClickSaveSurname(contact, position);
            }
        });
        btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPresenter.onClickSaveMail(contact, position);
            }
        });
    }

    @Override
    public String getName() {
        return etName.getText().toString();
    }

    @Override
    public String getSurname() {
        return etSurname.getText().toString();
    }

    @Override
    public String getMail() {
        return etMail.getText().toString();
    }

    @Override
    public void setDataContact(Contact contact) {
        Log.d("name",contact.getName());
        etName.setText(contact.getName());
        etSurname.setText(contact.getSurname());
        etMail.setText(contact.getMail());
        // notifyAll();
    }

    @Override
    public void saveData(Contact contact, int position) {
        ContactsProvider.getInstance(this).setContacts(contact, position);
//        notifyAll();
    }

}
