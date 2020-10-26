package com.example.studentdiary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.studentdiary.bean.AttendanceBean;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    ArrayList<String> subjectname,status,times;
    SQLiteOpenHelper helper;
    SQLiteDatabase db;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView=view.findViewById(R.id.reccycler);

        AttendanceBean attendanceBean = new AttendanceBean();
        subjectname=new ArrayList<>();
        status=new ArrayList<>();
        times=new ArrayList<>();


        helper=new databasehelper(getActivity());
        db=helper.getReadableDatabase();
        Intent i=new Intent();
        String ids=getActivity().getIntent().getStringExtra("id");
        Toast.makeText(getActivity(),ids+"sddddddddddddddddddd", Toast.LENGTH_SHORT).show();

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

        manager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        myAdapter myadap=new myAdapter(recyclerView,getActivity(),subjectname,status,times);

        recyclerView.setAdapter(myadap);

        return view;
    }
}