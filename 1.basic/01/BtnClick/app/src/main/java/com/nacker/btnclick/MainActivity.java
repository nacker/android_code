package com.nacker.btnclick;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.bt1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("第一个按钮被点击了");
            }
        });

        Button bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        System.out.println("第二个按钮被点击了");
    }

    //View：系统会把触发这个方法的那个组件的对象作为view对象传进来
    public void getScore(View v){
        //通过对view对象的判断，就可以知道用户点击的到底是哪一个按钮
        //拿到按钮的id
        int id = v.getId();
        switch (id) {
            case R.id.wangzhe:
                System.out.println("下辈子吧");
                break;
            case R.id.diamond:
                System.out.println("凑合凑合");
                break;
            case R.id.master:
                System.out.println("想想就好");
                break;

        }
    }
}
