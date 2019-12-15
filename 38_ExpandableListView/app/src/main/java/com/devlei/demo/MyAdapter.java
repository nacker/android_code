package com.devlei.demo;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseExpandableListAdapter {

	private ArrayList<String> mGroupNames;
	private HashMap<String, ArrayList<String>> mChildNames;

	public MyAdapter(ArrayList<String> groupNames,
					 HashMap<String, ArrayList<String>> childNames) {
		mGroupNames=groupNames;
		mChildNames=childNames;
	}

	@Override
	public int getGroupCount() {
		return mGroupNames.size();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
							 View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View itemView = inflater.inflate(android.R.layout.simple_list_item_1, null);
		TextView tv = (TextView) itemView.findViewById(android.R.id.text1);
		tv.setText(mGroupNames.get(groupPosition));
		return itemView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		//1.获取组
		String groupName = mGroupNames.get(groupPosition);
		//2.根据组名获取子列表
		ArrayList<String> childNames = mChildNames.get(groupName);
		return childNames.size();
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
							 boolean isLastChild, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View itemView = inflater.inflate(android.R.layout.simple_list_item_1, null);
		//1.控件已经被获取了
		TextView tv = (TextView) itemView.findViewById(android.R.id.text1);
		//2.获取子视图的数据
		//3.获取组
		String groupName = mGroupNames.get(groupPosition);
		//4.根据组名获取子列表
		ArrayList<String> childNames = mChildNames.get(groupName);
		//5.从子列表里面获取某个Item
		String text = childNames.get(childPosition);
		tv.setText(text);
		return itemView;
	}








	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
