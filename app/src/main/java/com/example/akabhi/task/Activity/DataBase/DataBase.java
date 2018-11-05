package com.example.akabhi.task.Activity.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class DataBase extends SQLiteOpenHelper {
    private static final String DATABASENAME = "task.db";
    private static final int VERSION = 1;
    private SQLiteDatabase sqLiteDatabase;
    private static String QUERY;

    //1. User Data======================================================
    private static final String USER_TABLE = "User_Data";
    //a. its column name
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";

    //==============================================================================================
    //==============================================================================================
    private String CREATE_CONTACT_TABLE_USER = " CREATE table IF NOT EXISTS " + USER_TABLE + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_NAME +
            " TEXT, " + PASSWORD + " TEXT)";

    public DataBase(Context context) {
        super(context, DATABASENAME, null, VERSION);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    //1.Insert , Select Function For User Data
    //==============================================================================================
    //==============================================================================================
    public void Insert_Function_User(String user_name, String password) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, user_name);
        contentValues.put(PASSWORD, password);
        sqLiteDatabase.insert(USER_TABLE, null, contentValues);
    }

    public Cursor Select_Function_User() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        QUERY = "SELECT * FROM " + USER_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
        return cursor;
    }
}
