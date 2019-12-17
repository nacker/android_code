package com.devlei.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //1.在广播电台的应用中 点击按钮后发送一个广播消息
    public void sendBroadcastClick(View v){
        //隐式意图  如果调用的组件是跨应用 因为你没办法拿到别人的组件名
        Intent intent=new Intent("com.devlei.demo.action.SENDRADIO");
        sendBroadcast(intent);
    }
}
