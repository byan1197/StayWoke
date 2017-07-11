package com.example.bond.staywoke;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bond on 10/07/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    //DATABASE VARIABLES
    public static final String DATABASE_NAME = "alarms.db";
    public static final String TABLE_NAME= "alarms_table";
    public static final String COL_1 = "ID";
    public static final String COL_2= "HOURS";
    public static final String COL_3= "MINUTES";
    public static final String COL_4= "REPEAT";
    public static final String COL_5= "ONOFF";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, HOURS INTEGER, MINUTES INTEGER, REPEAT TEXT, ONOFF TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(int hours, int minutes, String repeat, String onoff){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, hours);
        contentValues.put(COL_3, minutes);
        contentValues.put(COL_4, repeat);
        contentValues.put(COL_5, onoff);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        return true;

    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }
}
