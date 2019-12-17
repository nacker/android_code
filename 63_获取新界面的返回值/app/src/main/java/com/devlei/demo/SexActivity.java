package com.devlei.demo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class SexActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sex);

		findViewById(R.id.male_btn).setOnClickListener(this);
		findViewById(R.id.female_btn).setOnClickListener(this);
		findViewById(R.id.unknown_btn).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent intent=new Intent();
		switch (v.getId()) {
			case R.id.male_btn:
				intent.putExtra("sex", "男");
				break;
			case R.id.female_btn:
				intent.putExtra("sex", "女");
				break;
			case R.id.unknown_btn:
				intent.putExtra("sex", "人妖");
				break;
		}
		setResult(0, intent);
		finish();
	}

}
