package com.devlei.demo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.devlei.demo.cons.DbConst;

public class DbOpenHelper extends SQLiteOpenHelper{

	public DbOpenHelper(Context context) {
		super(context, DbConst.DB_NAME, null, DbConst.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table "+DbConst.TABLE_NAME+"("
				+DbConst._ID+" integer primary key autoincrement,"
				+DbConst.COLUMN_STDID+" varchar(5),"
				+DbConst.COLUMN_USERNAME+" varchar(20),"
				+DbConst.COLUMN_PHONE+" varchar(13));");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
