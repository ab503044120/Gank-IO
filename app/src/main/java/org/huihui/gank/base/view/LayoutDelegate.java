package org.huihui.gank.base.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * User: Administrator
 * Date: 2017-03-01 {HOUR}:38
 */
public abstract class LayoutDelegate extends BaseDelegate {
    @Override
    public View create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getRootLayoutId(), container, false);
        return mRootView;
    }
    @CallSuper
    @Override
    public void initWidget(Bundle savedInstanceState) {
        ButterKnife.bind(this,mRootView);
    }
}