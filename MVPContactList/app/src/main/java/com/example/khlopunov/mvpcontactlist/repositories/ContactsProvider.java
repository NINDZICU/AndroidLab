package com.example.khlopunov.mvpcontactlist.repositories;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.khlopunov.mvpcontactlist.entities.Contact;
import com.example.khlopunov.mvpcontactlist.presenters.EditPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anatoly on 16.03.2017.
 */

public class ContactsProvider {
    private static ContactsProvider sInstance;
    private Context context;
    private List<Contact> contacts;

    public ContactsProvider(Context context) {
        this.context = context;
    }

    public static ContactsProvider getInstance(@NonNull Context context){
        if(sInstance==null){
            sInstance = new ContactsProvider(context.getApplicationContext());
        }
        return sInstance;
    }
    public List<Contact> getContacts(){
        if(contacts==null){
            contacts=addContacts();
        }
        return contacts;
    }

    public void setContacts(Contact contact, int position){
        contacts.set(position, contact);
    }

    public List<Contact> addContacts(){
        List<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            contacts.add(new Contact("surname"+i, "name"+i, "mail"+i));
        }
        return contacts;
    }
}
