package com.example.akabhi.task.Activity.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.akabhi.task.Activity.DataBase.DataBase;
import com.example.akabhi.task.R;

public class calenderActivity extends AppCompatActivity {

    private CalendarView calenderView;
    private String location;
    private int ParentPosition, ChildPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        calenderView = findViewById(R.id.calenderView);
        Intent intent = getIntent();
        ParentPosition = intent.getIntExtra("parentposition", 0);
        ChildPosition = intent.getIntExtra("childpositoin", 0);
        location = intent.getStringExtra("location");

        calenderView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if (dayOfMonth > 0) {
                    final Dialog dialog = new Dialog(calenderActivity.this);
                    dialog.setContentView(R.layout.customdialoge);
                    TextView date = dialog.findViewById(R.id.date);
                    final EditText taskNote = dialog.findViewById(R.id.taskNote);
                    Button cancel = dialog.findViewById(R.id.cancel);
                    Button ok = dialog.findViewById(R.id.ok);

                    month = month + 1;
                    //==============================================================================
                    final String datetime = dayOfMonth + "/" + month + "/" + year;
                    date.setText(datetime);
                    dialog.show();
                    //==============================================================================
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });

                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String taskNotedString = taskNote.getText().toString();
                            DataBase dataBase = new DataBase(calenderActivity.this);
                            dataBase.Insert_Function_Task(taskNotedString, ParentPosition, ChildPosition, datetime,location);
                            dialog.cancel();
                            finish();
                        }
                    });

                }
            }
        });
    }
}
