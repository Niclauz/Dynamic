package com.example.dynamic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import com.example.dynamic.callback.DialogCallback;
import com.example.dynamic.interfaces.IDynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import dalvik.system.DexClassLoader;

public class MainActivity extends Activity implements OnClickListener {
	private static final String TAG = null;
	private Context mContext;
	// private IDynamic lib;

	private IDynamic lib;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		findViewById(R.id.btn_show_banner).setOnClickListener(this);
		findViewById(R.id.btn_show_dialog).setOnClickListener(this);
		findViewById(R.id.btn_destory).setOnClickListener(this);

		// 执行一次即可
		Constant.init(mContext);

		boolean copyFile = DexUtil.copyFile(mContext);
		Log.i(TAG, "----copyFile::" + copyFile);

		// Dynamic.showBanner();

		try {
			Class dynamicClazz = DexUtil.loadClass(mContext, Constant.getDynamicClassName());

			Log.i(TAG, "----dynamicClazz::" + dynamicClazz.getName());
			Log.i(TAG, "----dynamic::" + dynamicClazz.newInstance().getClass().getName());
			Log.i(TAG, "----dynamic::" + dynamicClazz.newInstance().toString());
			lib = (IDynamic) dynamicClazz.newInstance();

			// Method method = dynamicClazz.getMethod("init", Activity.class);
			// method.invoke(null, MainActivity.this);

			// dynamic = new Dynamic();
			// dynamic.init(MainActivity.this);

			if (lib != null) {
				lib.init(MainActivity.this);
			} else {
				Toast.makeText(getApplicationContext(), "加载失败", 0).show();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_show_banner:

			// try {
			// Method method2 = dynamicClazz.getMethod("showBanner", null);
			// method2.invoke(null, null);
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			if (lib != null) {
				lib.showBanner();
			} else {
				Toast.makeText(getApplicationContext(), "加载失败", 0).show();
			}
			break;
		case R.id.btn_show_dialog:

			// try {
			// Class<?> loadClass =
			// dexClassLoader.loadClass("com.example.dynamic.callback.DialogCallback");
			// Method method = dynamicClazz.getMethod("showDialog",
			// DialogCallback.class);
			// method.invoke(null, new DialogCallback() {
			//
			// @Override
			// public void onConfirm() {
			// Toast.makeText(getApplicationContext(), "callback confirm",
			// 0).show();
			// }
			//
			// @Override
			// public void onCancel() {
			// Toast.makeText(getApplicationContext(), "callback cancel",
			// 0).show();
			// }
			//
			// });
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			if (lib != null) {
				String result = lib.showDialog(new DialogCallback() {

					@Override
					public void onConfirm() {
						Toast.makeText(getApplicationContext(), "callback confirm", 0).show();
						startActivity(new Intent(mContext, Activity2.class));
					}

					@Override
					public void onCancel() {
						Toast.makeText(getApplicationContext(), "callback cancel", 0).show();
					}
				});
			} else {
				Toast.makeText(getApplicationContext(), "加载失败", 0).show();
			}
			break;
		case R.id.btn_destory:
			if (lib != null) {
				lib.destory();
			}
			break;

		default:
			break;
		}
	}

}
