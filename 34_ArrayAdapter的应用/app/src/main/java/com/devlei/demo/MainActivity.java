package com.devlei.demo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv= (ListView) findViewById(R.id.lv);

        ArrayList<String> datas=new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            datas.add("我很帅"+i);
        }

//		ArrayAdapter	当一个列表里面只有显示一个TextView 就可以直接使用它
//		resource 子项布局
//		textViewResourceId 就是说要将每一项数据绑定到哪个TextView下 这里提供一个TextView的id
//		datas 就是列表数据
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                datas);
        lv.setAdapter(adapter);
    }

}
