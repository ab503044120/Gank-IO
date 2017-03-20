package org.huihui.gank.view;

import android.view.View;

/**
 * User: Administrator
 * Date: 2017-03-01 {HOUR}:13
 */
public abstract class BaseDelegate implements IDelegate {
    protected View mRootView;

    @Override
    public View getRootView() {
        return mRootView;
    }
}