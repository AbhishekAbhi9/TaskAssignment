package com.example.akabhi.task.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akabhi.task.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import PojoClasses.Location_Pojo;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.View_Holder> {

    private ArrayList<Location_Pojo> location_pojos;
    private Context context;

    public LocationAdapter(ArrayList<Location_Pojo> location_pojos, FragmentActivity activity) {
        this.location_pojos = location_pojos;
        context = activity;
    }

    @Override
    public int getItemCount() {
        return location_pojos.size();
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.locationadapter, parent, false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder view_holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        view_holder.lat.setText(String.valueOf(decimalFormat.format(location_pojos.get(position).getLat())));
        view_holder.log.setText(String.valueOf(decimalFormat.format(location_pojos.get(position).getLog())));
        view_holder.name.setText(location_pojos.get(position).getLocationName());
    }

    public class View_Holder extends RecyclerView.ViewHolder {
        private TextView lat, log, name;

        public View_Holder(@NonNull View itemView) {
            super(itemView);
            lat = itemView.findViewById(R.id.lat);
            log = itemView.findViewById(R.id.log);
            name = itemView.findViewById(R.id.name);
        }
    }

}
