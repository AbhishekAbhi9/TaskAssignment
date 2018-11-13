package com.example.akabhi.task.Activity.Fragments;

import android.database.Cursor;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akabhi.task.Activity.Adapter.LocationAdapter;
import com.example.akabhi.task.Activity.DataBase.DataBase;
import com.example.akabhi.task.R;

import java.util.ArrayList;

import PojoClasses.Location_Pojo;

public class LocationData extends Fragment {

    private DataBase dataBase;
    private Double lat, log;
    private String place;
    private RecyclerView locationList;
    private ArrayList<Location_Pojo> location_pojos;
    private LocationAdapter locationAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, parent, false);
        locationList = view.findViewById(R.id.locationList);

        location_pojos = new ArrayList<>();
        dataBase = new DataBase(getContext());
        Cursor cursor = dataBase.Select_Function_Location();
        while (cursor.moveToNext()) {
            Location_Pojo location = new Location_Pojo();
            location.lat = cursor.getDouble(1);
            location.log = cursor.getDouble(2);
            location.LocationName = cursor.getString(3);
            location_pojos.add(location);
        }

        LocationAdapter locationAdapter = new LocationAdapter(location_pojos, (FragmentActivity) getContext());
        locationList.setAdapter(locationAdapter);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        locationList.setLayoutManager(linearLayoutManager);
        return view;
    }
}
