package com.devlei.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("it", "模拟普通的代码");
        Log.v("it", "模拟普通的代码");
        for (int i = 0; i < 3; i++) {
            if (i==1) {
                throw new NullPointerException("模拟的异常");
            }
            Log.v("it", "循环的代码 ");
        }
        Log.v("it", "模拟普通的代码");
        test();
        Log.v("it", "模拟普通的代码");

    }

    private void test() {
        Log.v("it", "方法里面的代码");
        Log.v("it", "方法里面的代码");
    }


}
