package com.example.khlopunov.mvpcontactlist;

import com.example.khlopunov.mvpcontactlist.entities.Contact;

/**
 * Created by Anatoly on 13.03.2017.
 */

public interface ContactListView {
    public Contact getContact(int position);
    public void setContact(Contact contact, int position);
}
