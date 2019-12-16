package com.devlei.demo;

import com.devlei.demo.bean.Person;

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
        //显示启动
        //传递一个商品id到新的界面 新的界面把商品id获取到并显示出来
        Intent intent=new Intent(this, MyActivity.class);
        //这里可以传递8大基本类型
        intent.putExtra("pid", 10086);
        startActivity(intent);
    }

    public void myClick2(View v){
        //传递一个对象给新的界面
        Intent intent=new Intent(this,MySecActivity.class);
        Person person=new Person("zhangsan", 24);
        intent.putExtra("person", person);
        startActivity(intent);
    }


}
