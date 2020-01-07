package com.devlei.netease.splash.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.devlei.netease.R;
import com.devlei.netease.splash.bean.Action;


public class WebViewActivity extends Activity {

     public static final String ACTION_NAME = "action";

     WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Action action = (Action) getIntent().getSerializableExtra(ACTION_NAME);
        setContentView(R.layout.activity_webview);
        webview = (WebView) findViewById(R.id.webview);

        //启用javaScript
        webview.getSettings().setJavaScriptEnabled(true);


        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);

        webview.loadUrl(action.getLink_url());

        //处理url重定向不要抛到系统浏览器
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Log.i("it520","onReceivedError");
                super.onReceivedError(view, request, error);

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.i("it520","onReceivedError2 ");

            }
        });
    }

    @Override
    public void onBackPressed() {
        //如果webviwe能够回退到上一个页面
        if(webview.canGoBack()){
            webview.goBack();
            return;
        }
        super.onBackPressed();
    }
}
