package com.devlei.demo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView wv = (WebView) findViewById(R.id.wv);
//		1.加载网页
        wv.loadUrl("http://www.baidu.com/");
//		2.处理跳转打开新的浏览器的问题
        wv.setWebViewClient(new WebViewClient(){
            @Override
//			url 点击的时候新的网址
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
//				3.告诉系统不要打开新的浏览器了 返回值 返回了true 表示告诉系统我们自己处理
                return true;
            }
        });
//		4.网页控件默认不支持JavaScript 需要用户打开支持的开关
        wv.getSettings().setJavaScriptEnabled(true);
//		5.网页控件默认不支持弹出框 alert() prompt() confirm()... 但是Html5的弹出框是默认支持的
        wv.setWebChromeClient(new WebChromeClient(){
            //			虽然不支持js的弹出框 但是会回调如下的方法
            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
//				在这个方法里面可以用原生弹出框来代替
                return super.onJsAlert(view, url, message, result);
            }

        });
    }


}
