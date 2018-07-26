package com.beadwallet.beautymirror.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.beadwallet.beautymirror.feature.base.BaseActivity;

import java.util.Stack;


public class ActivityUtil {

  // Activity栈
  private static Stack<BaseActivity> activityStack;
  // 单例模式
  private static ActivityUtil        instance;

  private ActivityUtil() {
  }

  /**
   * 单一实例
   */
  public static ActivityUtil getManager() {
    if (instance == null) {
      instance = new ActivityUtil();
    }
    return instance;
  }

  /**
   * 添加Activity到堆栈
   */
  public void addActivity(BaseActivity activity) {
    if (activityStack == null) {
      activityStack = new Stack<BaseActivity>();
    }
    activityStack.add(activity);
  }

  public void removeActivity(BaseActivity activity){
    if (activity != null) {
      activityStack.remove(activity);
      activity = null;
    }
  }

  /**
   * 获取当前Activity（堆栈中最后一个压入的）
   */
  public Activity currentActivity() {
    Activity activity = activityStack.lastElement();
    return activity;
  }

  /**
   * 结束当前Activity（堆栈中最后一个压入的）
   */
  public void finishActivity() {
    BaseActivity activity = activityStack.lastElement();
    finishActivity(activity);
  }

  /**
   * 结束指定的Activity
   */
  public void finishActivity(BaseActivity activity) {
    if (activity != null) {
      activityStack.remove(activity);
      activity.originFinish();
      activity = null;
    }
  }

  /**
   * 结束指定类名的Activity
   */
  public void finishActivity(Class<?> cls) {
    for (BaseActivity activity : activityStack) {
      if (activity.getClass().equals(cls)) {
        finishActivity(activity);
      }
    }
  }

  /**
   * 结束所有Activity
   */
  public void finishAllActivity() {
    for (int i = 0; i < activityStack.size(); i++) {
      if (null != activityStack.get(i)) {
        activityStack.get(i).originFinish();
      }
    }
    activityStack.clear();
  }

  /**
   * 退出应用程序
   */
  public void AppExit(Context context) {
    try {
      finishAllActivity();
      ActivityManager activityMgr = (ActivityManager) context
          .getSystemService(Context.ACTIVITY_SERVICE);
      activityMgr.killBackgroundProcesses(context.getPackageName());
      System.exit(0);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}