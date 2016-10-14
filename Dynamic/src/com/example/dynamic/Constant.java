package com.example.dynamic;

import java.io.File;

import android.content.Context;

/** 
 ** FBI WARNING * MAGIC * DO NOT TOUCH **
 ** CREATED BY NICK @ 2016年10月14日 上午10:02:44 
 */

public class Constant {
	private static Context context;

	public static void init(Context context) {
		Constant.context = context;
		dataPath = context.getFilesDir().getAbsolutePath();
		dexOutputDir = context.getFilesDir().getAbsolutePath();
	}

	private static String assetFileName = "Dyna.jar";

	private static String dataFileName = "Dyna.jar";

	private static String dynamicClassName = "com.example.dynamic.impl.Dynamic";

	private static String dataPath = "";
	private static String dexOutputDir = "";

	// 拷贝后文件路径
	private static String copiedDexPath = "";

	public static String getAssetFileName() {
		return assetFileName;
	}

	public static void setAssetFileName(String assetFileName) {
		Constant.assetFileName = assetFileName;
	}

	public static String getDataFileName() {
		return dataFileName;
	}

	public static void setDataFileName(String dataFileName) {
		Constant.dataFileName = dataFileName;
	}

	public static String getDynamicClassName() {
		return dynamicClassName;
	}

	public static void setDynamicClassName(String dynamicClassName) {
		Constant.dynamicClassName = dynamicClassName;
	}

	public static String getDataPath() {
		return dataPath;
	}

	public static void setDataPath(String dataPath) {
		Constant.dataPath = dataPath;
	}

	public static String getDexOutputDir() {
		return dexOutputDir;
	}

	public static void setDexOutputDir(String dexOutputDir) {
		Constant.dexOutputDir = dexOutputDir;
	}

	public static String getCopiedDexPath() {
		return Constant.dataPath + File.separator + Constant.dataFileName;
	}

	public static void setCopiedDexPath(String copiedDexPath) {
		Constant.copiedDexPath = copiedDexPath;
	}

}
