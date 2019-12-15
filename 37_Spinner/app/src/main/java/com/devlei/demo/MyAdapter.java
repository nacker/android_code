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
		Log.v("it", "getCount");
		return mDatas.size();
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.v("it", "getView position="+position);
//		TextView tv=new TextView(parent.getContext());
//		tv.setLayoutParams(new AbsListView.LayoutParams(
//				AbsListView.LayoutParams.MATCH_PARENT,
//				AbsListView.LayoutParams.WRAP_CONTENT));
//		tv.setTextSize(30);
//		tv.setText(mDatas.get(position));

//		LayoutInflater 布局充气的一个类  就是可以将一个布局转换成一个View
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//		root 询问用户是否需要添加到某个容器里面去
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
