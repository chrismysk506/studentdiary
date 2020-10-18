package com.example.studentdiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class databasehelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Adds.db";
    public static final String TABLE_NAME="studentattendance";
    public static final String COL_1="STUDENT_ID";
    public static final String COL_2="SUBJECT_NAME";
    public static final String COL_3="ATTENDANCE_STATUS";
    public static final String COL_4="DATE";
    public static final String COL_5="STUDENT_NAME";


    public databasehelper(Context context) {
        super(context,DATABASE_NAME,null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+ TABLE_NAME +"(STUDENT_ID INTEGER ,SUBJECT_NAME TEXT ,ATTENDANCE_STATUS TEXT,DATE TEXT ,STUDENT_NAME TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " +TABLE_NAME);onCreate(db);


    }


}
