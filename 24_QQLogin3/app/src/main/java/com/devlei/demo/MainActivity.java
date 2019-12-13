package com.devlei.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 *	1.这里创建一个文件 可以被其他应用读取(实际操作的意义)
 */
public class MainActivity extends Activity {

    private EditText mPassportEt;
    private EditText mPasswordEt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPassportEt = (EditText) findViewById(R.id.passport_et);
        mPasswordEt = (EditText) findViewById(R.id.password_et);

        readData();
    }


    /**
     * 1.获取两个输入框里面的值 2.做一个值的校验 判断不为空 3.每次都登录成功 保存到手机里面
     */
    public void loginClick(View v) {
        String passport = mPassportEt.getText().toString();
        String password = mPasswordEt.getText().toString();
        // passport==null||passport.equals("")
        // TextUtils.isEmpty()
        if (TextUtils.isEmpty(passport) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请填写完整的信息", 0).show();
            return;
        }
        // 登录保存数据
        saveData(passport, password);
    }


    /**
     * 读取文件里面的数据 回显文本
     */
    private void readData() {
        SharedPreferences sp=getSharedPreferences("qqinfo", MODE_PRIVATE);
//		defValue 如果找不到对应的值 应该怎么办
        String passport = sp.getString("username", "");
        mPassportEt.setText(passport);
        String password = sp.getString("pwd", "");
        mPasswordEt.setText(password);
    }

    /**
     * 将数据保存到手机里面-->文件系统-->将"账号#密码"写到文件里面去
     *
     */
    private void saveData(String passport, String password) {
        SharedPreferences sp=getSharedPreferences("qqinfo", MODE_PRIVATE);
        //2.往sp里面存储键值对 在存储多个值的时候可能会报错--->事务(原子性)
        Editor edit = sp.edit();
        edit.putString("username", passport);
        edit.putString("pwd", password);
        edit.commit();
    }

}
