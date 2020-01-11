package com.devlei.step.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.devlei.step.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 全屏 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 隐藏标题栏 Activity
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏标题栏 AppCompatActivity
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_splash);

        // 匿名Handler创建一个延时的调用
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, MainActivity.class);  //从启动动画ui跳转到主界面
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish(); // 结束启动界面
            }
        }, 1000 * 3);  // 启动动画持续3秒钟
    }
}
