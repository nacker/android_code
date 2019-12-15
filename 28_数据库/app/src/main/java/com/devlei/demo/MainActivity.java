package com.devlei.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
        boolean ifInserted = mDao.insertContact("lisi", "150122222");
        Toast.makeText(this, ifInserted?"插入成功":"插入失败", 0).show();
    }

    public void updateContact(View v) {
        boolean updateContact = mDao.updateContact("zhangsan", "10086");
        Toast.makeText(this, updateContact?"更新成功":"更新失败", 0).show();
    }

    public void deleteContact(View v) {
        boolean deleteContact = mDao.deleteContact("zhangsan");
        Toast.makeText(this, deleteContact?"删除成功":"删除失败", 0).show();
    }

    public void queryContact(View v) {
        String result = mDao.queryContact("150122222");
        Toast.makeText(this, result, 0).show();
    }

}
