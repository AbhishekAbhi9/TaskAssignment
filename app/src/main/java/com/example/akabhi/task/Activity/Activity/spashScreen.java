package com.example.akabhi.task.Activity.Activity;

import android.content.Intent;
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
}
