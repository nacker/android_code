package com.devlei.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //System.out.println("onCreate");
        //1.查找控件
        Button btn=(Button) findViewById(R.id.btn);
        //2.设置监听
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            //3.点击事件
            public void onClick(View arg0) {
                System.out.println("onClick");
            }
        });
    }
}
