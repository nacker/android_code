package com.devlei.demo;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    private static final int MAX_THREAD_COUNT = 3;
    private int mReadCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void downloadClick(View v){
        new Thread() {
            public void run() {
                try {
                    // 1.获取下服务器里面文件的大小 并且在手机创建一个一模一样大小的空文件 有一个长度
                    URL url = new URL(getUrlPath());
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    if (conn.getResponseCode() == 200) {
                        // 获取资源的文件大小
                        int contentLength = conn.getContentLength();
                        // RandomAccessFile 1.可以创建一个带内容空间的文件
                        File file = new File(getFilesDir(),
                                getFileName(getUrlPath()));
                        RandomAccessFile raf = new RandomAccessFile(file, "rw");
                        raf.setLength(contentLength);
                        // 2.创建n条线程 并且把文件分割成n等分(最后那条线程应该把剩下的内容读完)
                        // 知道了文件的长度还有线程数 就可以得到每个线程应该读取的内容大小
                        int perSize = contentLength / MAX_THREAD_COUNT;
                        Log.v("it", "contentLength:" + contentLength);
                        mReadCount=0;
                        for (int i = 0; i < MAX_THREAD_COUNT; i++) {
                            // startPosition每一个线程应该下载的起始位置
                            int startPosition = i * perSize;
                            int endPosition = (i + 1) * perSize - 1;
                            if (i == MAX_THREAD_COUNT - 1) {
                                endPosition = contentLength - 1;
                            }
                            Log.v("it", "startPosition:" + startPosition
                                    + "  endPosition:" + endPosition);
                            // 创建三个子线程进行下载
                            new DownLoadThread(i, startPosition, endPosition)
                                    .start();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    class DownLoadThread extends Thread {

        private int mStartPosition;
        private int mEndPosition;
        private int mThreadIndex;

        public DownLoadThread(int i, int startPosition, int endPosition) {
            mThreadIndex = i;
            mStartPosition = startPosition;
            mEndPosition = endPosition;
        }

        @Override
        public void run() {
            try {
                // 1.跟服务器谈一谈 我想下载某个文件某一部分
                URL url = new URL(getUrlPath());
                HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                //通过请求头告诉服务器
                //Range, "bytes=100-200" 就是告诉服务器 想下载文件从100~200字节的位置
                conn.setRequestProperty("Range", "bytes="+mStartPosition+"-"+mEndPosition);
                //如果只是获取文件的某部分 此时响应码不应该是200  而应该是206
                if (conn.getResponseCode()==206) {
                    InputStream is = conn.getInputStream();
                    // 2.告诉手机系统 想写某个文件的某一部分 原来的File是不存在这样的功能的
                    //RandomAccessFile 可以跳到文件某个位置之后开始写文件
                    File file=new File(getFilesDir(),getFileName(getUrlPath()));
                    RandomAccessFile raf=new RandomAccessFile(file, "rw");
                    raf.skipBytes(mStartPosition);//skipBytes 跳过了多少个字节
                    //3.开始写文件  数据的来源从InputStream来
                    byte[] buff=new byte[32];
                    int readCount;
                    while ((readCount=is.read(buff))>0) {
                        raf.write(buff, 0, readCount);
                    }
                    raf.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                mReadCount++;
                if (mReadCount==MAX_THREAD_COUNT) {
                    Log.v("it", "download finish !");
                }
            }
        }

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
