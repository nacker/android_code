package com.devlei.demo;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 当其他的应用 比如短信应用 该应用点击了网址之后 进入到我们创建的浏览器上
        // 获取URL地址 并且展示网页
        EditText urlEt = (EditText) findViewById(R.id.url_et);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null) {
            String url = data.toString();
            urlEt.setText(url);
            webView.loadUrl(url);
        }

    }

}
