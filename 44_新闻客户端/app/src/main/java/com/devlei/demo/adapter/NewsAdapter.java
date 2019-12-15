package com.devlei.demo.adapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.devlei.demo.R;
import com.devlei.demo.bean.NewsBean;
import com.devlei.demo.ui.ImageViewWithUrl;

public class NewsAdapter extends BaseAdapter {

	private ArrayList<NewsBean> mDatas;
	public void setDatas(ArrayList<NewsBean> newsBeans) {
		mDatas=newsBeans;
	}

	@Override
	public int getCount() {
		return mDatas!=null?mDatas.size():0;
	}

	class ViewHolder{
		ImageViewWithUrl newsIv;
		TextView newsTitleTv;
		TextView newsDescTv;
		TextView newsTypeTv;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if (convertView==null) {
			LayoutInflater inflater=LayoutInflater.from(parent.getContext());
			convertView=inflater.inflate(R.layout.news_item_layout, null);
			holder=new ViewHolder();
			holder.newsIv= (ImageViewWithUrl) convertView.findViewById(R.id.news_iv);
			holder.newsTitleTv = (TextView) convertView.findViewById(R.id.title_tv);
			holder.newsDescTv = (TextView) convertView.findViewById(R.id.desc_tv);
			holder.newsTypeTv = (TextView) convertView.findViewById(R.id.type_tv);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		NewsBean bean = mDatas.get(position);
		holder.newsIv.setImageUrl(bean.getImage());
		holder.newsTitleTv.setText(bean.getTitle());
		holder.newsDescTv.setText(bean.getDescription());
		initTypeText(holder, bean);

		return convertView;
	}

	private void initTypeText(ViewHolder holder, NewsBean bean) {
		if (bean.getType()==1) {
			holder.newsTypeTv.setText("评论:"+bean.getComment());
		}else if (bean.getType()==2) {
			holder.newsTypeTv.setText("专题");
		}else if (bean.getType()==3) {
			holder.newsTypeTv.setText("科技");
		}
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
