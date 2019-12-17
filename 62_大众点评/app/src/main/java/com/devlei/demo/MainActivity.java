package com.devlei.demo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //为什么要使用隐式意图 当应用只想跟其他应用交互的时候 才会用到
    public void myClick(View v){
        //1.打开系统的短信界面
        Intent intent=new Intent(Intent.ACTION_VIEW);
//		smsto:10086
        intent.setData(Uri.parse("smsto:10000"));
        //2.写一些默认的发送的短信内容    发送数据---->对应的短信界面接收数据
        intent.putExtra("subject", "大众点评 催单");
        intent.putExtra("sms_body", "快点送货 饿死了!");
        startActivity(intent);
    }

}

