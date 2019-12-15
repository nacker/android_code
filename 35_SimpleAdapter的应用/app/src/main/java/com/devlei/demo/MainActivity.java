package com.devlei.demo;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.lv);
//		什么时候使用SimpleAdapter都可以
//		1. datas代表整个列表的数据
        ArrayList<HashMap<String, Object>> datas=
                new ArrayList<HashMap<String,Object>>();
        for (int i = 1; i < 7; i++) {
            //2.data代表初始化每一项的数据
            HashMap<String, Object> data=new HashMap<String, Object>();
            data.put("图片", R.drawable.a2);
            data.put("文字", "测试"+i);
            datas.add(data);
        }
        //3.datas 代表数据已经有了
        //4.R.layout.lv_item_layout 每一项的布局已经准备好了 这里给的是一个容器
        //5.想对容器里面每一个控件进行数据绑定
        //5.1  找到要绑定值的控件id   new int[]{R.id.iv,R.id.tv}
        //5.2 循环遍历datas列表 找到里面的HashMap 通过new String[]{"图片","文字"} 找到对应的值
        //5.3 将控件与数据进行绑定
        SimpleAdapter adapter=new SimpleAdapter(
                this,
                datas,
                R.layout.lv_item_layout,
                new String[]{"图片","文字"},
                new int[]{R.id.iv,R.id.tv});
        lv.setAdapter(adapter);
    }

}
