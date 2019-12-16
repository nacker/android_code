package com.devlei.demo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //获取上一个界面传递过来的值  getIntent()获取打开该界面的意图
        Intent intent = getIntent();
        //defaultValue 找不到的值
        int intExtra = intent.getIntExtra("pid", 0);

        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText(intExtra+"");

    }


}
