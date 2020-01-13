/*
 * Copyright (c) 2020.
 * Created by QiuQiu on 2020/01/08.
 * All Rights Reserved.
 */
package com.wuyou.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * <pre>
 *     author: YanWen
 *     time  : 2020/01/08
 *     desc  : Toast工具类
 * </pre>
 */
public final class ToastUtils {

	private ToastUtils() {
		throw new UnsupportedOperationException("U can't instantiate me...");
	}

	/**
	 * 之前显示的内容
	 */
	private static String oldMsg;

	/**
	 * Toast对象
	 */
	private static Toast toast = null;

	/**
	 * 第一次时间
	 */
	private static long oneTime = 0;

	/**
	 * 第二次时间
	 */
	private static long twoTime = 0;

	/**
	 * 单例模式显示Toast,稍作优化
	 * @param context
	 * @param message
	 */
	public static void showToast(Context context, String message) {
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			toast.show();
			// 记录第一次显示的时间
			oneTime = System.currentTimeMillis();
		} else {
			// 获取第二次显示的时间
			twoTime = System.currentTimeMillis();
			if (message.equals(oldMsg)) {
				// 优化显示，如果两次显示内容一样，则仅当两次时间间隔大于LENGTH_SHORT时才显示
				if (twoTime - oneTime > Toast.LENGTH_SHORT) {
					toast.show();
				}
			} else {
				oldMsg = message;
				toast.setText(message);
				toast.show();
			}
		}
		oneTime = twoTime;
	}
}
