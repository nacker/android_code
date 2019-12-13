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
     * 读取文件里面的数据 回显文本
     */
    private void readData() {
        try {
            //1.读取账号密码
//			File file = new File("/data/data/com.it.qqlogin/qqinfo.txt");
//			File file = new File(getCacheDir(), "/qqinfo.txt");
            File file = new File(getFilesDir(), "/qqinfo.txt");
            if (file.exists()&&file.length()>0) {
                //2.显示到控件里面去  读取字符串的操作  abc#123
                BufferedReader reader=new BufferedReader(new FileReader(file));
                String line=reader.readLine();
                if (line.contains("#")) {
                    String[] datas = line.split("#");
                    mPassportEt.setText(datas[0]);
                    mPasswordEt.setText(datas[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * 将数据保存到手机里面-->文件系统-->将"账号#密码"写到文件里面去
     *
     */
    private void saveData(String passport, String password) {
        try {
//			File file = new File("/data/data/com.it.qqlogin/qqinfo.txt");
//			把文件保存到cache目录下
            //  /data/data/com.it.qqlogin/cache
//			File file = new File(getCacheDir(), "/qqinfo.txt");
//			File file = new File(getFilesDir(), "/qqinfo.txt");

//			name 就是文件名
//			mode 文件权限
//			MODE_APPEND 比如一个文件已经有内容了 再次写文件就是在文件后面继续追加
//			MODE_PRIVATE 对其他应用私有化 但是本应用是可读可写的
//			MODE_WORLD_READABLE  任何应用都能读取
//			MODE_WORLD_WRITEABLE  任何应用都能写入
            FileOutputStream fos=openFileOutput("qqinfo.txt",
                    MODE_WORLD_READABLE+MODE_WORLD_WRITEABLE);
//			OutputStreamWriter 转换流 对接字符流与字节流
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(fos));
//			BufferedWriter writer =
//					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(passport+"#"+password);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

