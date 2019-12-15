package com.devlei.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
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
                    // HttpUrlConnection方式API 面向过程
                    // HttpClient是一个面向对象(接口)的API
                    // 1.创建了一个浏览器
                    HttpClient client = new DefaultHttpClient();
                    // 2.声明一个Get请求
                    String urlPath = "http://192.168.1.10:8080/login/login?"
                            + "username=" + username + "&pwd=" + pwd;
                    HttpGet request = new HttpGet(urlPath);
                    // 3.通过浏览器执行一个HttpGet请求 返回了一个HttpResponse响应对象
                    // HttpUriRequest 这里封装Http请求的几种请求方式
                    HttpResponse response = client.execute(request);
                    // 4.返回结果 响应码==200
                    int responseCode = response.getStatusLine().getStatusCode();
                    if (responseCode == 200) {
                        // 5.获取响应的数据 HttpEntity实体 该对象了 返回的数据
                        HttpEntity entity = response.getEntity();
                        InputStream is = entity.getContent();
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(is));
                        final String line = reader.readLine();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this, line,
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

    public void loginClickByPost(View v) {
        // 1.拿到两个EditText的值
        final String username = mUserNameEt.getText().toString();
        final String pwd = mPwdEt.getText().toString();
        new Thread() {
            public void run() {
                try {
                    //1.创建一个浏览器
                    HttpClient client=new DefaultHttpClient();
                    //2.声明一个HttpPost请求
                    String urlPath="http://192.168.1.10:8080/loginPost/login";
                    HttpPost request=new HttpPost(urlPath);
                    //3.Post是将数据添加到Form表单中  思考:怎么将数据添加到请求里面
                    //不管是请求的数据 还是返回来的数据 所有的数据都封装在HttpEntity里面
                    //Pair 双    BasicNameValuePair键值对
                    ArrayList<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
                    parameters.add(new BasicNameValuePair("username", username));
                    parameters.add(new BasicNameValuePair("pwd", pwd));
                    HttpEntity entity=new UrlEncodedFormEntity(parameters, "utf-8");
                    request.setEntity(entity);
                    //4.执行一个HttpPost请求
                    HttpResponse response = client.execute(request);
                    //5.判断响应码
                    int responseCode = response.getStatusLine().getStatusCode();
                    if (responseCode==200) {
                        //6.读取服务器里面的数据
                        HttpEntity responseEntity = response.getEntity();
                        InputStream is = responseEntity.getContent();

                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(is));
                        final String line = reader.readLine();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this, line,
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
