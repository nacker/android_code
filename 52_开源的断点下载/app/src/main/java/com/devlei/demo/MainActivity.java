package com.devlei.demo;

import java.io.File;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void downloadClick(View v) {
        HttpUtils httpUtils = new HttpUtils();
        // url url地址
        // target 指定下载位置
        File file = new File(getFilesDir(), getFileName(getUrlPath()));
        // autoResume 断点续传
        // callback 下载完成回调接口
        // RequestCallBack
        httpUtils.download(getUrlPath(), file.getAbsolutePath(), true,
                new RequestCallBack<File>() {

                    @Override
                    public void onSuccess(ResponseInfo<File> responseInfo) {
                        Toast.makeText(MainActivity.this, "下载完成!", 0).show();
                        Log.v("520it", "下载完成!");
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {

                    }
                });
    }

    /**
     * 获取网络地址
     */
    private String getUrlPath() {
        return "http://192.168.1.10:8080/resource.rar";
    }

    /**
     * 根据资源的网络地址获取资源的文件名
     */
    private String getFileName(String urlPath) {
        return urlPath.substring(urlPath.lastIndexOf("/") + 1);
    }

}
