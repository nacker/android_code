package com.devlei.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn=(Button) findViewById(R.id.button1);
        btn.setOnClickListener(this);

        Button btn2=(Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(MainActivity.this, "点击事件2", 800).show();
            }
        });

        Button btn3=(Button) findViewById(R.id.button3);
        btn3.setOnClickListener(new MyClickListener());
    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View arg0) {
            Toast.makeText(MainActivity.this, "点击事件3", 800).show();
        }

    }

    @Override
    public void onClick(View arg0) {
        //Toast 吐司
        //duration 就是弹出的时间
        Toast.makeText(this, "还真的点我?", 800).show();
    }

    //一般不推荐 写法比较Low
    public void btn4Click(View v){
        Toast.makeText(this, "按钮4", 800).show();
    }

}
