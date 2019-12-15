package com.devlei.demo;

import java.util.ArrayList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private ArrayList<String> mDatas;
	
	public MyAdapter(ArrayList<String> datas) {
		mDatas=datas;
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View view = layoutInflater.inflate(R.layout.lv_item_layout, null);
		TextView tv=(TextView) view.findViewById(R.id.tv);
		tv.setText(mDatas.get(position));
		return view;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}


}
