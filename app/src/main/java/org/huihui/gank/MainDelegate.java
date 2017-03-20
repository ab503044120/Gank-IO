package org.huihui.gank;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.huihui.gank.view.DatabindingDelegate;

/**
 * Created by Administrator on 2017/3/20.
 */

public class MainDelegate extends DatabindingDelegate {
    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public int getRootLayoutId() {
        return 0;
    }

    @Override
    public void initWidget(Bundle savedInstanceState) {

    }
}
