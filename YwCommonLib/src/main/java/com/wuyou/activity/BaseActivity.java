package com.wuyou.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wuyou.utils.YwActivityManager;

/**
 * Activity的基类，定义一些基本架构，不需要显示界面，故而省略setContentView（resId）
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        YwActivityManager.getInstance().addActivity(this);

        initVariables();
        initViews(savedInstanceState);
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        YwActivityManager.getInstance().removeActivity(this);
    }

    protected abstract void initVariables();
    protected abstract void initViews(Bundle savedInstanceState);
    protected abstract void loadData();
}
