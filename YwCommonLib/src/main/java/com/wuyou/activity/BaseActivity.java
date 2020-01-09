package com.wuyou.activity;

import android.app.Activity;
import android.os.Bundle;

import com.wuyou.utils.ActivityUtils;

/**
 * Activity的基类，定义一些基本架构，不需要显示界面，故而省略setContentView（resId）
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUtils.getInstance().addActivity(this);

        initVariables();
        initViews(savedInstanceState);
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityUtils.getInstance().removeActivity(this);
    }

    protected abstract void initVariables();
    protected abstract void initViews(Bundle savedInstanceState);
    protected abstract void loadData();
}
