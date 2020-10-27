package com.example.studentdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class forgetpassword extends AppCompatActivity{
    EditText uptfirstname,uptlastname,uptadrr,uptpass,uptusername,uptmob;
    Spinner uptyea,uptbdepy;
    Button updatebtn;
    SQLiteOpenHelper helper,helper2;
    SQLiteDatabase db,db2,db4,db5;
String uptfirstname1,uptlastname1,uptadrr1,uptpass1,uptusername1,uptmob1,uptyea1,uptbdepy1,update1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        uptfirstname=findViewById(R.id.updateFirstName);
        uptlastname=findViewById(R.id.updateLastName);
        uptadrr=findViewById(R.id.updateaddr);
        uptpass=findViewById(R.id.updatepass);
        uptmob=findViewById(R.id.updateTextPhone);

        updatebtn=findViewById(R.id.updateRegisterButton);
        uptusername=findViewById(R.id.updateName);






        helper=new databasehelper3(this);
        helper2=new databasehelper2(this);
        db2=helper2.getWritableDatabase();

        db=helper.getWritableDatabase();
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uptfirstname1=uptfirstname.getText().toString();
                uptlastname1=uptlastname.getText().toString();
                uptadrr1=uptadrr.getText().toString();
                uptpass1=uptpass.getText().toString();
                uptmob1=uptmob.getText().toString();
                uptusername1=uptusername.getText().toString();
            /*   Cursor cursor= db.rawQuery(" SELECT * FROM "+databasehelper3.TABLE_NAME+" WHERE "+ databasehelper3.COL_2+"=?"+"AND "+databasehelper3.COL_3+"=?"+"AND "+databasehelper3.COL_5+"=?"+"AND "+databasehelper3.COL_4+"=?"+"AND "+databasehelper3.COL_6+"=?",new String[]{uptfirstname1,uptlastname1,uptmob1,uptadrr1,uptlastname1});
                       while (cursor.moveToFirst())
                       {
                           String one=cursor.getString(1);
                           String two= cursor.getString(2);
                           String three= cursor.getString(3);
                           String four= cursor.getString(4);
                           String five=cursor.getString(5);
                           String six=cursor.getString(6);
                           db4=helper.getWritableDatabase();


                       }*/
                ContentValues contentValues=new ContentValues();
                contentValues.put(databasehelper3.COL_7,uptpass1);
                ContentValues values2=new ContentValues();
                values2.put(databasehelper2.COL_3,uptpass1);
                  /*     db.execSQL(" UPDATE "+databasehelper3.TABLE_NAME+" SET "+databasehelper3.COL_7+"="+uptpass1+" WHERE "+databasehelper3.COL_6+"=?"+" AND "+databasehelper3.COL_5+"=?",new String[]{uptusername1,uptmob1});
                db2.execSQL(" UPDATE "+databasehelper2.TABLE_NAME+" SET "+databasehelper2.COL_3+"="+uptpass1+" WHERE "+databasehelper2.COL_2+"=?",new String[]{uptusername1});*/
             // db.update(databasehelper3.TABLE_NAME, contentValues,databasehelper3.COL_6+"=? ",new String[]{uptusername1});
                db.update(databasehelper3.TABLE_NAME,contentValues,"USERNAME = ?",new String[]{uptusername1});
           //  db2.update(databasehelper2.TABLE_NAME, values2,databasehelper2.COL_2+"=? ",new String[]{uptusername1});
                db2.update(databasehelper2.TABLE_NAME,values2,"USER_NAME = ?",new String[]{uptusername1});
                Toast.makeText(forgetpassword.this, "updated"+uptusername1+uptmob1+uptadrr1+uptfirstname1+uptlastname1+uptpass1, Toast.LENGTH_SHORT).show();

            }
        });



    }

    /*public boolean updateStudents(int id, String name, int age, String city){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);
        contentValues.put(CITY,city);
        db.update(TABLE_STUDENTS, contentValues, ID + " = ? " ,
                new String[]{String.valueOf(id)});

        return true;
    }*/

}