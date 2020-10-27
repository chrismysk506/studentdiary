package com.example.studentdiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class databasehelper3 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="forgetpass.db";
    public static final String TABLE_NAME="studentdetails";
    public static final String COL_1="STUDENT_ID";
    public static final String COL_2="FIRST_NAME";
    public static final String COL_3="LAST_NAME";
    public static final String COL_4="ADRESS";
    public static final String COL_5="PHONE_NUMBER";
    public static final String COL_6="USERNAME";
    public static final String COL_7="PASSWORD";


    public databasehelper3(Context context) {
        super(context,DATABASE_NAME,null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+ TABLE_NAME +"(STUDENT_ID INTEGER PRIMARY KEY AUTOINCREMENT ,FIRST_NAME TEXT ,LAST_NAME TEXT ,ADRESS TEXT ,PHONE_NUMBER TEXT ,USERNAME TEXT ,PASSWORD TEXT )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " +TABLE_NAME);onCreate(db);


    }


}
