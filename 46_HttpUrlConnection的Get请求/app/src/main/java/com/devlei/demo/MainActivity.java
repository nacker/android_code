package com.devlei.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText mUserNameEt;
    private EditText mPwdEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserNameEt = (EditText) findViewById(R.id.username_et);
        mPwdEt = (EditText) findViewById(R.id.password_et);
    }

    public void loginClick(View v) {
        final String username = mUserNameEt.getText().toString();
        final String pwd = mPwdEt.getText().toString();
        new Thread() {
            public void run() {
                try {
                    // 1.有了网络地址
                    String urlPath = "http://192.168.1.10:8080/login/login?username="
                            + username + "&pwd=" + pwd;
                    URL url = new URL(urlPath);
                    // 2.打开Http链接
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    // 3.不只是想获取资源 还需要向服务器提交参数数据 告诉服务器到底是什么方式的请求
                    // Http1.0中 一般有5种请求方式的 比较流行的Get请求和Post请求
                    conn.setRequestMethod("GET");
                    // 4.判断响应码
                    if (conn.getResponseCode() == 200) {
                        // 5.读取输入流
                        InputStream is = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(is));
                        final String result = reader.readLine();
                        //6.返回主线程显示UI
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this, "登录的结果:" + result,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
