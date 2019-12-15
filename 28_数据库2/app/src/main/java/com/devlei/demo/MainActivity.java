package com.devlei.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private ContactDao mDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            // 1.创建了一个文件对象
            File file = new File(getFilesDir(), "haha.txt");
            // 2.1 创建了一个空文件
            file.createNewFile();
            // 2.2 创建一个输出流
            FileOutputStream fos = new FileOutputStream(file);
            // 3.如果想去创建一个数据库文件 SQLiteOpenHelper 它是一个帮助类
            DbOpenHelper helper = new DbOpenHelper(this);
            // 4.1 getReadableDatabase 创建数据库文件
            // helper.getReadableDatabase();
            // 4.2 getWritableDatabase() 创建数据库文件
            // SQLiteDatabase db = helper.getWritableDatabase();
            // db.execSQL(sql)
        } catch (IOException e) {
            e.printStackTrace();
        }

        mDao=new ContactDao(this);
    }

    public void insertContact(View v) {
        mDao.insertContact("zhangsan", "150122222");
    }

    public void updateContact(View v) {
        mDao.updateContact("zhangsan", "1501111111");
    }

    public void deleteContact(View v) {
        mDao.deleteContact("zhangsan");
    }

    public void queryContact(View v) {
        mDao.queryContact("15018888888");
    }

}
