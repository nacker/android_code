package com.devlei.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void downloadClick(View v){
        try {
            //下载一个空的文件 1024*1024*10 到SD卡
            //1.SD卡是可以插拔的 判断下SD卡是否装载
            String externalState = Environment.getExternalStorageState();
            if (externalState.equals(Environment.MEDIA_MOUNTED)) {
                //2.创建一个空的文件
                File parentFile = Environment.getExternalStorageDirectory();
                File file=new File(parentFile, "葫芦娃.avi");
                //字节流
                FileOutputStream fos=new FileOutputStream(file);
                byte[] buff=new byte[1024*1024];
                for (int i = 0; i < 10; i++) {
                    fos.write(buff);
                }
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

