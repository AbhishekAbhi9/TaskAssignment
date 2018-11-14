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

    //2. User Location======================================================
    private static final String LOCATION_TABLE = "Location_Data";
    //a. its column name
    private static final String LOCATION_ID = "Location_id";
    private static final String LOCATION_LAT = "Location_lat";
    private static final String LOCATION_LOG = "Location_log";
    private static final String LOCATION_NAME = "Location_namew";

    //==============================================================================================
    //==============================================================================================
    private String CREATE_CONTACT_TABLE_LOCATION = " CREATE table IF NOT EXISTS " + LOCATION_TABLE + "(" + LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + LOCATION_LAT +
            " REAL, " + LOCATION_LOG + " REAL, " + LOCATION_NAME + " TEXT)";


    //3. User Task======================================================
    private static final String TASK_TABLE = "Task_Data";
    //a. its column name
    private static final String TASK_ID = "Task_id";
    private static final String TASK_DATA = "Task_data";
    private static final String TASK_PARENTPOSITION = "Task_ParentPosition";
    private static final String TASK_CHILDPOSITION = "Task_ChildPosition";
    private static final String TASK_DATE = "Task_Date";
    private static final String TASK_LOCATION = "Task_Location";

    //==============================================================================================
    //==============================================================================================
    private String CREATE_CONTACT_TABLE_TASK = " CREATE table IF NOT EXISTS " + TASK_TABLE + "(" + TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TASK_DATA +
            " TEXT, " + TASK_PARENTPOSITION + " INTEGER, " + TASK_CHILDPOSITION + " INTEGER," + TASK_DATE + " DATETIME," + TASK_LOCATION + " TEXT)";


    public DataBase(Context context) {
        super(context, DATABASENAME, null, VERSION);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE_LOCATION);
        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LOCATION_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
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

    //2.Insert , Select Function For User Data
    //==============================================================================================
    //==============================================================================================
    public void Insert_Function_Location(double lat, double log, String locationname) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOCATION_LAT, lat);
        contentValues.put(LOCATION_LOG, log);
        contentValues.put(LOCATION_NAME, locationname);
        sqLiteDatabase.insert(LOCATION_TABLE, null, contentValues);
    }

    public Cursor Select_Function_Location() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        QUERY = "SELECT * FROM " + LOCATION_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
        return cursor;
    }

    //3.Insert , Select Function For User Data
    //==============================================================================================
    //==============================================================================================
    public void Insert_Function_Task(String task, int parentposition, int childposition, String date, String location) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_DATA, task);
        contentValues.put(TASK_PARENTPOSITION, parentposition);
        contentValues.put(TASK_CHILDPOSITION, childposition);
        contentValues.put(TASK_DATE, date);
        contentValues.put(TASK_LOCATION, location);
        sqLiteDatabase.insert(TASK_TABLE, null, contentValues);
    }

    public Cursor Select_Function_Task() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        QUERY = "SELECT * FROM " + TASK_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY, null);
        return cursor;
    }
}
