package com.devlei.demo;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private ArrayList<String> mDatas;
	private Context mContext;

	public MyAdapter(Context c,ArrayList<String> datas) {
		mDatas=datas;
		mContext=c;
	}

	//1.告诉系统 要展示几项
	@Override
	public int getCount() {
		return mDatas.size();
	}

	/**
	 * @param position 当前项的索引
	 * @return View 就是要展示的某一项的控件布局
	 * */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.v("it", "getView"+position);
		String text = mDatas.get(position);
		TextView tv=new TextView(mContext);
		tv.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
				AbsListView.LayoutParams.WRAP_CONTENT));
		tv.setTextSize(20);
		tv.setText(text);
		return tv;
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
