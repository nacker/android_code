package com.devlei.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 *	实现增删查改
 */
public class ContactDao {

	private DbOpenHelper mHelper;

	public ContactDao(Context c) {
		mHelper=new DbOpenHelper(c);
	}

	/**
	 * 插入一条联系人信息
	 * @param username
	 * @param phone
	 */
	public boolean insertContact(String username,String phone){
		SQLiteDatabase db = mHelper.getWritableDatabase();
		// _id username phone
		//nullColumnHack 比如 放了username 那么插入的时候 username就不能为null
		//如果nullColumnHack设置为null  那么此次插入的字段可以允许某些字段不插入
		//ContentValues 键值对 源代码里面只是简单的维护了一个HashMap
		ContentValues values=new ContentValues();
		//key 插入的数据库表的字段名  value就是要插入的值
		values.put(DbOpenHelper._USERNAME, username);
		values.put(DbOpenHelper._PHONE, phone);
		//@return 新插入的行的id 如果是-1则说明插入失败
		long rowId = db.insert(DbOpenHelper.TABLE_NAME, null , values);
		return rowId!=-1;
	}

	public boolean updateContact(String username,String newPhone){
		SQLiteDatabase db = mHelper.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put(DbOpenHelper._PHONE, newPhone);
		//whereClause  where语句
		//whereArgs where语句的绑定值
		//@return int 更新了几行就返回多少
		int updateRows = db.update(DbOpenHelper.TABLE_NAME,
				values, DbOpenHelper._USERNAME+"=?", new String[]{username});
		return updateRows>0;
	}

	public boolean deleteContact(String username){
		SQLiteDatabase db = mHelper.getWritableDatabase();
		//whereClause  where语句
		//whereArgs where语句的绑定值
		int deleteRows = db.delete(DbOpenHelper.TABLE_NAME, "username=?", new String[]{username});
		return deleteRows>0;
	}

	public String queryContact(String searchPhone){

		String result="";

		SQLiteDatabase db = mHelper.getReadableDatabase();
//		columns String[] 返回的行
		Cursor cursor = db.query(DbOpenHelper.TABLE_NAME,
				new String[]{DbOpenHelper._USERNAME,DbOpenHelper._PHONE},
				"phone=?", new String[]{searchPhone},
				null, null, null);
		while (cursor.moveToNext()) {
			//传入一个列的列名 返回一个列的索引
			int columnIndex = cursor.getColumnIndex(DbOpenHelper._USERNAME);
			//传入一个列的索引 返回该列下 某一行的值
			String username = cursor.getString(columnIndex);

			//传入一个列的列名 返回一个列的索引
			int phoneColumnIndex = cursor.getColumnIndex(DbOpenHelper._PHONE);
			//传入一个列的索引 返回该列下 某一行的值
			String phone = cursor.getString(phoneColumnIndex);

			result+=(username+"  "+phone+" \n");
		}
		return result;
	}


}
