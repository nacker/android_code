package com.devlei.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView mImageView;
    private ArrayList<String> mImageUrlPaths=new ArrayList<String>();
    private int mCurrentIndex;
    //1.在主线程中创建了一个Handler对象
    private Handler mHandler=new Handler(){

        //3.在主线程中处理消息
        public void handleMessage(Message msg) {
//			bmp 能不能从子线程中传过来
            mImageView.setImageBitmap((Bitmap) msg.obj);
            Log.v("it", "handleMessage "+Thread.currentThread().getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("it", "onCreate "+Thread.currentThread().getName());
        mImageView =(ImageView) findViewById(R.id.center_iv);

        new Thread(){
            public void run() {
                loadImageUrls();
                loadImageByUrl(mCurrentIndex);
            }
        }.start();

    }

    /**
     * 根据索引显示图片
     * @param currIndex
     */
    private void loadImageByUrl(int currIndex) {
        try {
            String imageUrl = mImageUrlPaths.get(currIndex);
            File file=new File(getFilesDir(), getImageName(imageUrl));
            if (file.exists()&&file.length()>0) {
                final Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
//				跳到主线程中修改界面
//				2.在子线程中发送一条消息告诉主线程 想修改图片控件显示
//				Message有两个参数 arg1  arg2 都是int类型 如果你想传递一个int类型数据 就可以使用它们
//				Message 还提供了另外一个属性msg.obj 可以携带任何对象
                Message msg=new Message();
                msg.obj=bmp;
                mHandler.sendMessage(msg);
                Log.v("it", "loadImageByUrl "+Thread.currentThread().getName());
                return;
            }
            URL url=new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int responseCode = conn.getResponseCode();
            if (responseCode==200) {
                InputStream is = conn.getInputStream();

                final Bitmap bitmap = BitmapFactory.decodeStream(is);
                FileOutputStream fos = openFileOutput(getImageName(imageUrl), MODE_PRIVATE);
                bitmap.compress(CompressFormat.PNG, 100, fos);

//				跳到主线程中修改界面
                Message msg=new Message();
                msg.obj=bitmap;
                mHandler.sendMessage(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载图片的URL地址
     */
    private void loadImageUrls() {
        try {
            //1.创建一个Url对象
            URL url=new URL(getImageUrls());
            //2.打开一个链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //3.判断是否能够获取资源 通过状态码来判断
            int responseCode = conn.getResponseCode();
            if (responseCode==200) {
                //4.只能返回一个流数据 输入流
                InputStream is = conn.getInputStream();
                //5.因为要读取字符串 所以要使用字符流
                BufferedReader reader=new BufferedReader(new InputStreamReader(is));
                String line=null;
                while ((line=reader.readLine())!=null) {
                    //读取数据
                    mImageUrlPaths.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void preClick(View v){
        mCurrentIndex--;
        if (mCurrentIndex<0) {
            mCurrentIndex=mImageUrlPaths.size()-1;
        }
        new Thread(){
            public void run() {
                loadImageByUrl(mCurrentIndex);
            }
        }.start();
    }

    public void nextClick(View v){
        mCurrentIndex++;
        if (mCurrentIndex>mImageUrlPaths.size()-1) {
            mCurrentIndex=0;
        }
        new Thread(){
            public void run() {
                loadImageByUrl(mCurrentIndex);
            }
        }.start();
    }

    /**
     * 获取网络地址
     */
    private String getImageUrls(){
        return "http://192.168.1.10:8080/img/photo.txt";
    }

    public String getImageName(String urlPath){
        //1.拿到最后/的位置
        int lastIndexOf = urlPath.lastIndexOf("/");
        //2.返回截取到/后面的文件名
        return urlPath.substring(lastIndexOf+1);
    }

}
