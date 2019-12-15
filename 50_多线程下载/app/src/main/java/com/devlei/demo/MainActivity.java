package com.devlei.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final int MAX_THREAD_COUNT = 3;
    private int mReadCount;
    private LinearLayout mPbContainerLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPbContainerLl = (LinearLayout) findViewById(R.id.pbcontainer_ll);
    }

    public void downloadClick(View v){
        //删除所有子控件
        mPbContainerLl.removeAllViews();
        //ProgressBar有多少个 取决线程数量  max progress
        for (int i = 0; i < MAX_THREAD_COUNT; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.pb_layout, null);
            mPbContainerLl.addView(view);
        }
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
        private int mLastPosition;//如果没有记录文件 它的位置就在mStartPosition 如果有记录文件 它的位置就是记录位置中的位置

        public DownLoadThread(int i, int startPosition, int endPosition) {
            mThreadIndex = i;
            mStartPosition = startPosition;
            mEndPosition = endPosition;
            mLastPosition=startPosition;
        }

        @Override
        public void run() {
            //PbContaierLl 有多个ProgressBar
            //getChildAt 找到容器里面的子控件
            View view = mPbContainerLl.getChildAt(mThreadIndex);
            final ProgressBar pb=(ProgressBar) view.findViewById(R.id.progressBar1);
            runOnUiThread(new Runnable() {
                public void run() {
                    pb.setMax(mEndPosition-mStartPosition);
                }
            });
            try {
                //判断有没当前线程的记录进度的文件 如果有 那么最新的下载位置应该从记录文件的位置中来找
                File lastRecordFile=getRecordFile(mThreadIndex, getFileName(getUrlPath()));
                if (lastRecordFile.exists()&&lastRecordFile.length()>0) {
                    FileInputStream fis=new FileInputStream(lastRecordFile);
                    BufferedReader reader=new BufferedReader(new InputStreamReader(fis));
                    mLastPosition=Integer.parseInt(reader.readLine());
                }

                Log.v("it", "实际开始下载:  mLastPosition:" + mLastPosition
                        + "  mEndPosition:" + mEndPosition);

                // 1.跟服务器谈一谈 我想下载某个文件某一部分
                URL url = new URL(getUrlPath());
                HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                //通过请求头告诉服务器
                //Range, "bytes=100-200" 就是告诉服务器 想下载文件从100~200字节的位置
                conn.setRequestProperty("Range", "bytes="+mLastPosition+"-"+mEndPosition);
                //如果只是获取文件的某部分 此时响应码不应该是200  而应该是206
                if (conn.getResponseCode()==206) {
                    InputStream is = conn.getInputStream();
                    // 2.告诉手机系统 想写某个文件的某一部分 原来的File是不存在这样的功能的
                    //RandomAccessFile 可以跳到文件某个位置之后开始写文件
                    File file=new File(getFilesDir(),getFileName(getUrlPath()));
                    RandomAccessFile raf=new RandomAccessFile(file, "rw");
                    raf.skipBytes(mLastPosition);//skipBytes 跳过了多少个字节
                    //3.开始写文件  数据的来源从InputStream来
                    byte[] buff=new byte[1024];
                    int readCount;
                    while ((readCount=is.read(buff))>0) {
                        raf.write(buff, 0, readCount);
                        mLastPosition+=readCount;
                        //保存一下mLastPosition大小
                        File recordFile=getRecordFile(mThreadIndex, getFileName(getUrlPath()));
                        //有没有一种方式可以实时保存当前的文件进度
                        //RandomAccessFile 可以实时的写文件到硬盘中  模式rwd rws都可以实时将内存中的数据刷新的硬盘中
                        RandomAccessFile recordRaf=new RandomAccessFile(recordFile, "rwd");
                        recordRaf.write((mLastPosition+"").getBytes());

                        runOnUiThread(new Runnable() {
                            public void run() {
                                pb.setProgress(mLastPosition-mStartPosition);
                            }
                        });

                    }
                    raf.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                mReadCount++;
                if (mReadCount==MAX_THREAD_COUNT) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this,
                                    "download finish !", 0).show();
                        }
                    });
                    //1.删除记录文件
                    for (int i = 0; i < MAX_THREAD_COUNT; i++) {
                        File recordFile = getRecordFile(i,getFileName(getUrlPath()));
                        recordFile.delete();
                    }
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

    /**
     * 为每一条线程创建一个记录当前进度文件
     * */
    private File getRecordFile(int threadIndex,String fileName){
//		resource#2.txt
//		resource.rar
        fileName=fileName.substring(0, fileName.lastIndexOf("."));
        fileName+=("#"+threadIndex+".txt");
        return new File(getFilesDir(),fileName);
    }

}
