/*
 * Copyright (c) 2020.
 * Created by QiuQiu on 2020/01/09.
 * All Rights Reserved.
 */

package com.wuyou.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 *     author: YanWen
 *     time  : 2020/01/09
 *     desc  : 日期时间管理工具类
 * </pre>
 */
public final class DateUtils {
    private static final SimpleDateFormat DATE_FORMAT_DATETIME =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT_DATE =
            new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_FORMAT_TIME =
            new SimpleDateFormat("HH:mm:ss");

    private DateUtils() {
        throw new UnsupportedOperationException("U can't instantiate me...");
    }

    /**
     * 格式化日期时间
     * @param date
     * @return
     */
    public static String formatDataTime(long date) {
        return DATE_FORMAT_DATETIME.format(new Date(date));
    }

    /**
     * 格式化日期
     * @param date
     * @return
     */
    public static String formatDate(long date) {
        return DATE_FORMAT_DATE.format(new Date(date));
    }

    /**
     * 格式化时间
     * @param date
     * @return
     */
    public static String formatTime(long date) {
        return DATE_FORMAT_TIME.format(new Date(date));
    }

    /**
     * 自定义格式的格式化日期时间
     * @param beginDate
     * @param format
     * @return
     */
    public static String formatDateCustom(String beginDate, String format) {
        return new SimpleDateFormat(format).format(new Date(Long.parseLong(beginDate)));
    }

    public static String formatDateCustom(Date beginDate, String format) {
        return new SimpleDateFormat(format).format(beginDate);
    }

    /**
     * 将时间字符串转换成Date
     * @param s
     * @param style
     * @return
     */
    public static Date string2Date(String s, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        Date date = null;
        if (s == null || s.length() < 6) {
            return null;
        }
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前系统时间，格式：12:36:41
     * @return
     */
    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
    }

    /**
     * 计算两个时间差
     * @param dateStart
     * @param dateEnd
     * @return
     */
    public static long subtractDate(Date dateStart, Date dateEnd) {
        return dateEnd.getTime() - dateStart.getTime();
    }

    /**
     * 得到几天后的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);

        return now.getTime();
    }

    /**
     * 获取年份
     * @return
     */
    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        return year;
    }

    /**
     * 获取月份
     * @return
     */
    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);

        return month + 1;
    }

    /**
     * 获取自然月的日 - DAY_OF_MONTH
     * The first day of the month has value 1.
     * @return
     */
    public static int getDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return day;
    }

    /**
     * 获取自然年的日 - DAY_OF_YEAR
     * number within the current year.  The first day of the year has value 1.
     * @return
     */
    public static int getDayOfYear() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);

        return day;
    }

    /**
     * 获取当前时间为本月的第几周
     * @return
     */
    public static int getWeekOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.WEEK_OF_MONTH);

        return week;
    }

    /**
     * 获取当前时间为当前时间为当年第几周
     * @return
     */
    public static int getWeekOfYear() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.WEEK_OF_YEAR);

        return week;
    }

    /**
     * 获取当前时间为本周的第几天
     * @return
     */
    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1) {
            day = 7;
        } else {
            day = day - 1;
        }

        return day;
    }
}
