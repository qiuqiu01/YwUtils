/*
 * Copyright (c) 2017.
 * Created by QiuQiu on 2017/7/5.
 * All Rights Reserved.
 */
package com.wuyou.common.utils;

import java.util.Random;

import android.graphics.Color;

/**
 * Color工具类
 */
public class ColorUtil {
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
