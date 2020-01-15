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
public final class SimpleToastUtils {

	private SimpleToastUtils() {
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
	 * Android源码中的NotificationManagerService.java这个类中定义了两个静态变量，
	 * 分别对应Toast.LENGTH_LONG（3.5秒）和Toast.LENGTH_SHORT（2秒）的值
	 * https://blog.csdn.net/zapzqc/article/details/8502646
	 */
	private static final int LONG_DELAY = 3500; 	// 3.5 seconds
	private static final int SHORT_DELAY = 2000; 	// 2 seconds

	/**
	 * 稍作优化显示Toast;
	 * @param context	上下文
	 * @param message	Toast上要显示的文本信息
	 */
	public static void showToast(Context context, String message) {
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			toast.show();
			// 记录第一次显示的时间值
			oneTime = System.currentTimeMillis();
		} else {
			// 获取第二次显示的时间值
			twoTime = System.currentTimeMillis();
			if (message.equals(oldMsg)) {
				// 优化显示，如果两次显示内容一样，则仅当两次时间间隔大于LENGTH_SHORT对应时长才显示
				if (twoTime - oneTime > SHORT_DELAY) {
					toast.show();
				}
			} else {	// 两次信息不一致,则直接显示新的信息
				oldMsg = message;
				toast.setText(message);
				toast.show();
			}
		}
		oneTime = twoTime;
	}
}
