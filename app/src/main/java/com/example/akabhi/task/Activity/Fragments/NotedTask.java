package com.example.akabhi.task.Activity.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akabhi.task.Activity.Adapter.TaskAdapter;
import com.example.akabhi.task.Activity.DataBase.DataBase;
import com.example.akabhi.task.R;

import java.util.ArrayList;

import PojoClasses.Location_Pojo;

public class NotedTask extends Fragment {

    private ExpandableListView taskExpandableListView;
    private ArrayList<Location_Pojo> location_pojo;
    private Cursor cursor;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DataBase dataBase;
    private TextView swipetexlastt, swipetextfirst, swipetextmiddle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notedtask, null);
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        taskExpandableListView = view.findViewById(R.id.taskExpandableListView);
        swipetexlastt = view.findViewById(R.id.swipetexlastt);
        swipetextfirst = view.findViewById(R.id.swipetextfirst);
        swipetextmiddle = view.findViewById(R.id.swipetextmiddle);

        setTaskRecyclerview();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setTaskRecyclerview();
            }
        });

        return view;
    }

    private void setTaskRecyclerview() {
        dataBase = new DataBase(getActivity());
        location_pojo = new ArrayList<>();
        cursor = dataBase.Select_Function_Location();
        if (cursor.getCount() != 0) {
            swipetextfirst.setVisibility(View.GONE);
            swipetextmiddle.setVisibility(View.GONE);
            swipetexlastt.setVisibility(View.GONE);
            taskExpandableListView.setVisibility(View.VISIBLE);

            while (cursor.moveToNext()) {
                Location_Pojo locationPojo = new Location_Pojo();
                locationPojo.LocationName = cursor.getString(3);
                location_pojo.add(locationPojo);
            }
            TaskAdapter taskAdapter = new TaskAdapter(location_pojo, getActivity());
            taskExpandableListView.setAdapter(taskAdapter);
            swipeRefreshLayout.setRefreshing(false);
        } else {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
