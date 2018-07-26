package com.beadwallet.beautymirror.feature.base;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


import com.beadwallet.beautymirror.R;
import com.beadwallet.beautymirror.app.GlobleApplication;
import com.beadwallet.beautymirror.di.module.base.BaseActivityModule;
import com.beadwallet.beautymirror.feature.base.PermissionActivity;
import com.beadwallet.beautymirror.util.ActivityUtil;
import com.elvishew.xlog.XLog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import butterknife.ButterKnife;


public abstract class BaseActivity extends PermissionActivity {


  private static final String                   TAG         = "BaseActivity";
  private              String                   mSimpleName = this.getClass().getSimpleName();
  private              ArrayList<BasePresenter> mPresenters = new ArrayList<>();
  protected ViewGroup mRootView;

  private SystemBarTintManager tintManager;

  /**
   * 状态栏颜色
   */
  @TargetApi(19)
  private void statusbarTint(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
      mRootView.setFitsSystemWindows(true);
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
      tintManager = new SystemBarTintManager(this);
      tintManager.setStatusBarTintColor(ContextCompat.getColor(this, R.color.colorPrimary));
      tintManager.setStatusBarTintEnabled(true);
    }
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    printLifeCycle("onCreate()");
    ActivityUtil.getManager().addActivity(this);
    if (layoutResID() != 0) {
      initContentView();
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      ButterKnife.bind(this);
    }
    if (useEventBus()) {
      EventBus.getDefault().register(this);
    }
    setupActivityComponent();
    if (getPresenter() != null && getPresenters() != null) {
      throw new RuntimeException("cannot override both getPresenter() and getPresenters()");
    }
    if (getPresenter() != null) {
      mPresenters.add(getPresenter());
    }
    if (getPresenters() != null) {
      mPresenters.addAll(Arrays.asList(getPresenters()));
    }
  }

  @CallSuper
  protected void initContentView() {
    mRootView = (ViewGroup) View.inflate(this, layoutResID(), null);
    setContentView(mRootView);
    statusbarTint();
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    printLifeCycle("onPostCreate()");
    handleArguments(savedInstanceState);
    initializeUI(savedInstanceState);
    loadData();
    if (mPresenters.size() > 0) {
      for (BasePresenter presenter : mPresenters) {
        presenter.onCreate();
      }
    }
  }

  private void initializeUI(Bundle savedInstanceState) {
    initdefaultUI(savedInstanceState);
  }

  protected void loadData() {

  }

  @Override
  protected void onStart() {
    super.onStart();
    printLifeCycle("onStart()");
    if (mPresenters.size() > 0) {
      for (BasePresenter presenter : mPresenters) {
        presenter.onStart();
      }
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    printLifeCycle("onResume()");
    if (mPresenters.size() > 0) {
      for (BasePresenter presenter : mPresenters) {
        presenter.onResume();
      }
    }
  }

  @Override
  protected void onPause() {
    super.onPause();
    printLifeCycle("onPause()");
    if (mPresenters.size() > 0) {
      for (BasePresenter presenter : mPresenters) {
        presenter.onPause();
      }
    }
  }

  @Override
  protected void onStop() {
    super.onStop();
    printLifeCycle("onStop()");
    if (mPresenters.size() > 0) {
      for (BasePresenter presenter : mPresenters) {
        presenter.onStop();
      }
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    printLifeCycle("onDestroy()");
    if (useEventBus()) {
      EventBus.getDefault().unregister(this);
    }
    if (mPresenters.size() > 0) {
      for (BasePresenter presenter : mPresenters) {
        presenter.onDestroy();
      }
    }
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    printLifeCycle("onRestart()");
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    printLifeCycle("onRestoreInstanceState()");
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    printLifeCycle("onSaveInstanceState()");
  }

  protected abstract @LayoutRes
  int layoutResID();

  protected @Nullable
  BasePresenter getPresenter() {
    return null;
  }

  protected @Nullable
  BasePresenter[] getPresenters() {
    return null;
  }

  protected boolean useEventBus() {
    return false;
  }

  /**
   * 处理参数
   */
  protected void handleArguments(Bundle savedInstanceState) {
  }

  /**
   * 商户角色界面初始化
   */
  protected abstract void initdefaultUI(Bundle savedInstanceState);
  


  private void printLifeCycle(String methodName) {
  //  XLog.tag(TAG).i(mSimpleName + "::" + methodName);
  }

  protected void setupActivityComponent() {
    getGlobleApplication().getBaseComponent().plus(new BaseActivityModule(this)).inject(this);
  }

  public GlobleApplication getGlobleApplication() {
    return ((GlobleApplication) getApplication());
  }

  @Override
  public void finish() {
    ActivityUtil.getManager().finishActivity(this);
  }

  public void originFinish() {
    super.finish();
  }
}
