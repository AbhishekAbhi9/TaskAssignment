package com.example.akabhi.task.Activity.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akabhi.task.Activity.DataBase.DataBase;
import com.example.akabhi.task.R;

public class loginScreen extends AppCompatActivity {

    private Button Login;
    private EditText user_name, user_password;
    private TextView user, forgotPassword, password;
    private int REQUESTCODE = 1;
    private Dialog dialog;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        Login = findViewById(R.id.btn_login);
        user_name = findViewById(R.id.input_user);
        user_password = findViewById(R.id.input_password);
        user = findViewById(R.id.newUser);
        forgotPassword = findViewById(R.id.forgotPassword);

        //==========================================================================================
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewUser();
            }
        });

        //==========================================================================================
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        //==========================================================================================
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotPassword();
            }
        });

    }

    private void NewUser() {
        Intent intent = new Intent(loginScreen.this, NewUser.class);
        startActivityForResult(intent, REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE) {
            try {
                String newUser = data.getStringExtra("username");
                user_name.setText(newUser);
            } catch (NullPointerException n) {
            }
        }
    }

    private void Login() {
        dataBase = new DataBase(loginScreen.this);
        Cursor cursor = dataBase.Select_Function_User();
        while (cursor.moveToNext()) {
            if (user_name.getText().toString().equals(cursor.getString(1)) &&
                    user_password.getText().toString().equals(cursor.getString(2))) {
                Intent intent = new Intent(loginScreen.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please check password and username", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ForgotPassword() {
        if (user_name.getText().toString().isEmpty()) {
            Toast.makeText(loginScreen.this, "Please enter user name", Toast.LENGTH_SHORT).show();
        } else {
            dataBase = new DataBase(loginScreen.this);
            Cursor cursor = dataBase.Select_Function_User();
            while (cursor.moveToNext()) {
                if (user_name.getText().toString().equals(cursor.getString(1))) {
                    dialog = new Dialog(loginScreen.this);
                    dialog.setContentView(R.layout.custompopup);
                    password = dialog.findViewById(R.id.password);
                    password.setText(cursor.getString(2));
                    dialog.show();
                } else {
                    Toast.makeText(this, "UserName not exits", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

}
