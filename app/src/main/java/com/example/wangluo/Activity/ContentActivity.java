package com.example.wangluo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wangluo.R;

public class ContentActivity extends AppCompatActivity {
String getURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intent=getIntent();
        getURL=intent.getStringExtra("URL");
        WebView webView = (WebView) findViewById(R.id.web_content);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(getURL);
    }
}
