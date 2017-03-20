package org.huihui.gank.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * User: Administrator
 * Date: 2017-03-01 {HOUR}:23
 */
public abstract class DatabindingDelegate<V extends ViewDataBinding> extends BaseDelegate {
    private ViewDataBinding mViewDataBinding;

    @Override
    public View create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getRootLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();
        return mRootView;
    }
}