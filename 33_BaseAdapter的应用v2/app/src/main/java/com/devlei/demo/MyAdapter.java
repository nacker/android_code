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

	class ViewHolder{
		TextView tv1;
		TextView tv2;
	}

	/**
	 * position 索引 convertView parent 每个 ItemView里面的容器 返回的View直接添加到容器中来
	 * convertView 缓存的ItemView
	 * @return View 就是每个ItemView要显示的内容
	 * */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if (convertView==null) {
			LayoutInflater layoutInflater = LayoutInflater
					.from(parent.getContext());
			convertView = layoutInflater.inflate(android.R.layout.simple_list_item_2,
					null);
			//在convertView刚开始创建的时候 就初始化ViewHolder
			holder=new ViewHolder();

			holder.tv1 = (TextView) convertView.findViewById(android.R.id.text1);
			holder.tv2 = (TextView) convertView.findViewById(android.R.id.text2);

			convertView.setTag(holder);
			Log.v("it", "getView");
		}else {
			holder=(ViewHolder) convertView.getTag();
		}

		holder.tv1.setText(mDatas.get(position));
		holder.tv2.setText(mDatas.get(position));

//		Log.v("520it", "getView view=" + view + "   convertView=" + convertView);
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
