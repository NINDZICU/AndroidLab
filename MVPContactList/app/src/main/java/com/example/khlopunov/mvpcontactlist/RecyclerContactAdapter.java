package com.example.khlopunov.mvpcontactlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khlopunov.mvpcontactlist.entities.Contact;
import com.example.khlopunov.mvpcontactlist.presenters.MainPresenter;
import com.example.khlopunov.mvpcontactlist.repositories.ContactsProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anatoly on 12.03.2017.
 */

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ContactsViewHolder>
implements ContactListView{
    private List<Contact> mContacts;
    private MainPresenter mainPresenter;
    private Context context;

    public RecyclerContactAdapter(Context context) {
        this.context = context;
        mainPresenter = new MainPresenter(context, this);
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.contact_item,
                parent,
                false
        );
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, final int position) {
        final Contact contact = mContacts.get(position);
        holder.surname.setText(contact.getSurname());
        holder.name.setText(contact.getName());
        holder.mail.setText(contact.getMail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        mContacts = ContactsProvider.getInstance(context).getContacts();
        return mContacts.size();
    }



    @Override
    public Contact getContact(int position) {
        return mContacts.get(position);
    }

    @Override
    public void setContact(Contact contact, int position) {
        mContacts.set(position, contact);
        notifyDataSetChanged();
    }

    public void refresh(){
        notifyDataSetChanged();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView surname;
        TextView name;
        TextView mail;
        public ContactsViewHolder(View itemView) {
            super(itemView);
            surname = (TextView) itemView.findViewById(R.id.tv_surname);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            mail = (TextView) itemView.findViewById(R.id.tv_mail);
        }
    }

}
