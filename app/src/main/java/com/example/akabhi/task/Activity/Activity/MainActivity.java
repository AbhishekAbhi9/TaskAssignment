package com.example.akabhi.task.Activity.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.akabhi.task.Activity.Fragments.LocationData;
import com.example.akabhi.task.Activity.Fragments.Note;
import com.example.akabhi.task.Activity.Fragments.Task;
import com.example.akabhi.task.R;

public class MainActivity extends AppCompatActivity {

    //<----this is the tag which are declare for the defining the tag
    private static String TASK = "Task", LOCATIONDATA = "Location", NOTETASK = "TastNote", LOGOUT = "Logout", CURRENT_TAG = TASK;

    private String[] Fragment_title;
    //<--this is the integer to trace the respective activity
    private static int navItemIndex = 0;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.draw_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Fragment_title = getResources().getStringArray(R.array.nav_item_activity_titles);
        loading_all_the_nav_layout();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TASK;
            load_Fragment();
        }
    }

    protected void loading_all_the_nav_layout() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //setting menu name
        set_menu_name();
    }

    protected void set_menu_name() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_task:
                        navItemIndex = 0;
                        CURRENT_TAG = TASK;
                        break;
                    case R.id.nav_location:
                        navItemIndex = 1;
                        CURRENT_TAG = LOCATIONDATA;
                        break;
                    case R.id.nav_note:
                        navItemIndex = 2;
                        CURRENT_TAG = NOTETASK;
                        break;
                    case R.id.nav_logout:
                        navItemIndex = 3;
                        CURRENT_TAG = LOGOUT;
                        break;
                    default:
                        navItemIndex = 0;
                }
                //here we are checking the menu item is checked or not
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }

                //and if the menu item is not checked then it should be checked and load the fragment to the activity
                item.setChecked(true);

                //this is the function is used to call the load function
                load_Fragment();
                return true;
            }
        });
    }

    protected void load_Fragment() {

        toolbar.setTitle(Fragment_title[navItemIndex]);
        mDrawerLayout.closeDrawers();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = Call_all_Fragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (runnable != null) {
            handler = new Handler();
            handler.post(runnable);
        }

        //Closing drawer on item click
        mDrawerLayout.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    public Fragment Call_all_Fragment() {
        switch (navItemIndex) {
            case 0:
                Task task = new Task();
                return task;
            case 1:
                LocationData locationData = new LocationData();
                return locationData;
            case 2:
                Note note = new Note();
                return note;
            case 3:
                Intent intent = new Intent(MainActivity.this, loginScreen.class);
                startActivity(intent);
                break;
        }
        return new Task();
    }
}
