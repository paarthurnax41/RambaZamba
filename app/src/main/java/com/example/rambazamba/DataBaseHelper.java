package com.example.rambazamba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "ACTIVITIES";

    // Table columns
    public static final String _ID = "_id";
    public static final String ACTIVITY = "activity";


    // Database Information
    static final String DB_NAME = "myActivities.DB";

    // database version
    static final int DB_VERSION = 1;

    private SQLiteDatabase database;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ACTIVITY + " CHAR(50));";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void open() throws SQLException {
        database = this.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public void add(String activity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACTIVITY, activity);


        database.insert(TABLE_NAME, null, contentValues);
    }

    public List<String> getAllActivities() {
        Cursor cursor = getActivitiesCursor();

        List<String> activities = new ArrayList<>();
        while (cursor.moveToNext()) {
            activities.add(cursor.getString(1));
        }
        cursor.close();

        return activities;
    }

    public Cursor getActivitiesCursor() {
        String[] projection = {
                _ID, ACTIVITY
        };

        return database.query(TABLE_NAME, projection, null, null, null, null, null);
    }


    public int update(long _id, String activity) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ACTIVITY, activity);

        int count = database.update(TABLE_NAME, contentValues, _ID + " = " + _id, null);
        return count;
    }

    public void delete(long _id) {
        database.delete(TABLE_NAME, _ID + "=" + _id, null);
    }

    public String get(long _id) {
        Cursor c = database.query(TABLE_NAME, new String[]{ACTIVITY}, _ID + " = " + _id, null, null, null, null);
        String activity = null;
        if (c.moveToNext()) {
            activity = c.getString(0);
        }
        c.close();
        return activity;
    }

    public Cursor getNames() {
        String[] projection = {
                _ID, ACTIVITY
        };

        // Filter results WHERE "name" = passed in parameter
//            String selection = NAME + " = ?";
//            String[] selectionArgs = { namePassedIn};

        // How you want the results sorted in the resulting Cursor
        String sortOrder = _ID + " DESC";

        Cursor cursor = database.query(
                TABLE_NAME,                     // The table to query
                projection,                     // The columns to return
                null,                      // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                   // don't group the rows
                null,                    // don't filter by row groups
                sortOrder                      // The sort order
        );

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }
}

