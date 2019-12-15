package com.devlei.demo;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.http.Header;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

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
        //如下的操作自带了子线程
        //初始化了一个浏览器
        AsyncHttpClient client=new AsyncHttpClient();
        //执行Post请求
        //url url地址
        String urlPath="http://192.168.1.10:8080/loginPost/login";
        //params RequestParams请求的参数
        RequestParams params=new RequestParams();
        params.put("username", username);
        params.put("pwd", pwd);
        //responseHandler 响应监听器
//		ResponseHandlerInterface
        client.post(urlPath, params, new TextHttpResponseHandler() {

            //响应码200
            //该方法下的代码 是在主线程中运行
            //responseString 就是服务器返回的数据
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  String responseString) {
                Toast.makeText(MainActivity.this, responseString, 0).show();
            }

            //响应码 404 500 505
            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {

            }
        });
    }

    /**
     * 文件上传
     * @throws FileNotFoundException
     */
    public void uploadClick(View v) throws FileNotFoundException{
        AsyncHttpClient client=new AsyncHttpClient();
        String urlPath="http://192.168.1.10:8080/upload/upload";
        RequestParams params=new RequestParams();
        params.put("fName", "phone.png");
        File file=new File(getFilesDir(),"aa.png");
        params.put("file", file);
        client.post(urlPath, params, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  String responseString) {
                Toast.makeText(MainActivity.this, responseString, 0).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {

            }
        });

    }

}

