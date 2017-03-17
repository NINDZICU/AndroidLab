package com.example.khlopunov.mvpcontactlist.presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.example.khlopunov.mvpcontactlist.entities.Contact;
import com.example.khlopunov.mvpcontactlist.ContactListView;
import com.example.khlopunov.mvpcontactlist.EditActivity;

/**
 * Created by Anatoly on 15.03.2017.
 */

public class MainPresenter {
    private ContactListView contactListView;
    private Context context;

    public MainPresenter(Context context,ContactListView contactListView) {
        this.contactListView = contactListView;
        this.context = context;
    }

    public void onItemClick(int position){
        Contact contact = contactListView.getContact(position);
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra("contact",  contact);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

}
