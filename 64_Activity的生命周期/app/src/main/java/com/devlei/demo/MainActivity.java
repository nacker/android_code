package com.devlei.demo;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText mContentEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("it", "onCreate");
        mContentEt =(EditText) findViewById(R.id.content_et);

        //在重新进入界面的时候应该将数据进行回显
        SharedPreferences sp = getSharedPreferences("sms", MODE_PRIVATE);
        String content = sp.getString("content", "");
        mContentEt.setText(content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("it", "onDestroy");
        //在该方法里面保存短信的内容
        String content = mContentEt.getText().toString();

        SharedPreferences sp = getSharedPreferences("sms", MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString("content", content);
        editor.commit();
    }


}
