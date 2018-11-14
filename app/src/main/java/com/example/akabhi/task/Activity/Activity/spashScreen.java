package com.example.akabhi.task.Activity.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.akabhi.task.R;

public class spashScreen extends AppCompatActivity {

    private ImageView logo_image;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);
        logo_image = findViewById(R.id.spashlogo);

        Load_permission();

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
        logo_image.setAnimation(animation);
        final Intent intent = new Intent(getApplicationContext(), loginScreen.class);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }

    protected void Load_permission() {
        if (ActivityCompat.checkSelfPermission(spashScreen.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(spashScreen.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(spashScreen.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }
}
