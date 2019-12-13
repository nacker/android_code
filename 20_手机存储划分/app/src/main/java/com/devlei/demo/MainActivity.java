package com.devlei.demo;

import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.内部存储 	/data
        File dataFile = Environment.getDataDirectory();
        Log.v("it", dataFile.getAbsolutePath());
        //2.外部存储
        // /storage/emulated/0
        // /mnt/sdcard
        File extralFile = Environment.getExternalStorageDirectory();
        Log.v("it", extralFile.getAbsolutePath());
    }

}
