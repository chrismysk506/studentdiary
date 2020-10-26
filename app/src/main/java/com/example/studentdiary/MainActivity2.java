package com.example.studentdiary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity2 extends AppCompatActivity {
    Button upload,browse;
    TextView filesstatus;
    ImageView imageshow;
    EditText filesname;
    FirebaseDatabase database;
    FirebaseStorage storage;
    public Uri pdfurl;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        upload=findViewById(R.id.uploadsButton);
        browse=findViewById(R.id.brosweButton);
        filesstatus=findViewById(R.id.Nofile);
        imageshow=findViewById(R.id.pdfimg);
        filesname=findViewById(R.id.filesName);

        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pdfurl!=null)
                {
                    Toast.makeText(MainActivity2.this,"done file",Toast.LENGTH_LONG).show();
                    uploadpdf(pdfurl);
                }
                else
                {
                    Toast.makeText(MainActivity2.this,"choose file",Toast.LENGTH_LONG).show();
                }
            }
        });

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
                    selectpdf();
                }
                else
                {
                    ActivityCompat.requestPermissions(MainActivity2.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},2);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==2&& grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            selectpdf();
        }
        else
        {
            Toast.makeText(MainActivity2.this,"allow permissions to read storage",Toast.LENGTH_LONG).show();
        }
    }

    private void selectpdf() {

        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select pdf file"),1);
      //  imageshow.setVisibility(View.VISIBLE);

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && data!=null && resultCode== Activity.RESULT_OK && data.getData()!=null)
        {
            pdfurl=data.getData();

            filesstatus.setText("file is selected"+"name "+data.getData().getLastPathSegment());
            imageshow.setVisibility(View.VISIBLE);
        }
        else
        {
            filesstatus.setText("No file selected");
        }
    }




    private void uploadpdf(Uri data) {
        progressDialog=new ProgressDialog(this);

        final String filename=System.currentTimeMillis()+"";
        progressDialog.setTitle("uploading..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.show();

        Toast.makeText(getApplicationContext(),"progress end",Toast.LENGTH_LONG).show();



        Toast.makeText(getApplicationContext(),"storage start",Toast.LENGTH_LONG).show();

        StorageReference reference= storage.getReference("uploads");//root path
        reference.child(filename).putFile(pdfurl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask= taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri uri =uriTask.getResult();
                String url =uri.toString();

                //  helper help = new helper(file.getText().toString(), url);
                DatabaseReference reference1=database.getReference();
                reference1.child(filesname.getText().toString()).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"url uploaded",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"error in url",Toast.LENGTH_LONG).show();
                        }
                    }
                });

                Toast.makeText(getApplicationContext(),"uploaded sucessfully",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
               /* FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.navcontainer, new HomeFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
               imageshow.setVisibility(View.VISIBLE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"uploaded  not sucessfully",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int current=(int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(current);


            }
        });
    }

}