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
		mDatas = datas;
	}

	@Override
	public int getCount() {
		// Log.v("it", "getCount");
		return mDatas.size();
	}

	/**
	 * position 索引 convertView parent 每个 ItemView里面的容器 返回的View直接添加到容器中来
	 * convertView 缓存的ItemView
	 * @return View 就是每个ItemView要显示的内容
	 * */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView==null) {
			LayoutInflater layoutInflater = LayoutInflater
					.from(parent.getContext());
			View view = layoutInflater.inflate(android.R.layout.simple_list_item_2,
					null);
			convertView=view;

		}else {

		}

		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		TextView tv2 = (TextView) convertView.findViewById(android.R.id.text2);
		tv.setText(mDatas.get(position));
		tv2.setText(mDatas.get(position));

//		Log.v("it", "getView view=" + view + "   convertView=" + convertView);
		return convertView;
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
