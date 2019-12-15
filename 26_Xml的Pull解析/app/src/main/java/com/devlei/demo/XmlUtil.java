package com.devlei.demo;

import java.io.FileInputStream;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.util.Xml;

public class XmlUtil {

	/**
	 * 解析Xml文件 返回一个字符串
	 *
	 * @throws Exception
	 */
	public static String parseXmlFile(Context c) throws Exception {
		String result="";
		// 1.创建一个Pull解析器
		XmlPullParser pullParser = Xml.newPullParser();
		// 2.告诉解析器文件在哪里(将整个xml文件进行关联)
		// 不管openFileInput/openFileOutput 都会在files目录下找文件
		FileInputStream fis = c.openFileInput("qqinfo.xml");
		pullParser.setInput(fis, "utf-8");
		// 3.解析文件
		// 3.1获取事件的类型 (开标签 文本 闭标签 标签属性 开始文档的类型 结束文档的类型)
		int eventType = pullParser.getEventType();
		//Log.v("it", eventType + " ");
		// 3.2pullParser.next() 返回的是下一个事件类型
		//eventType = pullParser.next();
		// 3.3 pullParser.getName() 获取标签的名称
		//Log.v("it", pullParser.getName() + " ");
		// 3.4 如果是Text的事件 pullParser.getName()返回null
		// 3.5 pullParser.nextText() 找到某个标签后面的值
		while (eventType!=XmlPullParser.END_DOCUMENT) {
			//  <map>  <pwd>   <username>
			if (eventType==XmlPullParser.START_TAG) {
				if (pullParser.getName().equals("username")) {
					result+=("   username="+pullParser.nextText());
				}else if (pullParser.getName().equals("pwd")) {
					result+=("   pwd="+pullParser.nextText());
				}
			}
			eventType = pullParser.next();
		}

		return result;
	}

}
