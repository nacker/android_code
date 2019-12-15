package com.devlei.demo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.有了UI控件了
        ListView lv = (ListView) findViewById(R.id.lv);
        //2.创建数据
        ArrayList<String> datas=new ArrayList<String>();
        for (int i = 0; i < 300; i++) {
            datas.add("测试"+i);
        }
        //3.控件与数据如何绑定
        MyAdapter adapter=new MyAdapter(this,datas);
        lv.setAdapter(adapter);
    }

    private void normalMethod() {
        LinearLayout containerLl = (LinearLayout) findViewById(R.id.container_ll);
        //动态添加TextView  300
        int MAX_CHILD_COUNT=300;
        for (int i = 0; i < MAX_CHILD_COUNT; i++) {
            TextView tv=new TextView(this);
            //为TextView设置宽高  TextView需要添加到哪个容器下 就使用哪个容器的LayoutParams
            tv.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
            //为TextView设置字体大小
            tv.setTextSize(30);//px
            tv.setTextColor(0xFFFF0000);
            tv.setText("测试 "+i);
            //将TextView添加到容器里面来
            containerLl.addView(tv);
            //TextView 此时创建了300次  耗费性能
        }
    }


}
