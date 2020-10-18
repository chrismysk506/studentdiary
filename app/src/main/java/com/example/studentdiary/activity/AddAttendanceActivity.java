package com.example.studentdiary.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.studentdiary.bean.AttendanceBean;

import com.example.studentdiary.bean.AttendanceBean;
import com.example.studentdiary.bean.AttendanceSessionBean;
import com.example.studentdiary.bean.FacultyBean;
import com.example.studentdiary.bean.StudentBean;
import com.example.studentdiary.context.ApplicationContext;
import com.example.studentdiary.databasehelper;
import com.example.studentdiary.db.DBAdapter;
import com.example.studentdiary.R;
import java.util.ArrayList;

public class AddAttendanceActivity extends Activity {
    String subjectname,date;
	SQLiteOpenHelper helper;
	SQLiteDatabase db;
	ArrayList<StudentBean> studentBeanList;
	private ListView listView ;
	private ArrayAdapter<String> listAdapter;
	int sessionId=0;
	String status="P";
	Button attendanceSubmit;
	DBAdapter dbAdapter = new DBAdapter(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.__listview_main);

		sessionId = getIntent().getExtras().getInt("sessionId");
		subjectname=getIntent().getStringExtra("subject");
		date=getIntent().getStringExtra("dates");

		helper=new databasehelper(this);
		
		listView=(ListView)findViewById(R.id.listview);
		final ArrayList<String> studentList = new ArrayList<String>();

		studentBeanList=((ApplicationContext)AddAttendanceActivity.this.getApplicationContext()).getStudentBeanList();


		for(StudentBean studentBean : studentBeanList)
		{
			String users = studentBean.getStudent_firstname()+","+studentBean.getStudent_lastname();
				
			studentList.add(users);
			Log.d("users: ", users);

		}

		listAdapter = new ArrayAdapter<String>(this, R.layout.add_student_attendance, R.id.labelA, studentList);
		listView.setAdapter( listAdapter ); 

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

				arg0.getChildAt(arg2).setBackgroundColor(Color.TRANSPARENT);
				//arg0.setBackgroundColor(234567);
				arg1.setBackgroundColor(334455);
				final StudentBean studentBean = studentBeanList.get(arg2);
				final Dialog dialog = new Dialog(AddAttendanceActivity.this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//...........
				dialog.setContentView(R.layout.test_layout);
				// set title and cancelable
				RadioGroup radioGroup;
				RadioButton present;
				RadioButton absent;
				radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
				present=(RadioButton)dialog.findViewById(R.id.PresentradioButton);
				absent=(RadioButton)dialog.findViewById(R.id.AbsentradioButton);
				radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if(checkedId == R.id.PresentradioButton) {
							
							status = "P";
						} else if(checkedId == R.id.AbsentradioButton) {

							status = "A";
						} else {
						}
					}
				});

				attendanceSubmit = (Button)dialog.findViewById(R.id.attendanceSubmitButton);
				attendanceSubmit.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						AttendanceBean attendanceBean = new AttendanceBean();
						
						attendanceBean.setAttendance_session_id(sessionId);
						attendanceBean.setAttendance_student_id(studentBean.getStudent_id());
						attendanceBean.setAttendance_status(status);
						String kt=studentBean.getStudent_firstname();
						insert(Integer.toString(studentBean.getStudent_id()),subjectname,status,date,kt);
						/*db=helper.getWritableDatabase();
						ContentValues contentValues=new ContentValues();
						//contentValues.put(databasehelper.COL_1,studentBean.getStudent_id());

						contentValues.put(databasehelper.COL_2,subjectname);
						contentValues.put(databasehelper.COL_3,status);
						contentValues.put(databasehelper.COL_4,date);
						contentValues.put(databasehelper.COL_5,studentBean.getStudent_firstname());

						long res= db.insert(databasehelper.TABLE_NAME,null,contentValues);
						Toast.makeText(getApplicationContext(),"savedddddddddddddddd",Toast.LENGTH_LONG).show();*/

						Toast.makeText(getApplicationContext(),Integer.toString(studentBean.getStudent_id())+subjectname+status+date+studentBean.getStudent_firstname(),Toast.LENGTH_LONG).show();
						DBAdapter dbAdapter = new DBAdapter(AddAttendanceActivity.this);
						dbAdapter.addNewAttendance(attendanceBean);
						
						dialog.dismiss();
						
					}
				});
				
				dialog.setCancelable(true);
				dialog.show();
			}
		});



	}

	public void insert(String student_id,String subjectname,String status,String date,String s) {
		db=helper.getWritableDatabase();
		ContentValues contentValues=new ContentValues();
		contentValues.put(databasehelper.COL_1,student_id);
		contentValues.put(databasehelper.COL_2,subjectname);
		contentValues.put(databasehelper.COL_3,status);
		contentValues.put(databasehelper.COL_4,date);
		contentValues.put(databasehelper.COL_5,s);

		long res= db.insert(databasehelper.TABLE_NAME,null,contentValues);
		Toast.makeText(getApplicationContext(),"savedddddddddddddddd",Toast.LENGTH_LONG).show();

	}


}
