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
    }

    public void myClick(View v){
        //点击按钮的时候启动一个新的界面Activity
        //如果现在按下去 那么新的界面肯定会覆盖老的界面 这样就不能看见老的界面 那就不算暂停
        //解决:能不能将新的界面变成透明的界面
        Intent intent=new Intent(this,MyActivity.class);
        startActivity(intent);
    }

    //暂停的时候调用
    @Override
    protected void onPause() {
        super.onPause();
        Log.v("it", "onPause");
    }

    //恢复的时候调用的
    @Override
    protected void onResume() {
        super.onResume();
        Log.v("it", "onResume");
    }


}

