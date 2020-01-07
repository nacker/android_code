package com.devlei.netease.splash.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.devlei.netease.MainActivity;
import com.devlei.netease.R;
import com.devlei.netease.service.DowloadImageService;
import com.devlei.netease.splash.bean.Action;
import com.devlei.netease.splash.bean.Ads;
import com.devlei.netease.splash.bean.AdsDetail;
import com.devlei.netease.util.Constant;
import com.devlei.netease.util.ImageUtil;
import com.devlei.netease.util.JsonUtil;
import com.devlei.netease.util.Md5Helper;
import com.devlei.netease.util.SharePrenceUtil;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SplashActivity extends Activity {

    //广告图片
    ImageView ads_img;

    //json 缓存
    static final String JSON_CACHE = "ads_Json";
    static final String JSON_CACHE_TIME_OUT = "ads_Json_time_out";
    static final String JSON_CACHE_LAST_SUCCESS = "ads_Json_last";
    static final String LAST_IMAGE_INDEX = "img_index";
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //7.0~2.0
        //开启全屏的设置
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //4.4 沉浸式
        setContentView(R.layout.activity_splash);


        ads_img = (ImageView) findViewById(R.id.ads);
        mHandler = new Handler();


        getAds();

        showImage();
    }

//   public void newThread(){
//       Thread thread = new Thread(){
//           @Override
//           public void run() {
//               super.run();
//               Looper.prepare();
//               Handler handler =  new Handler();
//               Looper.loop();
//           }
//       };
//       thread.start();
//   }

    Runnable NoPhotoGotoMain = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent();
            intent.setClass(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void click(View view) {
        mHandler.sendMessage(new Message());
    }

    public void showImage() {
        //读出缓存
        String cache = SharePrenceUtil.getString(this, JSON_CACHE);
        if (!TextUtils.isEmpty(cache)) {
            //读出这次显示的图片的角标
            int index = SharePrenceUtil.getInt(this, LAST_IMAGE_INDEX);
            //转化成对象
            Ads ads = JsonUtil.parseJson(cache, Ads.class);
            int size = ads.getAds().size();

            if (null == ads) {
                return;
            }

            List<AdsDetail> adsDetails = ads.getAds();
            if (null != adsDetails && adsDetails.size() > 0) {
                final AdsDetail detail = adsDetails.get(index % size);
                List<String> urls = detail.getRes_url();
                if (urls != null && !TextUtils.isEmpty(urls.get(0))) {
                    //获取到url
                    String url = urls.get(0);
                    //计算出文件名
                    String image_name = Md5Helper.toMD5(url);

                    File image = ImageUtil.getFileByName(image_name);
                    if (image.exists()) {
                        Bitmap bitmap = ImageUtil.getImageBitMapByFile(image);
                        ads_img.setImageBitmap(bitmap);
                        //指向下一张图片
                        index++;
                        SharePrenceUtil.saveInt(this, LAST_IMAGE_INDEX, index);

                        ads_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Action action = detail.getAction_params();
                                //如果h5的数据是空的或者没有广告页面,我们不跳转
                                if (action != null && !TextUtils.isEmpty(action.getLink_url())) {

                                    Intent intent = new Intent();
                                    intent.setClass(SplashActivity.this, WebViewActivity.class);
                                    intent.putExtra(WebViewActivity.ACTION_NAME, action);
                                    startActivity(intent);

                                }
                            }
                        });
                    }
                }
            }
        } else {
            //没有缓存,显示不了图片,3秒后跳转到首页
            mHandler.postDelayed(NoPhotoGotoMain, 3000);
        }

    }

    //判断是否需要http请求
    public void getAds() {
        String cache = SharePrenceUtil.getString(this, JSON_CACHE);
        if (TextUtils.isEmpty(cache)) {
            httpRequest();
        } else {
            int time_Out = SharePrenceUtil.getInt(this, JSON_CACHE_TIME_OUT);
            long now = System.currentTimeMillis();
            long last = SharePrenceUtil.getLong(this, JSON_CACHE_LAST_SUCCESS);
            if ((now - last) > time_Out * 60 * 1000) {
                httpRequest();
            }
        }
    }


    //获取广告数据
    public void httpRequest() {
        Log.i("it520", "httpRequest");
        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(Constant.SPLASH_URL)
                .build();

        //开启一个异步请求
        client.newCall(request).enqueue(new Callback() {

            //请求失败
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            //有响应
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    //请求失败
                }

                //获取到接口的数据
                String date = response.body().string();

                Ads ads = JsonUtil.parseJson(date, Ads.class);

                if (null != ads) {
                    //请求成功
                    Log.i("it520", ads.toString());

                    //http成功后,缓存json
                    SharePrenceUtil.saveString(SplashActivity.this, JSON_CACHE, date);
                    //http成功后,缓存超时时间
                    SharePrenceUtil.saveInt(SplashActivity.this, JSON_CACHE_TIME_OUT, ads.getNext_req());
                    //http成功后,缓存上次请求成功的时间
                    SharePrenceUtil.saveLong(SplashActivity.this, JSON_CACHE_LAST_SUCCESS, System.currentTimeMillis());

                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, DowloadImageService.class);
                    intent.putExtra(DowloadImageService.ADS_DATE, ads);
                    startService(intent);

                } else {
                    //请求失败
                }
            }
        });

    }
}
