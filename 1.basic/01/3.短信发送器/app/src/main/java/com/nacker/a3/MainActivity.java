package com.nacker.a3;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View v){
        //拿到用户输入的号码和内容
        EditText et_phone = (EditText) findViewById(R.id.et_phone);
        EditText et_content = (EditText) findViewById(R.id.et_content);

        String phone = et_phone.getText().toString();
        String content = et_content.getText().toString();

        //1.获取短信管理器
        SmsManager sm = SmsManager.getDefault();
        //2.切割短信，把长短信分成若干个小短信
        ArrayList<String> smss = sm.divideMessage(content);

        for (String string : smss){
            sm.sendTextMessage(phone, null, string, null, null);
        }
    }

}
