/*
 * Copyright (c) 2020.
 * Created by QiuQiu on 2020/1/8.
 *  All Rights Reserved.
 */
package com.wuyou.common.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity相关管理工具
 * Created by QiuQiu on 2020/1/8.
 */
public class ActivityUtils {
    private static ActivityUtils instance;

    private List<Activity> activities = new ArrayList<Activity>();

    private ActivityUtils() {
    }

    public static ActivityUtils getInstance() {
        if (instance == null) {
            synchronized (ActivityUtils.class) {
                if (instance == null) {
                    instance = new ActivityUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity
     * @param activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 移除Activity
     * @param activity
     */
    public void removeActivity(Activity activity) {
        try {
            for (int i = 0; i < activities.size(); i++) {
                Activity act = activities.get(i);
                if (activity != null && activity == act) {
                    activities.remove(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 退出程序
     */
    public void finishAll() {
        for (Activity activity : activities) {
            activity.finish();
        }
        activities.clear();
    }
}
