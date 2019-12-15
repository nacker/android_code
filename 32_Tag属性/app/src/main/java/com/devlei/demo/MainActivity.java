package com.devlei.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn =(Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        //往Button里面绑定一个对象
        mBtn.setTag("haha");
    }

    //View v 传进来的View就是那个被点击的View
    @Override
    public void onClick(View v) {
        //取出绑定的对象
        String tag = (String) v.getTag();
        Toast.makeText(this, tag, 0).show();
    }

}
