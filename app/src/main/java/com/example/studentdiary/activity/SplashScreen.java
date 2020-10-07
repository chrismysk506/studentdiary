 package com.example.studentdiary.activity;

 import android.app.Activity;
 import android.content.Intent;
 import android.os.Bundle;
 import android.os.Handler;
 import android.view.Window;


 import com.example.studentdiary.bean.AttendanceBean;
 import com.example.studentdiary.bean.AttendanceSessionBean;
 import com.example.studentdiary.bean.FacultyBean;
 import com.example.studentdiary.bean.StudentBean;
 import com.example.studentdiary.context.ApplicationContext;
 import com.example.studentdiary.db.DBAdapter;
 import com.example.studentdiary.R;
 public class SplashScreen extends Activity {

     private static int SPLASH_TIME_OUT = 4000;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         requestWindowFeature(Window.FEATURE_NO_TITLE);
         setContentView(R.layout.activity_splash_screen);
         new Handler().postDelayed(new Runnable(){
             @Override
             public void run(){
                 Intent homeIntent = new Intent(SplashScreen.this, LoginActivity.class);
                 startActivity(homeIntent);
                 finish();
             }
         },SPLASH_TIME_OUT);
     }
 }
