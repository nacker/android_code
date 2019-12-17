package com.devlei.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class BActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		Log.v("520it", "BActivity  onCreate");
	}
	
	public void openA(View v){
		Intent intent=new Intent(this,AActivity.class);
		startActivity(intent);
	}

	public void openB(View v){
		Intent intent=new Intent(this,BActivity.class);
		startActivity(intent);
	}
	
}
