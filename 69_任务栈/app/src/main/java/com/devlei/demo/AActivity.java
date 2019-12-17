package com.devlei.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);
		Log.v("520it", "AActivity  onCreate");
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
