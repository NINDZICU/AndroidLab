package com.example.khlopunov.mvpcontactlist;

import com.example.khlopunov.mvpcontactlist.entities.Contact;

/**
 * Created by Anatoly on 15.03.2017.
 */

public interface EditInterface {
    public String getName();
    public String getSurname();
    public String getMail();
    public void setDataContact(Contact contact);
    public void saveData(Contact contact, int position);
}
