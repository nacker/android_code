package com.devlei.demo;

import java.util.Iterator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//获取跟短信相关的数据
		Bundle bundle = intent.getExtras();
		//短信在发送的过程中 可能会变成多条  所以这里把它封装成一个数组
		Object[] objs = (Object[]) bundle.get("pdus");
		for (Object obj : objs) {
			//短信在手机里面是以2进制的字节码进行传输
			byte[] buff=(byte[]) obj;
			//将二进制转换成一条短信
			SmsMessage message=SmsMessage.createFromPdu(buff);
			//1.获取发信人
			String phone = message.getDisplayOriginatingAddress();
			//2.短信的内容
			String smsContent = message.getDisplayMessageBody();

			Log.v("it", phone+"  "+smsContent);

			//发条信息返回去...
		}
		//2.告诉广播 不要继续发下去了   终止广播
		abortBroadcast();
	}

}
