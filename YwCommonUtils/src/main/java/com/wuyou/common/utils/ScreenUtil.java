/*
 * Copyright (c) 2020.
 * Created by QiuQiu on 2020/1/8.
 * All Rights Reserved.
 */
package com.wuyou.common.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 屏幕管理工具，获取设备宽高
 */
public class ScreenUtil {
    private ScreenUtil() {
        throw new AssertionError();
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;

        return screenWidth;
    }

    /**
     * 获取屏幕高度
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenHeight = dm.heightPixels;

        return screenHeight;
    }
}
