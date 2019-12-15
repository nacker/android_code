package com.devlei.demo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 *	实现增删查改
 */
public class ContactDao {

	private DbOpenHelper mHelper;

	public ContactDao(Context c) {
		mHelper=new DbOpenHelper(c);
	}

	public void insertContact(String username,String phone){
		SQLiteDatabase db = mHelper.getWritableDatabase();
		//bindArgs 占位符所对应的值
		db.execSQL("insert into "+DbOpenHelper.TABLE_NAME
						+"("+DbOpenHelper._USERNAME+","+DbOpenHelper._PHONE+") values(?,?);",
				new String[]{username,phone});
	}

	public void updateContact(String username,String newPhone){
		SQLiteDatabase db = mHelper.getWritableDatabase();
		db.execSQL("update contactinfo set phone=? where username=?;",
				new String[]{newPhone,username});
	}

	public void deleteContact(String username){
		SQLiteDatabase db = mHelper.getWritableDatabase();
		db.execSQL("delete from contactinfo where username=?;",
				new String[]{username});
	}

	public void queryContact(String phone){
		SQLiteDatabase db = mHelper.getReadableDatabase();
		//cursor 游标
		Cursor cursor = db.rawQuery("select username from contactinfo where phone=?;",
				new String[]{phone});
		//如果有下一行 就返回true
//		boolean moveToNext = cursor.moveToNext();
		while (cursor.moveToNext()) {
			String username = cursor.getString(0);
			Log.v("520it", username);
		}
	}


}
