package com.devlei.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myClick(View v){
        //1.告诉系统 打开一个新的界面  意向 想做某些事
        Intent intent=new Intent(this, MyActivity.class);
        startActivity(intent);
    }
}
