package com.devlei.demo.util;

import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import android.util.Xml;

import com.devlei.demo.bean.NewsBean;

public class XmlUtil {

	public static ArrayList<NewsBean> parseNews(InputStream is) throws Exception{
		ArrayList<NewsBean> result=new ArrayList<NewsBean>();
		XmlPullParser pullParser=Xml.newPullParser();
		pullParser.setInput(is, "utf-8");
		//开始解析数据 基于事件的解析机制
		int eventType = pullParser.getEventType();
		NewsBean newsBean=null;
		while (eventType!=XmlPullParser.END_DOCUMENT) {
			if (eventType==XmlPullParser.START_TAG) {
				if (pullParser.getName().equals("item")) {
					newsBean=new NewsBean();
				}else if (pullParser.getName().equals("title")) {
					newsBean.setTitle(pullParser.nextText());
				}else if (pullParser.getName().equals("description")) {
					newsBean.setDescription(pullParser.nextText());
				}else if (pullParser.getName().equals("image")) {
					newsBean.setImage(pullParser.nextText());
				}else if (pullParser.getName().equals("type")) {
					newsBean.setType(Integer.parseInt(pullParser.nextText()));
				}else if (pullParser.getName().equals("comment")) {
					newsBean.setComment(Integer.parseInt(pullParser.nextText()));
				}
			}else if (eventType==XmlPullParser.END_TAG) {
				if (pullParser.getName().equals("item")) {
					result.add(newsBean);
				}
			}
			eventType=pullParser.next();
		}
		return result;
	}

}
