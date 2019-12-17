package com.devlei.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SDcardReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals(Intent.ACTION_MEDIA_MOUNTED)) {
			Log.v("it", "一个SD卡安装的状态");
		}else if (action.equals(Intent.ACTION_MEDIA_UNMOUNTED)) {
			Log.v("it", "一个是SD卡卸载的状态");
		}
	}

}