package com.example.studentdiary.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentdiary.MainActivity;
import com.example.studentdiary.MainActivity3;
import com.example.studentdiary.bean.AttendanceBean;
import com.example.studentdiary.bean.AttendanceSessionBean;
import com.example.studentdiary.bean.FacultyBean;
import com.example.studentdiary.bean.StudentBean;
import com.example.studentdiary.context.ApplicationContext;
import com.example.studentdiary.databasehelper;
import com.example.studentdiary.databasehelper2;
import com.example.studentdiary.db.DBAdapter;
import com.example.studentdiary.R;


public class LoginActivity extends Activity {
	SQLiteOpenHelper helper;
	SQLiteDatabase db;
	Button login;
	EditText username,password;
	Spinner spinnerloginas;
	String userrole;
	private String[] userRoleString = new String[] { "admin", "faculty","Student"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		login =(Button)findViewById(R.id.buttonlogin);
		username=(EditText)findViewById(R.id.username);
		password=(EditText)findViewById(R.id.password);
		spinnerloginas=(Spinner)findViewById(R.id.spinnerloginas);
        helper=new databasehelper2(this);
		spinnerloginas.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
				// TODO Auto-generated method stub
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				userrole =(String) spinnerloginas.getSelectedItem();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		ArrayAdapter<String> adapter_role = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, userRoleString);
		adapter_role
		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerloginas.setAdapter(adapter_role);

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(userrole.equals("admin"))
				{

					String user_name = username.getText().toString();
					String pass_word = password.getText().toString();

					if (TextUtils.isEmpty(user_name))
					{
						username.setError("Invalid User Name");
					}
					else if(TextUtils.isEmpty(pass_word))
					{
						password.setError("enter password");
					}
					else
					{
						if(user_name.equals("admin") & pass_word.equals("admin123")){
						Intent intent =new Intent(LoginActivity.this, MenuActivity.class);
						startActivity(intent);
						Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
						}
					}
				}
				
				else if(userrole.equals("faculty"))
				{
					String user_name = username.getText().toString();
					String pass_word = password.getText().toString();

					if (TextUtils.isEmpty(user_name))
					{
						username.setError("Invalid User Name");
					}
					else if(TextUtils.isEmpty(pass_word))
					{
						password.setError("enter password");
					}
					DBAdapter dbAdapter = new DBAdapter(LoginActivity.this);
					FacultyBean facultyBean = dbAdapter.validateFaculty(user_name, pass_word);
					
					if(facultyBean!=null)
					{
						Intent intent = new Intent(LoginActivity.this,AddAttandanceSessionActivity.class);
						startActivity(intent);
						((ApplicationContext)LoginActivity.this.getApplicationContext()).setFacultyBean(facultyBean);
						Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
					}
				}
				else {
					String user_name = username.getText().toString();
					String pass_word = password.getText().toString();

					if (TextUtils.isEmpty(user_name))
					{
						username.setError("Invalid User Name");
					}
					else if(TextUtils.isEmpty(pass_word))
					{
						password.setError("enter password");
					}
					else

					{
						db=helper.getReadableDatabase();
						Cursor cursor=db.rawQuery("SELECT * FROM "+databasehelper2.TABLE_NAME+" WHERE "+databasehelper2.COL_2+"=?"+" AND "+databasehelper2.COL_3+"=?",new String[]{username.getText().toString(),password.getText().toString()});
						if(cursor.getCount()>0)
						{
							cursor.moveToFirst();
							Intent intent=new Intent(getApplicationContext(),MainActivity3.class);
							intent.putExtra("id",cursor.getString(0));
							intent.putExtra("name",username.getText().toString());
							Toast.makeText(getApplicationContext(),cursor.getString(0)+"kkkkkkkkkkkkkkkkkkkkkk",Toast.LENGTH_LONG).show();
							startActivity(intent);
						}
						else{
							Toast.makeText(getApplicationContext(),"user does not exist",Toast.LENGTH_LONG).show();
						}
					}

				}


			}
		});



	}



}
