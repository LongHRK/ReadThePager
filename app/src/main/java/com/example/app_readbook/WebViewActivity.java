package com.example.app_readbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        web = findViewById(R.id.webview);

        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        web.loadUrl(link);
        web.setWebViewClient(new WebViewClient());
    }
}