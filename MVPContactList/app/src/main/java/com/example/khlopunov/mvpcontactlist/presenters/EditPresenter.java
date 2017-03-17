package com.example.khlopunov.mvpcontactlist.presenters;

import com.example.khlopunov.mvpcontactlist.EditInterface;
import com.example.khlopunov.mvpcontactlist.entities.Contact;

/**
 * Created by Anatoly on 15.03.2017.
 */

public class EditPresenter {
    private EditInterface edit;

    public EditPresenter(EditInterface edit) {
        this.edit = edit;
    }
    public void onClickSaveName(Contact contact, int position){
        contact.setName(edit.getName());
        edit.saveData(contact, position);
    }
    public void onClickSaveSurname(Contact contact, int position){
        contact.setSurname(edit.getSurname());
        edit.saveData(contact, position);
    }
    public void onClickSaveMail(Contact contact, int position){
        contact.setMail(edit.getMail());
        edit.saveData(contact, position);
    }
}
