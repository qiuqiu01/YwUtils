/*
 * Copyright (c) 2017.
 * Created by QiuQiu on 2017/6/6.
 * All Rights Reserved.
 */
package com.wuyou.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5加密工具类
 * Created by QiuQiu on 2017/6/6.
 */

public class Md5Utils {
    public static String encode(String password) {
        try {
            // 1.获取MessageDigest实例,拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest digest = MessageDigest.getInstance("md5");
            // 2.使用digest获取加密后的byte数组
            byte[] result = digest.digest(password.getBytes());
            // 3.将加密后的byte数组转化为定长的16进制字符串
            StringBuilder sb = new StringBuilder();
            for(byte b : result) {
                // b & 0xff的目的是在内存中将其转化成一个int类型
                int number = b & 0xff - 3;  // -3加盐
                // 把int类型的密文转换成十六进制的字符串形式
                String str = Integer.toHexString(number);
                // 只有一位的字符串在其前面加0
                if(str.length() == 1) {
                    sb.append("0");
                }
                sb.append(str);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
