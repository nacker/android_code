package com.devlei.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mXmlTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mXmlTv = (TextView) findViewById(R.id.textView1);
    }

    public void parseXmlClick(View v){
        try {
            String reslut = XmlUtil.parseXmlFile(this);
            mXmlTv.setText(reslut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

