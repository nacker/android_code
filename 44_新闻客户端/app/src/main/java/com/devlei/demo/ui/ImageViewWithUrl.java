package com.devlei.demo.ui;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.devlei.demo.R;

public class ImageViewWithUrl extends ImageView {

	private static final int ACTION_LOAD_SUCCESS=0x001;
	private static final int ACTION_LOAD_FAILURE=0x002;
	private Handler mHandler=new Handler(){

		public void handleMessage(Message msg) {
			if (msg.what==ACTION_LOAD_SUCCESS) {
				setImageBitmap((Bitmap) msg.obj);
			}else if (msg.what==ACTION_LOAD_FAILURE) {
				setImageResource(R.drawable.ic_launcher);
			}
		}

	};

	// 程序员在new一个控件的时候调用的
	public ImageViewWithUrl(Context context) {
		super(context);
	}

	// 当把控件设置在xml文件中的时候 系统在渲染界面的时候就会调用该构造器
	public ImageViewWithUrl(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 如果有些图片看不到 就只能看到替代的静态图片
	 *
	 */
	public void setImageUrl(final String urlPath) {
		new Thread() {
			public void run() {
				try {
					// 开始做一个网络请求
					URL url = new URL(urlPath);
					HttpURLConnection conn=(HttpURLConnection) url.openConnection();
					if (conn.getResponseCode()==200) {
						//如果成功就显示网络的图片
						InputStream is = conn.getInputStream();
						Bitmap bmp = BitmapFactory.decodeStream(is);
						Message msg=new Message();
//						msg.what  当有多个子线程向Handler发送信息 Handler需要根据标识来进行处理
						msg.what=ACTION_LOAD_SUCCESS;
						msg.obj=bmp;
						mHandler.sendMessage(msg);
					}else {
						//TODO 失败
						Message msg=new Message();
						msg.what=ACTION_LOAD_FAILURE;
						mHandler.sendMessage(msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					//TODO 出现了异常 也算失败
					Message msg=new Message();
					msg.what=ACTION_LOAD_FAILURE;
					mHandler.sendMessage(msg);
				}
			}
		}.start();
	}

}
