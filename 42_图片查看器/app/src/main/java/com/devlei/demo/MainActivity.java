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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView mImageView;
    private ArrayList<String> mImageUrlPaths=new ArrayList<String>();
    private int mCurrentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView =(ImageView) findViewById(R.id.center_iv);

//		android.os.NetworkOnMainThreadException
        new Thread(){
            public void run() {
                //开发步骤
                //1.从网络上读取photo.txt 并封装成为ArrayList<String>
                loadImageUrls();
                //2.创建一个索引变量 用来控制当前的显示的图片索引
                //3.根据索引 从ArrayList取出某一个字符串(图片网址) 根据网址获取图片 显示到控件上来
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
            //1.拿到的图片Url地址
            String imageUrl = mImageUrlPaths.get(currIndex);
            //2.拿到图片名
            //3.拿到图片存储时的全路径 判断文件是否存在
            File file=new File(getFilesDir(), getImageName(imageUrl));
            //4.如果当前的图片被缓存了 就不需要在执行网络请求了
            if (file.exists()&&file.length()>0) {
                //5.文件存在了 还要从文件里面读取图片
                final Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mImageView.setImageBitmap(bmp);
                    }
                });
                return;
            }
            Log.v("it", "loadImageByUrl currIndex="+currIndex);
            URL url=new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            int responseCode = conn.getResponseCode();
            if (responseCode==200) {
                InputStream is = conn.getInputStream();

                final Bitmap bitmap = BitmapFactory.decodeStream(is);
                //将bitmap写到文件里面去  flies目录
                //format 文件的类型
                //quality 图片从流转换成文件的 保存的质量 范围0~100
                //stream 文件要保存到哪里去
                FileOutputStream fos = openFileOutput(getImageName(imageUrl), MODE_PRIVATE);
                bitmap.compress(CompressFormat.PNG, 100, fos);


                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mImageView.setImageBitmap(bitmap);
                    }
                });

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
