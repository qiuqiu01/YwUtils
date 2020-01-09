/*
 * Copyright (c) 2017.
 * Created by QiuQiu on 2017/7/5.
 * All Rights Reserved.
 */
package com.wuyou.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Drawable工具类
 */
public class DrawableUtils {
	/**
	 * 生成圆角图片，对应的xml中的shape标签
	 * @return
	 */
	public static Drawable generateDrawable(int rgb,float radius){
		GradientDrawable drawable = new GradientDrawable();
		drawable.setShape(GradientDrawable.RECTANGLE);	// 设置为矩形，默认就是矩形
		drawable.setColor(rgb);
		drawable.setCornerRadius(radius);
		return drawable;
	}
	
	/** 
	 * 用java代码的方式动态生成状态选择器
	 * @param pressed
	 * @param normal
	 * @return
	 */
	public static Drawable generateSelector(Drawable pressed,Drawable normal){
		StateListDrawable drawable = new StateListDrawable();
		drawable.addState(new int[]{android.R.attr.state_pressed}, pressed);	// 设置按下的图片
		drawable.addState(new int[]{}, normal);	// 设置默认的图片
		
		// 设置状态选择器过度动画
		drawable.setEnterFadeDuration(500);
		drawable.setExitFadeDuration(500);

		return drawable;
	}
}
