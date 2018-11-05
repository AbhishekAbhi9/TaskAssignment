package com.example.akabhi.task.Activity.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.akabhi.task.Activity.Fragments.CurrentLocation;
import com.example.akabhi.task.Activity.Fragments.NoteTask;

public class PageAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                CurrentLocation currentLocation = new CurrentLocation();
                return currentLocation;
            case 1:
                NoteTask noteTask = new NoteTask();
                return noteTask;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
