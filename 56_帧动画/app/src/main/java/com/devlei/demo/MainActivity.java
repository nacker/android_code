package com.devlei.demo;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView=(ImageView)findViewById(R.id.iv);
    }

    public void startPlay(View v){
        //getDrawable() 拿到图片的src属性值
        AnimationDrawable drawable = (AnimationDrawable) mImageView.getDrawable();
        drawable.start();
    }

}
