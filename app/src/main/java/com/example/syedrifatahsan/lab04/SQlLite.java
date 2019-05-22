package com.example.syedrifatahsan.lab04;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQlLite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="employeeDB";
    public static final String TABLE_NAME="employeeTable";


    public SQlLite(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL("create table employeeDB(id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(255),loc1 VARCHAR(255),loc2 VARCHAR(255))");
        }catch (Exception e){
            Log.d("dbPera",e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            db.execSQL("drop table if exists employeeDB");
            onCreate(db);
        } catch (Exception e){
            Log.d("dbPera",e.toString());
        }


    }

    public long insertData(String name,String loc1,String loc2){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",name);
        contentValues.put("loc1",loc1);
        contentValues.put("loc2",loc2);
        long insertid =sqLiteDatabase.insert(DATABASE_NAME,null,contentValues);
        return insertid;

    }
}
