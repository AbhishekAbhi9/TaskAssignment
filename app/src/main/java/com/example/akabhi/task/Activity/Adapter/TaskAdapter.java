package com.example.akabhi.task.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.akabhi.task.Activity.Activity.NewUser;
import com.example.akabhi.task.R;

import java.util.ArrayList;

import PojoClasses.Location_Pojo;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class TaskAdapter extends BaseExpandableListAdapter {

    private ArrayList<Location_Pojo> location_pojos;
    private Context context;
    private int REQUESTCODE = 1;

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

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.childview, null);
        }

        TextView tasks = convertView.findViewById(R.id.tasks);
        final Button calender = convertView.findViewById(R.id.calender);

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("position", String.valueOf(childPosition));
                Log.e("group", String.valueOf(groupPosition));
            }
        });

        tasks.setTypeface(null, Typeface.BOLD);
        tasks.setText("add task");
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
