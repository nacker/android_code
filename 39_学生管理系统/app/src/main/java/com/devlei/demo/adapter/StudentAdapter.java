package com.devlei.demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.devlei.demo.R;
import com.devlei.demo.bean.Student;
import com.devlei.demo.dao.StudentDao;

public class StudentAdapter extends BaseAdapter {
	
	private StudentDao mDao;
	
	public StudentAdapter(Context c) {
		mDao=new StudentDao(c);
	}
	
	@Override
	public int getCount() {
		return mDao.getStudentCount();
	}
	
	class ViewHolder{
		public TextView stdIdTv;
		public TextView stdNameTv;
		public TextView stdPhoneTv;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if (convertView==null) {
			convertView=View.inflate(parent.getContext(),
					R.layout.lv_item_view, null);
			holder=new ViewHolder();
			holder.stdIdTv=(TextView) convertView.findViewById(R.id.studentid_tv);
			holder.stdNameTv=(TextView) convertView.findViewById(R.id.username_tv);
			holder.stdPhoneTv=(TextView) convertView.findViewById(R.id.phone_tv);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		Student student = mDao.getStudentByPosition(position);
		holder.stdIdTv.setText(student.getId());
		holder.stdNameTv.setText(student.getName());
		holder.stdPhoneTv.setText(student.getPhone());
		return convertView;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
