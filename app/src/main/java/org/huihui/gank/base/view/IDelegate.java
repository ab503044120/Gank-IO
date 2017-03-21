package org.huihui.gank.base.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * View 的代理类
 * UserInfoResponse: Administrator
 * Date: 2016-09-09 {HOUR}:06
 */
public interface IDelegate {

    View create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    int getOptionsMenuId();

    Toolbar getToolbar();

    View getRootView();

    int getRootLayoutId();

    void initWidget(Bundle savedInstanceState);
}  