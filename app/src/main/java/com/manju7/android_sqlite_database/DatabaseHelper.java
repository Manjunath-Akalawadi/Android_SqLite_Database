package com.manju7.android_sqlite_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Employee.db";
    public static final String TABLE_NAME = "employee";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "CITY";



    public DatabaseHelper(Context context)   {
        super(context, DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" create table "+ TABLE_NAME +"("+COL1+" TEXT PRIMARY KEY ,"+COL2+" TEXT , "+COL3+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String id,String name,String city){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,name);
        contentValues.put(COL3,city);
        long result= db.insert(TABLE_NAME,null,contentValues);

        if (result==-1){
                return  true;
        }else {
                return false;
        }


    }


    public Cursor getAllData(){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery(" select * from "+TABLE_NAME,null);
        return res;

    }

    public boolean updateDate(String id,String name,String city){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,name);
        contentValues.put(COL3,city);
        db.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        return true;



    }

    public Integer deleteData(String id){

        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});

    }
}
