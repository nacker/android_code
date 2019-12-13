package com.devlei.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.edittext_layout);
		setContentView(R.layout.textview_layout);
//		EditText
		
	}

	
	public void myClick(View v){
		Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
	}

}
