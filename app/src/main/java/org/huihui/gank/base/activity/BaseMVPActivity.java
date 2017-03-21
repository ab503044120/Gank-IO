package org.huihui.gank.base.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.huihui.gank.base.view.IDelegate;

import java.lang.reflect.ParameterizedType;

import rx.subscriptions.CompositeSubscription;

/**
 * User: Administrator
 * Date: 2017-03-01 {HOUR}:30
 */
public abstract class BaseMVPActivity<D extends IDelegate> extends AppCompatActivity {
    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    protected final String TAG = getClass().getSimpleName();

    /**
     * hanlder
     */
    protected Handler mHandler = new Handler(Looper.getMainLooper());
    /**
     * 主线程id
     */
    protected long UiThreadId = Thread.currentThread().getId();

    protected D viewDelegate;

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getIActivityManager().add(this);
        UiThreadId = Thread.currentThread().getId();
        try {
            viewDelegate = ((Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("create IDelegate error");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("create IDelegate error");
        }
        setContentView(viewDelegate.create(getLayoutInflater(), (ViewGroup) findViewById(android.R.id.content), savedInstanceState));
        initToolbar();
        initVariable(savedInstanceState);
        viewDelegate.initWidget(savedInstanceState);
        initListener();
        initData(savedInstanceState);
    }


    /**
     * 初始化变量
     *
     * @param savedInstanceState
     */
    protected abstract void initVariable(Bundle savedInstanceState);

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);

    protected void initToolbar() {
        Toolbar toolbar = viewDelegate.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

//    private MaterialDialog loadingdialog;

//    /**
//     * 显示进度弹窗
//     */
//    public void showLoadingDialog(final String content) {
//        if (loadingdialog == null) {
//            loadingdialog = DialogUtils.loadDiaolog(getActivity(), R.color.btn_enable, "加载中...", false);
//        }
//        if (Thread.currentThread().getId() != UiThreadId) {
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    if (loadingdialog.isShowing()) {
//                        loadingdialog.dismiss();
//                    }
//                    loadingdialog.setContent(content);
//                    loadingdialog.show();
//                }
//            });
//        } else {
//            if (loadingdialog.isShowing()) {
//                loadingdialog.dismiss();
//            }
//            loadingdialog.setContent(content);
//            loadingdialog.show();
//        }
//    }
//
//    /**
//     * 隐藏进度弹窗
//     */
//    public void hindLoadingDialog() {
//        if (loadingdialog == null) {
//            return;
//        }
//        if (Thread.currentThread().getId() != UiThreadId) {
//            loadingdialog.dismiss();
//        } else {
//            loadingdialog.dismiss();
//        }
//    }
//
//    public void showOkToast(final String msg) {
//        if (Thread.currentThread().getId() != UiThreadId) {
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    ToastUtils.showOkToast(msg);
//                }
//            });
//        } else {
//            ToastUtils.showOkToast(msg);
//        }
//    }
//
//    public void showToast(final String msg) {
//        if (Thread.currentThread().getId() != UiThreadId) {
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    ToastUtils.showToast(msg);
//                }
//            });
//        } else {
//            ToastUtils.showToast(msg);
//        }
//    }
//
//    public void showErrToast(final String msg) {
//        if (Thread.currentThread().getId() != UiThreadId) {
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    ToastUtils.showErrToast(msg);
//                }
//            });
//        } else {
//            ToastUtils.showErrToast(msg);
//        }
//    }

    public void showToast(String s){
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (viewDelegate.getOptionsMenuId() != 0) {
            getMenuInflater().inflate(viewDelegate.getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public abstract void initListener();

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getIActivityManager().delete(this);
    }

    public Activity getActivity() {
        return this;
    }

    protected IActivityManager getIActivityManager() {
        return ActivityManagerImp.getInstance();
    }
}