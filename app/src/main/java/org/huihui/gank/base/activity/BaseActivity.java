package org.huihui.gank.base.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;


/**
 * UserInfoResponse: Administrator
 * Date: 2016-09-08 {HOUR}:06
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();

    /**
     * hanlder
     */
    protected Handler mHandler = new Handler(Looper.getMainLooper());
    /**
     * 主线程id
     */
    public long UiThreadId = Thread.currentThread().getId();

    /**
     * 加载条
     */

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getIActivityManager().add(this);
        UiThreadId = Thread.currentThread().getId();
        initBaseView();
        initVariable();
        initView();
        initData();
    }

    /**
     * 获取父布局layout
     *
     * @return layoutResId
     */
    @LayoutRes
    protected abstract int getLayoutResId();

    /**
     * 初始化变量
     */
    protected abstract void initVariable();

    /**
     * 初始化UI界面
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    protected abstract void initBaseView();

    protected abstract IActivityManager getIActivityManager();

    public Activity getActivity() {
        return this;
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getIActivityManager().delete(this);
    }
}  