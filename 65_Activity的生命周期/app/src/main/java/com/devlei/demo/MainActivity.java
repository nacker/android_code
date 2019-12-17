package com.devlei.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

//音乐播放器
public class MainActivity extends Activity {

    private boolean ifPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("it", "开始播放音乐");
    }

    //界面可见的时候被调用的
    @Override
    protected void onStart() {
        super.onStart();
        if (ifPause) {
            Log.v("it", "从当前暂停的位置重新播放音乐");
            ifPause=false;
        }
    }

    //界面不可见的时候被调用了
    @Override
    protected void onStop() {
        super.onStop();
        Log.v("it", "暂停播放音乐 记录当前播放的进度");
        ifPause=true;
    }

}
