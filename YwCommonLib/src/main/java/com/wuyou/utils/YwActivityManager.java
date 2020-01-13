/*
 * Copyright (c) 2020.
 * Created by QiuQiu on 2020/01/13.
 *  All Rights Reserved.
 */

package com.wuyou.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * <pre>
 *     author: YanWen
 *     time  : 2020/01/13
 *     desc  : Activity管理工具类
 * </pre>
 */
public final class YwActivityManager {

    private static YwActivityManager instance;

    private List<Activity> activities = new ArrayList<Activity>();

    private YwActivityManager() {
        throw new UnsupportedOperationException("Can't instantiate YwActivityManager..." +
                " Consider use getInstance() instead!");
    }

    public static YwActivityManager getInstance() {
        if (instance == null) {
            synchronized (YwActivityManager.class) {
                if (instance == null) {
                    instance = new YwActivityManager();
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
