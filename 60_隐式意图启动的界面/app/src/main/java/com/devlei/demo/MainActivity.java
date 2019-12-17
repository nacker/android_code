package com.devlei.demo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	//隐式意图有2种 1.只带有action属性  2.附带data 还有type
	public void myClick(View v){
		//没有说明要打开某一个界面 而是通过匹配来决定到底要怎么打开 这种叫做隐式意图
		Intent intent=new Intent();
		//我想启动一个页面 该页面配置了一个ACTION属性
		intent.setAction("com.devlei.demo.action.HAHA");
		startActivity(intent);
		//如果只有1个匹配就直接打开  如果有2个匹配 就出现一个列表  如果没有匹配 就报错
	}

	public void myClick2(View v){
		Intent intent =new Intent();
		//告诉系统我们想启动一个带有浏览器ACTION的界面
		intent.setAction("android.intent.action.VIEW");
		//想打开一个浏览器  需要传递一个网址进来
		//Uri包含URL  所以URI可以代表URL
		Uri data=Uri.parse("http://www.baidu.com");
		//告诉系统展示成什么样的格式   mimeType来决定   text/plaint   text/html
		intent.setDataAndType(data, "text/html");
		startActivity(intent);
	}

}
