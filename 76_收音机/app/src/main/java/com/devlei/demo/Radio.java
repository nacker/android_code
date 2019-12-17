package com.devlei.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Radio extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v("it", "地方的收音机接收到广播电台的信号");
	}

}
