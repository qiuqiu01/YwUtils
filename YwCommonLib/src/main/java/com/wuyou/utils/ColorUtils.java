/*
 * Copyright (c) 2017.
 * Created by QiuQiu on 2017/07/05.
 * All Rights Reserved.
 */
package com.wuyou.utils;

import java.util.Random;

import android.graphics.Color;

/**
 * <pre>
 *     author: YanWen
 *     time  : 2017/07/05
 *     desc  : Color工具类
 * </pre>
 */
public final class ColorUtils {

	private ColorUtils() {
		throw new UnsupportedOperationException("U can't instantiate me...");
	}

	/**
	 * 随机生成漂亮的颜色
	 * @return
	 */
	public static int randomColor(){
		Random random = new Random();
		int red = random.nextInt(150);		// 0-190
		int green = random.nextInt(150);	// 0-190
		int blue = random.nextInt(150);		// 0-190
		return Color.rgb(red, green, blue);		// 使用rgb混合生成一种新的颜色
	}
}
