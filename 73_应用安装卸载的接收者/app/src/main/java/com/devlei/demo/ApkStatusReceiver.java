package com.devlei.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ApkStatusReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals(Intent.ACTION_PACKAGE_ADDED)) {
			Log.v("it", "应用被安装");
		}else if (action.equals(Intent.ACTION_PACKAGE_REMOVED)) {
			Log.v("it", "应用被卸载");
		}
	}

}

