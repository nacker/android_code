package com.devlei.demo;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private ArrayList<String> mDatas;
	private LayoutInflater mInflater;
	
	public MyAdapter(Context c) {
		mInflater = LayoutInflater.from(c);
	}
	
	public void setDatas(ArrayList<String> datas) {
		mDatas=datas;
	}
	
	@Override
	public int getCount() {
		return mDatas!=null?mDatas.size():0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tv=null;
		if (convertView==null) {
			convertView = mInflater.inflate(android.R.layout.simple_list_item_1, null);
			tv=(TextView) convertView.findViewById(android.R.id.text1);
			convertView.setTag(tv);
		}else {
			tv=(TextView) convertView.getTag();
		}
		tv.setText(mDatas.get(position));
		return convertView;
	}

	@Override
	public Object getItem(int position) {
		return mDatas!=null?mDatas.get(position):null;
	}

	@Override
	public long getItemId(int position) {
		return 5;
	}

}
