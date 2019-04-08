package com.example.first;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper extends SQLiteOpenHelper  {
    public static final String DATABASE_NAME = "robible.db";
    public static final String TABLE_NAME = "rotext";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "bible";
    public static final String COL_3 = "capitol";
    public static final String COL_4 = "poem";
    public static final String COL_5 = "poemtext";
//_________
    // public EditText editTextDuplicat;

//_________________

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "   (ID INTEGER PRIMARY KEY AUTOINCREMENT, BIBLE INTEGER, CAPITOL INTEGER, POEM INTEGER, POEMTEXT TEXT)");


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(int bible, int capitol, int poem, String poemtext){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, bible);
        contentValues.put(COL_3, capitol);
        contentValues.put(COL_4, poem);
        contentValues.put(COL_5, poemtext);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result==-1){
            return false;
        } else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);

        return res;

    }
    public Cursor doarcapitol(int bible){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " WHERE " + "bible = " + bible , null);
        return res;
    }


}
