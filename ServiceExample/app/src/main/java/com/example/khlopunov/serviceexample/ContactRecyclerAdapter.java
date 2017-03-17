package com.example.khlopunov.serviceexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khlopunov.serviceexample.model.User;
import com.example.khlopunov.serviceexample.providers.UserProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anatoly on 17.03.2017.
 */

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactRecyclerAdapter.ContactsViewHolder> {
    private List<User> users;
    private Context context;

    public ContactRecyclerAdapter(Context context) {
        this.context = context;
        users = new ArrayList<>();
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
        final User user = users.get(position);
        holder.surname.setText(user.getName().getLast());
        holder.name.setText(user.getName().getFirst());
        holder.mail.setText(user.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InfoContactActivity.class);

                intent.putExtra("user", user);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        users = UserProvider.getInstance(context).getContacts();
        return users.size();
    }

    public void refresh() {
        this.users = UserProvider.getInstance(context).getContacts();
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
