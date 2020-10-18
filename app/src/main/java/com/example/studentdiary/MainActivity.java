package com.example.studentdiary;

import android.app.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentdiary.activity.AddAttandanceSessionActivity;
import com.example.studentdiary.activity.AddAttendanceActivity;
import com.example.studentdiary.activity.AddFacultyActivity;
import com.example.studentdiary.activity.AddStudentActivity;
import com.example.studentdiary.activity.LoginActivity;
import com.example.studentdiary.activity.MenuActivity;
import com.example.studentdiary.activity.ViewAttendanceByFacultyActivity;
import com.example.studentdiary.activity.ViewAttendancePerStudentActivity;
import com.example.studentdiary.activity.ViewFacultyActivity;
import com.example.studentdiary.activity.ViewStudentActivity;
import com.example.studentdiary.bean.AttendanceBean;
import com.example.studentdiary.bean.AttendanceSessionBean;
import com.example.studentdiary.bean.FacultyBean;
import com.example.studentdiary.bean.StudentBean;
import com.example.studentdiary.context.ApplicationContext;
import com.example.studentdiary.db.DBAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends Activity {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    ArrayList<String> subjectname,status,times;
    SQLiteOpenHelper helper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.reccycler);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        AttendanceBean attendanceBean = new AttendanceBean();
        subjectname=new ArrayList<>();
        status=new ArrayList<>();
        times=new ArrayList<>();

                         /*    subjectname.add("dbms");
                             status.add("Present");
                             times.add("03/12/2020");*/
        helper=new databasehelper(this);
        db=helper.getReadableDatabase();
        Intent i=new Intent();
        String ids=getIntent().getStringExtra("id");
        Toast.makeText(getApplicationContext(),ids+"sddddddddddddddddddd", Toast.LENGTH_SHORT).show();

        Cursor cursor=db.rawQuery(" SELECT * FROM "+databasehelper.TABLE_NAME+" WHERE "+databasehelper.COL_1+"=?",new String[]{ids});
       /* for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            subjectname.add(cursor.getColumnName(1));
            status.add(cursor.getColumnName(2));
            times.add(cursor.getColumnName(3));
        }   */

       while (cursor.moveToNext())
       {
             subjectname.add(cursor.getString(cursor.getColumnIndex("SUBJECT_NAME")));
             status.add(cursor.getString(cursor.getColumnIndex("ATTENDANCE_STATUS")));
             times.add(cursor.getString(cursor.getColumnIndex("DATE")));
       }
       // Toast.makeText(getApplicationContext(),subjectname.get(1)+"k",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
        manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        myAdapter myadap=new myAdapter(recyclerView,this,subjectname,status,times);
        
        recyclerView.setAdapter(myadap);
        




    }
}