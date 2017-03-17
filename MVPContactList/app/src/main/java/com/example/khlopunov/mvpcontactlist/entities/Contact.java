package com.example.khlopunov.mvpcontactlist.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anatoly on 12.03.2017.
 */

public class Contact implements Parcelable{
    private String surname;
    private String name;
    private String mail;

    public Contact(String name, String surname, String mail) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }


    public Contact(Parcel in) {
        String[] data = new String[3];
        in.readStringArray(data);
        name = data[0];
        surname = data[1];
        mail = data[2];
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{surname, name, mail});
    }
    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {

        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
