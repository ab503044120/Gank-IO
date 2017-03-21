package org.huihui.gank.base.activity;

import android.app.Activity;

import java.util.List;

/**
 * User: Administrator
 * Date: 2017-02-25 {HOUR}:35
 */
public interface IActivityManager {

    List<Activity> getActiivtys();

     void add(Activity activity);

     void delete(Activity activity);

     Activity delete(Class<? extends Activity> activity);
}