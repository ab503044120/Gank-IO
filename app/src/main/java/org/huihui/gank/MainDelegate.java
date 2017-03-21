package org.huihui.gank;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import org.huihui.gank.base.view.LayoutDelegate;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/20.
 */

public class MainDelegate extends LayoutDelegate {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rl_content)
    RecyclerView rlContent;
    @BindView(R.id.sr_content)
    SwipeRefreshLayout srContent;

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
        return R.layout.activity_main;
    }

    @Override
    public void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        rlContent.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

    }

    public void stopRefresh() {
        srContent.setRefreshing(false);
    }
}
