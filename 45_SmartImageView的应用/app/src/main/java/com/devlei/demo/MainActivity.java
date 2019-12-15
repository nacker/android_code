package com.devlei.demo;

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    private SmartImageView mSmartImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSmartImageView =(SmartImageView) findViewById(R.id.sm_iv);
        mSmartImageView.setImageUrl("http://192.168.1.10:8080/img/a.png",
                R.drawable.ic_launcher);
    }


}

