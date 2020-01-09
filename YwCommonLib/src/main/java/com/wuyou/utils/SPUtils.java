/*
 * Copyright (c) 2017.
 * Created by QiuQiu on 2017/7/5.
 * All Rights Reserved.
 */
package com.wuyou.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 封装SharePreferences
 */
public class SPUtils {
    /**
     * 定义默认的SharePreferences配置文件的文件名
     */
    private static final String FILE_NAME = "config";

    /**
     * 设置值,使用Object可设置各种类型的值
     * @param context
     * @param filename  配置文件名，但是这里未用到，默认给空字符串即可
     * @param key
     * @param value
     */
    public static void setSP(Context context, String filename, String key, Object value) {
        String type = value.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        Editor edit = sp.edit();

        if ("String".equals(type)) {
            edit.putString(key, (String) value);
        } else if ("Integer".equals(type)) {
            edit.putInt(key, (Integer) value);
        } else if ("Boolean".equals(type)) {
            edit.putBoolean(key, (Boolean) value);
        } else if ("Float".equals(type)) {
            edit.putFloat(key, (Float) value);
        } else if ("Long".equals(type)) {
            edit.putLong(key, (Long) value);
        }

        edit.commit();
    }

    /**
     * 根据key获取值
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static Object getSP(Context context, String key, Object defValue) {
        String type = defValue.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if ("String".equals(type)) {
            return sp.getString(key, (String) defValue);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defValue);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defValue);
        }

        return null;
    }

    /**
     * 获取bool类型值
     */
    public static boolean getBoolean(Context context, String key, boolean defValue){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    /**
     * 设置bool类型值
     */
    public static void setBoolean(Context context, String key, boolean value){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 获取int类型值
     */
    public static int getInt(Context context, String key, int defValue){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    /**
     * 设置int类型值
     */
    public static void setInt(Context context, String key, int value){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 获取float类型值
     */
    public static float getFloat(Context context, String key, float defValue){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }

    /**
     * 设置float类型值
     */
    public static void setFloat(Context context, String key, float value){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        sp.edit().putFloat(key, value).commit();
    }

    /**
     * 获取long类型值
     */
    public static float getLong(Context context, String key, long defValue){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    /**
     * 设置long类型值
     */
    public static void setLong(Context context, String key, long value){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        sp.edit().putLong(key, value).commit();
    }

    /**
     * 获取String类型值
     */
    public static String getString(Context context, String key, String defValue){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    /**
     * 设置String类型值
     */
    public static void setString(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }
}
