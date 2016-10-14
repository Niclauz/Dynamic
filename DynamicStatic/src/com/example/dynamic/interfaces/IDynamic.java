package com.example.dynamic.interfaces;

import com.example.dynamic.callback.DialogCallback;

import android.app.Activity;

/** 
 ** FBI WARNING * MAGIC * DO NOT TOUCH **
 ** CREATED BY NICK @ 2016年10月12日 下午1:50:35 
 */

public interface IDynamic {
	public void init(Activity activity);

	public void showBanner();

	public String showDialog(DialogCallback callback);

	public void destory();

}
