package com.devlei.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("it", "onCreate 界面被创建的时候调用的");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("it", "onStart 界面可见的时候调用的");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.v("it", "onResume 界面可见的 并且能够获取焦点(能跟用户进行交互)时候调用的");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("it", "onPause 界面可见的 但是无法操作(无法获取焦点)时候调用的");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("it", "onStop 界面不可见的时候调用的");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("it", "onDestroy 界面被销毁的时候调用的");
    }

    public void myClick(View v){
        Intent intent=new Intent(this,MyActivity.class);
        startActivity(intent);
    }


}
