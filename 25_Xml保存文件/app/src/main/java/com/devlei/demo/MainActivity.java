package com.devlei.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void saveXmlClick(View v){
        try {
            //  包名/files目录
            String passport="zhangsan";
            String password="123";
//			XmlUtil.saveXmlByStringBuilder(this,passport, password);
            XmlUtil.saveXmlByXmlSerializer(this, passport, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
