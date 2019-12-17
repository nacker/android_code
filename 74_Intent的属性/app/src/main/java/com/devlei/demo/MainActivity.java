package com.devlei.demo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent 意图(代表要做某一件事)   3大组件 基本都是通过Intent来启动的  还可以传递数据
        //intent.setComponent(component)   指定显示启动的界面
        //intent.setClass(packageContext, cls)  指定显示启动的界面
        //intent.setAction(action)  指定隐式启动界面 (只要启动的条件能跟手机里所有的应用的注册文件内部的界面条件相匹配)
        //intent.addCategory(category)   隐式启动的时候附带给Action的信息
        //也是隐式启动的一种
        //intent.setData(data)   设置一些数据 这些数据跟系统有关系  http://   smsto://   tel://
        //intent.setType(type)   一般不会自己用的 设置MimeType
        //intent.setDataAndType(data, type)   设置数据  还有数据的显示方式  显示源码  以HTML的格式来显示
        //intent.putExtra(name, value) 传递数据的

    }

    public void myClick(View v){
//		Intent intent=new Intent(this,MyActivity.class);
        Intent intent=new Intent();
//		intent.setAction("打");
//		intent.addCategory("人");
//		intent.addCategory("其他");
//		intent.setComponent(new ComponentName(this,MyActivity.class));
//		intent.setClass(this,MyActivity.class);

//		intent.setData(data)

        startActivity(intent);
    }


}
