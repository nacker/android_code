package com.devlei.demo.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtil {

	/**
	 * @param urlPath
	 * @return  InputStream 如果后台出错了 可能返回为null
	 * @throws Exception
	 */
	public static InputStream getResource(String urlPath) throws Exception{
		URL url=new URL(urlPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		int responseCode = conn.getResponseCode();
		if (responseCode==200) {
			return conn.getInputStream();
		}
		return null;
	}

}
