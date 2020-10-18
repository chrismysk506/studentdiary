package com.example.studentdiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databasehelper2  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="login.db";
    public static final String TABLE_NAME="studentlogin";
    public static final String COL_1="STUDENT_ID";
    public static final String COL_2="USER_NAME";
    public static final String COL_3="PASSWORD";



    public databasehelper2(Context context) {
        super(context,DATABASE_NAME,null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+ TABLE_NAME +"(STUDENT_ID INTEGER PRIMARY KEY AUTOINCREMENT ,USER_NAME TEXT ,PASSWORD TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " +TABLE_NAME);onCreate(db);


    }


}


