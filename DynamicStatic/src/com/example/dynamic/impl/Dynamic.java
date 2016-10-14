package com.example.dynamic.impl;

import com.example.dynamic.callback.DialogCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.SystemClock;
import android.widget.Toast;

/** 
 ** FBI WARNING * MAGIC * DO NOT TOUCH **
 ** CREATED BY NICK @ 2016年10月12日 下午1:52:42 
 */

public class Dynamic {
	private static Activity mActivity;

	public static void init(Activity activity) {
		// TODO Auto-generated method stub
		mActivity = activity;
	}

	public static void showBanner() {
		// TODO Auto-generated method stub
		Toast.makeText(mActivity, "showBanner", 0).show();
		new Thread() {
			public void run() {
				safeToast("我在子线程里睡5秒");
				SystemClock.sleep(5000);
				safeToast("睡醒了。。。");
			}

		}.start();
	}

	private static String result = "";

	public static String showDialog(final DialogCallback callback) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
		AlertDialog create = builder.setTitle("哈哈").setMessage("我在asset的jar中")
				.setPositiveButton("确定", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						result = "确定";
						callback.onConfirm();
						dialog.dismiss();
					}
				}).setNegativeButton("取消", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						result = "取消";
						callback.onCancel();
						dialog.dismiss();
					}
				}).show();
		return result;

	}

	public static void destory() {
		// TODO Auto-generated method stub
		Toast.makeText(mActivity, "destory", 0).show();
		mActivity.finish();
	}

	private static void safeToast(final String msg) {
		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(mActivity, msg, 0).show();
			}
		});
	};

}
