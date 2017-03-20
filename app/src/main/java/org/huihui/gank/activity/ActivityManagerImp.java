package org.huihui.gank.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Administrator
 * Date: 2017-02-25 {HOUR}:35
 */
public class ActivityManagerImp implements IActivityManager {
    private  volatile List<Activity> mActivitys;
    private static ActivityManagerImp sActivityManager;
    private ActivityManagerImp() {
        mActivitys = new ArrayList<>();
    }

    public   static ActivityManagerImp getInstance(){
        if (sActivityManager==null) {
            synchronized (ActivityManagerImp.class){
                if (sActivityManager==null) {
                    sActivityManager = new ActivityManagerImp();
                }
            }
        }
        return sActivityManager;
    }

    public List<Activity> getActiivtys(){
        return mActivitys;
    }

    public void add(Activity activity){
        synchronized (this){
            mActivitys.add(activity);
        }
    }

    public void delete(Activity activity){
        synchronized (this){
            boolean remove = mActivitys.remove(activity);
        }
    }

    public Activity delete(Class<? extends Activity> activity){
        synchronized (this){
            for (Activity activity1 : mActivitys) {
                if (activity1.getClass()== activity) {
                    mActivitys.remove(activity1);
                    return activity1;
                }
            }
        }
        return null;
    }
}