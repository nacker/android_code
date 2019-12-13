package com.devlei.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.util.Xml;

public class XmlUtil {

	/**
	 * 通过将字符串进行拼接 并且写到 /files目录下
	 *
	 * @throws Exception
	 */

	public static void saveXmlByStringBuilder(Context c, String passport,
											  String password) throws Exception {
		// <map>
		// <string name="pwd">123</string>
		// <string name="username">5abc#12345</string>
		// </map>
		// 1.拼接字符串
		StringBuilder sb = new StringBuilder();
		sb.append("<map>");
		sb.append("<string name=\"pwd\">" + passport + "</string>");
		sb.append("<string name=\"username\">" + password + "</string>");
		sb.append("</map>");
		String result = sb.toString();
		// 2.创建一个文件的对象
		// 因为getFilesDir()只有在Context才有 所以要传进来
		File file = new File(c.getFilesDir(), "qqinfo.xml");
		// 3.创建一个输出流(字符流)
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		// 4.开始写字符串
		writer.write(result);
		writer.close();
	}

	public static void saveXmlByXmlSerializer(Context c, String passport,
											  String password) throws Exception {
		// xmlSerializer 创建xml文档的一个类 它可以帮助我们创建一个xml文件
		XmlSerializer xmlSerializer = Xml.newSerializer();
		// 1.告诉序列化工具 文件应该存储到什么地方
		// 创建一个outputstream-->创建一个文件 转换成流
		FileOutputStream fos = c.openFileOutput("qqinfo.xml",
				Context.MODE_PRIVATE);
		xmlSerializer.setOutput(fos, "utf-8");
		// <map>
		// <pwd>123</pwd>
		// <username>zhangsan</username>
		// </map>
		// 3.告诉序列器 开始写文档
		// <?xml version='1.0' encoding='utf-8' standalone='yes' ?>
		xmlSerializer.startDocument("utf-8", true);
		// 命名空间是用来避免多个文件中的标签的命名冲突的
		xmlSerializer.startTag(null, "map");

		xmlSerializer.startTag(null, "pwd");
		xmlSerializer.text(password);
		xmlSerializer.endTag(null, "pwd");

		xmlSerializer.startTag(null, "username");
		xmlSerializer.text(passport);
		xmlSerializer.endTag(null, "username");

		xmlSerializer.endTag(null, "map");
		//4.文档写完 此时可以将内存中数据写到硬盘中去
		xmlSerializer.endDocument();
	}

}
