package com.devlei.demo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.devlei.demo.bean.Student;
import com.devlei.demo.cons.DbConst;

public class StudentDao {

	private DbOpenHelper mHelper;

	public StudentDao(Context c) {
		mHelper = new DbOpenHelper(c);
	}
	
	public int getStudentCount(){
		SQLiteDatabase db = mHelper.getReadableDatabase();
		Cursor cursor = db.query(DbConst.TABLE_NAME, null, null, null, null, null, null);
		return cursor.getCount();
	}
	
	public boolean saveStudent(String stdId,String username,String phone){
		SQLiteDatabase db = mHelper.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put(DbConst.COLUMN_STDID, stdId);
		values.put(DbConst.COLUMN_USERNAME, username);
		values.put(DbConst.COLUMN_PHONE, phone);
		long insertId = db.insert(DbConst.TABLE_NAME, null, values);
		return insertId!=-1;
	}
	
	public Student getStudentByPosition(int position){
		SQLiteDatabase db = mHelper.getReadableDatabase();
		String[] columns=new String[]{DbConst.COLUMN_STDID,DbConst.COLUMN_USERNAME,DbConst.COLUMN_PHONE};
		Cursor cursor = db.query(DbConst.TABLE_NAME, columns,null, null, null, null, null);
		if (cursor.moveToPosition(position)) {
			String id=cursor.getString(0);
			String name=cursor.getString(1);
			String phone=cursor.getString(2);
			return new Student(id, name, phone);
		}
		return null;
	}
	
	

}
