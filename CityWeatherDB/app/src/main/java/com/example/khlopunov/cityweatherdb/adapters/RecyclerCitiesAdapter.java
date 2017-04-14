package com.example.khlopunov.cityweatherdb.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.khlopunov.cityweatherdb.R;
import com.example.khlopunov.cityweatherdb.entities.City;
import com.example.khlopunov.cityweatherdb.tables.CityContract;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Admin on 06.11.2016.
 */

public class RecyclerCitiesAdapter extends RecyclerView.Adapter<RecyclerCitiesAdapter.CitiesViewHolder> {
    private List<City> mCities;
    private Context context;

    public RecyclerCitiesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.city_item,
                parent,
                false
        );
        return new CitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CitiesViewHolder holder, int position) {
        final City city = mCities.get(position);
        holder.nameCity.setText(city.getName());
        holder.tvDegrees.setText(String.valueOf(city.getDegrees()) + "°С");

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] mSelectionArgs = {Integer.toString(city.getId())};
                System.out.println("ID:" + city.getId());
                context.getContentResolver().delete(CityContract.getBaseUri(), CityContract.CityEntry.COLUMN_CITY_NAME + "=?",
                        new String[]{city.getName()});
                context.getContentResolver().notifyChange(CityContract.getBaseUri(), null);
            }
        });
    }

    @Override
    public int getItemCount() {
        Cursor mCursor = context.getContentResolver().query(CityContract.getBaseUri(),
                null, null, null, null);
        if (mCursor != null) {
            mCities = new ArrayList<>();
            while (mCursor.moveToNext()) {
                City changedCity = CityContract.fromCursor(mCursor);
                mCities.add(changedCity);
            }
            mCursor.close();
        }
        return mCities.size();
    }

    public class CitiesViewHolder extends RecyclerView.ViewHolder {
        TextView nameCity;
        TextView tvDegrees;
        Button btnDelete;

        public CitiesViewHolder(View itemView) {
            super(itemView);
            nameCity = (TextView) itemView.findViewById(R.id.tv_name_city);
            tvDegrees = (TextView) itemView.findViewById(R.id.tv_degrees);
            btnDelete = (Button) itemView.findViewById(R.id.btn_delete);
        }
    }

    public void onUpdate() {
        notifyDataSetChanged();
    }
}
