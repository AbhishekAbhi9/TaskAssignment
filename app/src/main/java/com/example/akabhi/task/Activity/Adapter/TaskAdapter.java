package com.example.akabhi.task.Activity.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.akabhi.task.Activity.Activity.calenderActivity;
import com.example.akabhi.task.Activity.DataBase.DataBase;
import com.example.akabhi.task.R;

import java.util.ArrayList;
import java.util.Calendar;

import PojoClasses.Location_Pojo;

public class TaskAdapter extends BaseExpandableListAdapter {

    private ArrayList<Location_Pojo> location_pojos;
    private Context context;

    public TaskAdapter(ArrayList<Location_Pojo> location_pojo, FragmentActivity activity) {
        this.location_pojos = location_pojo;
        context = activity;
    }

    @Override
    public int getGroupCount() {
        return location_pojos.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return location_pojos.get(groupPosition);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = location_pojos.get(groupPosition).getLocationName();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.taskadpter, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.lblListItem);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.childview, null);
        }

        final TextView tasks = convertView.findViewById(R.id.tasks);
        final Button calender = convertView.findViewById(R.id.calender);

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, calenderActivity.class);
                intent.putExtra("parentposition", groupPosition);
                intent.putExtra("childpositoin", childPosition);
                intent.putExtra("location", location_pojos.get(groupPosition).getLocationName().toString());
                context.startActivity(intent);
            }
        });

        DataBase dataBase = new DataBase(context);
        Cursor cursor = dataBase.Select_Function_Task();
        while (cursor.moveToNext()) {
            if (cursor.getCount() > 0) {
                try {
                    if ((groupPosition == cursor.getInt(2)) && (childPosition == cursor.getInt(3))) {
                        tasks.setBackgroundColor(Color.parseColor("#228B22"));
                        tasks.setText(cursor.getString(1) + " (" + cursor.getString(4) + ") ");
                    }
                } catch (IndexOutOfBoundsException i) {
                }
            }
        }

        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 3;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
