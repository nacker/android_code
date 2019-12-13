package com.devlei.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void readDataClick(View v) {
        try {
            String path = "/data/data/com.it.qqlogin/files/qqinfo.txt";
            File file = new File(path);
            // 字符流
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line=reader.readLine();
            Log.v("it", line);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
