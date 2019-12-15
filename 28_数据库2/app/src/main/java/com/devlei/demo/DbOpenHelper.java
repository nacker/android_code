package com.devlei.demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DbOpenHelper extends SQLiteOpenHelper implements BaseColumns{

	//1.数据库文件名
	public static final String DB_NAME="contact.db";
	//2.数据库版本
	public static final int DB_VERSION=1;
	//3.数据库表的内容
	public static final String TABLE_NAME="contactinfo";
	public static final String _USERNAME="username";
	public static final String _PHONE="phone";

	//factory 用来创建游标对象的工厂类
	public DbOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
//	结论:
//		当数据库文件创建的时候会调用onCreate() 可以在这个方法里面创建多张数据库表了
	public void onCreate(SQLiteDatabase db) {
		Log.v("it", "onCreate");
//		接下来创建数据库表就需要使用sql语句
		db.execSQL("create table "+TABLE_NAME+" ("+_ID+" integer primary key autoincrement," +
				_USERNAME+" text,"+_PHONE+" text);");
	}

	@Override
//	Update 升级数据库   可以在该方法里面删除/添加/修改数据库表
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.v("it", "onUpgrade"+"  oldVersion="
				+oldVersion+" newVersion="+newVersion);
	}

}
