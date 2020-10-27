package com.example.studentdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class videofullscreen extends AppCompatActivity {



    WebView webView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videofullscreen);
        getSupportActionBar().hide();
        webView2=findViewById(R.id.full_video);
        Intent i=getIntent();
        String url=i.getStringExtra("link");
        Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
        webView2.loadUrl(url);
        webView2.setWebViewClient(new WebViewClient());
        webView2.setWebChromeClient(new WebChromeClient());
         webView2.getSettings().setJavaScriptEnabled(true);
    }


}