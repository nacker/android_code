package com.devlei.demo;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mWeatherTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWeatherTv = (TextView) findViewById(R.id.weather_tv);
    }

    public void parseXmlClick(View v) throws Exception{
        String result="";
        //1.找到文件  AssetManager资源的管理器
        AssetManager assets = getAssets();
        //在assets目录下找到某个文件 将某个文件读进内存中
        InputStream fis = assets.open("weather.xml");
        //2.创建一个Pull解析器
        XmlPullParser pullParser=Xml.newPullParser();
        //3.绑定流
        pullParser.setInput(fis, "utf-8");
        //4.开始解析文档  事件解析-->获取一开始的事件类型
        int eventType = pullParser.getEventType();
        //5.做一个while循环 结束条件:直到读取到文档结束
        while (eventType!=XmlPullParser.END_DOCUMENT) {
            //7.读取一行数据之后 就存储到result变量中
            if (eventType==XmlPullParser.START_TAG
                    &&pullParser.getName().equals("string")) {
                result+=pullParser.nextText()+"\n";
            }
            //6.做了一个循环遍历
            eventType=pullParser.next();
        }
        mWeatherTv.setText(result);
    }



}
