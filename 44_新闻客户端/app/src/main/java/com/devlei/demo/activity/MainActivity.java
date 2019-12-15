package com.devlei.demo.activity;

import java.io.InputStream;
import java.util.ArrayList;

import com.devlei.demo.R;
import com.devlei.demo.R.id;
import com.devlei.demo.R.layout;
import com.devlei.demo.adapter.NewsAdapter;
import com.devlei.demo.bean.NewsBean;
import com.devlei.demo.ui.ImageViewWithUrl;
import com.devlei.demo.util.NetworkUtil;
import com.devlei.demo.util.XmlUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListView mNewsLv;
    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsLv =(ListView) findViewById(R.id.lv);
        mAdapter = new NewsAdapter();
        mNewsLv.setAdapter(mAdapter);

        new Thread(){
            public void run() {
                getNetworkResource();
            }
        }.start();

    }

    private void getNetworkResource() {
        try {
            String urlPath="http://192.168.1.10:8080/news.xml";
            //1.网络获取资源
            InputStream is = NetworkUtil.getResource(urlPath);
            if (is!=null) {
                //2.给InputStream 通过Pull解析器进行解析
                ArrayList<NewsBean> newsBeans = XmlUtil.parseNews(is);
                mAdapter.setDatas(newsBeans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
