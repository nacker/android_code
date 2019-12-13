package com.devlei.demo;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.日志的原理
        //System.out.println("haha");
        //2.等级拦截器
        Log.v("it", "verbose");//黑色
        Log.d("it", "debug");//蓝色
        Log.i("it", "info");//绿色
        Log.w("it", "warn");//橙色
        Log.e("it", "error");//红色
        //不同等级下的显示的日志不同
        //等级从低到高 verbose-->debug-->info--->warn-->error
        //等级拦截器中 越高等级的拦截不能拦截比它低等级的日志
        //3.静态过滤器
        Log.v("it", "it verbose");//黑色
    }

}
