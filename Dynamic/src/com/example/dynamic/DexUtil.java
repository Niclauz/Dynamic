package com.example.dynamic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.util.Log;
import dalvik.system.DexClassLoader;

/** 
 ** FBI WARNING * MAGIC * DO NOT TOUCH **
 ** CREATED BY NICK @ 2016年10月14日 上午10:27:50 
 */

public class DexUtil {

	private static final String TAG = "DexUtil";

	public static Class loadClass(Context context,String className) throws ClassNotFoundException {
		Class dynamicClazz;
		// 使用dexClassLoader加载
		// assets文件绝对路径
		// String path = "file:///android_asset/Dyna.jar";
		// String dexPath = Environment.getExternalStorageDirectory().toString()
		// + File.separator + "Dynamic.apk";
		// dex解压后的路径
		// String dexOutputDir =
		// Environment.getDataDirectory().getAbsolutePath();
		// String dexOutputDir = getExternalFilesDir(null).getAbsolutePath();
		String dexOutputDir = Constant.getDexOutputDir();

		Log.i(TAG, "----dexOutputDir::" + dexOutputDir);
		DexClassLoader dexClassLoader = new DexClassLoader(Constant.getCopiedDexPath(), dexOutputDir, null,
				context.getClassLoader());

		// dexClassLoader加载类
		// 动态类名
		dynamicClazz = dexClassLoader.loadClass(className);

		return dynamicClazz;
	}

	/**
	 * 从asset中复制文件
	 * @param source
	 * @param target
	 */
	public static boolean copyFile(Context context) {
		boolean success = false;

		// 拷贝asset文件
		// String SD =
		// Environment.getExternalStorageDirectory().getAbsolutePath();

		deleteExitFile(Constant.getDataPath() + File.separator + Constant.getDataFileName());
		File targetFile = new File(Constant.getDataPath());
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			InputStream in = context.getAssets().open(Constant.getAssetFileName());
			FileOutputStream out = new FileOutputStream(new File(targetFile, Constant.getDataFileName()));

			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = in.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	private static void deleteExitFile(String path) {
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
	}
}
