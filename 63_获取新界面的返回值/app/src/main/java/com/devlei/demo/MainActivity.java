package com.devlei.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ImageView mImageView;
    private TextView mSexTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView =(ImageView) findViewById(R.id.icon_iv);
        mSexTv =(TextView) findViewById(R.id.sex_tv);
    }

    public void chooseIcon(View v){
        Intent intent=new Intent(this,ChooseIconActivity.class);
//		startActivity(intent);
        //1.当你想打开一个新的界面 并且向从新的界面获取数据 此时需要调用startActivityForResult
        startActivityForResult(intent, 1);
    }

    public void chooseSex(View v){
        Intent intent=new Intent(this,SexActivity.class);
        startActivityForResult(intent, 2);
    }

    //3.拿到新页面的传递过来的数据
    //data 传递的数据都是通过该Intent对象来操作的
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null) {
            if (requestCode==1) {
                int iconResId = data.getIntExtra("icon", 0);
                mImageView.setImageResource(iconResId);
            }else if (requestCode==2) {
                String sex = data.getStringExtra("sex");
                mSexTv.setText(sex);
            }
        }
    }


}
