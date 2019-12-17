package com.devlei.demo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class ChooseIconActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_icon);

		findViewById(R.id.e0).setOnClickListener(this);
		findViewById(R.id.e1).setOnClickListener(this);
		findViewById(R.id.e2).setOnClickListener(this);
		findViewById(R.id.e3).setOnClickListener(this);
	}

	//View v 点击了谁 就是谁
	@Override
	public void onClick(View v) {
		Intent intet=new Intent();
		switch (v.getId()) {
			case R.id.e0:
				//要返回一个什么类型的数据  int类型的数据
				intet.putExtra("icon", R.drawable.e0);
				break;
			case R.id.e1:
				intet.putExtra("icon", R.drawable.e1);
				break;
			case R.id.e2:
				intet.putExtra("icon", R.drawable.e2);
				break;
			case R.id.e3:
				intet.putExtra("icon", R.drawable.e3);
				break;
		}
		//2. 给旧的页面传递数据
		setResult(0, intet);
		finish();//如果没有调用该句 数据是返不回去的
	}


}
