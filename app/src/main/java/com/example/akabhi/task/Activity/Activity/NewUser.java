package com.example.akabhi.task.Activity.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.akabhi.task.Activity.DataBase.DataBase;
import com.example.akabhi.task.R;

public class NewUser extends AppCompatActivity {

    private EditText userName, password, conformpassword;
    private Button save;
    private DataBase dataBase;
    private int userLenght, passwordLenght, conformpasswordLenght, RESULTCODE = 2;
    private android.support.v7.widget.Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        conformpassword = findViewById(R.id.conformpassword);
        save = findViewById(R.id.save);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar();
        getSupportActionBar().setTitle("User Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saveUser();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void saveUser() {
        dataBase = new DataBase(NewUser.this);
        userLenght = userName.getText().length();
        passwordLenght = password.getText().length();
        conformpasswordLenght = conformpassword.getText().length();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().equals(conformpassword.getText().toString())) {
                    dataBase.Insert_Function_User(userName.getText().toString(), password.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra("username", userName.getText().toString());
                    setResult(RESULTCODE, intent);
                    finish();
                } else {
                    Toast.makeText(NewUser.this, "password is not correct", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
