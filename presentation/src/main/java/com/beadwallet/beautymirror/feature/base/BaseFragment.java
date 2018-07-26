package com.beadwallet.beautymirror.feature.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beadwallet.beautymirror.app.GlobleApplication;
import com.beadwallet.beautymirror.di.module.base.BaseFragmentModule;
import com.beadwallet.data.cache.UserCache;
import com.elvishew.xlog.XLog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;



public abstract class BaseFragment extends PermissionFragment {




  private static final String TAG         = "BaseFragment";
  private String mSimpleName = this.getClass().getSimpleName();
  private ArrayList<BasePresenter> mPresenters = new ArrayList<>();

  private Unbinder mUnbinder;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    printLifeCycle("onAttach()");
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    printLifeCycle("onCreate()");
    setupFragmentComponent();
    if (getPresenter() != null && getPresenters() != null) {
      throw new RuntimeException("cannot override both getPresenter() and getPresenters()");
    }
    if (getPresenter() != null) {
      mPresenters.add(getPresenter());
    }
    if (getPresenters() != null) {
      mPresenters.addAll(Arrays.asList(getPresenters()));
    }
    if (useEventBus()) {
      EventBus.getDefault().register(this);
    }
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           Bundle savedInstanceState) {
    printLifeCycle("onCreateView()");
    View view = inflater.inflate(layoutResID(), container, false);
    mUnbinder = ButterKnife.bind(this, view);
    initView(view);
    return view;
  }

  protected void initView(View view) {

  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    printLifeCycle("onViewCreated()");
    handleArguments(savedInstanceState);
    initializeUI(savedInstanceState);
    if (mPresenters.size() > 0) {
      for (BasePresenter presenter : mPresenters) {
        presenter.onViewCreated();
      }
    }
  }

  protected void initializeUI(Bundle savedInstanceState){
    merchantUI(savedInstanceState);


  }

  protected void terminalUI(Bundle savedInstanceState) {

  }

  protected void storeUI(Bundle savedInstanceState) {

  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    printLifeCycle("onActivityCreated()");
    loadData();
  }

  @Override
  public void onStart() {
    super.onStart();
    printLifeCycle("onStart()");
    if (mPresenters.size() > 0) {
      for (BasePresenter presenter : mPresenters) {
        presenter.onStart();
      }
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    printLifeCycle("onResume()");
    if (mPresenters.size() > 0) {
      for (BasePresenter presenter : mPresenters) {
        presenter.onResume();
      }
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    printLifeCycle("onPause()");
    if (mPresenters.size() > 0) {
      for (BasePresenter presenter : mPresenters) {
        presenter.onPause();
      }
    }
  }

  @Override
  public void onStop() {
    super.onStop();
    printLifeCycle("onStop()");
    if (mPresenters.size() > 0) {
      for (BasePresenter presenter : mPresenters) {
        presenter.onStop();
      }
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    printLifeCycle("onDestroyView()");
    if (mUnbinder != null) {
      mUnbinder.unbind();
    }
    if (mPresenters.size() > 0) {
      for (BasePresenter presenter : mPresenters) {
        presenter.onDestroyView();
      }
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    printLifeCycle("onDestroy()");
    if (mPresenters.size() > 0) {
      for (BasePresenter presenter : mPresenters) {
        presenter.onDestroy();
      }
    }
    if (useEventBus()) {
      EventBus.getDefault().unregister(this);
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    printLifeCycle("onDetach()");
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    printLifeCycle("onSaveInstanceState()");
  }

  @Override
  public void onViewStateRestored(Bundle savedInstanceState) {
    super.onViewStateRestored(savedInstanceState);
    printLifeCycle("onViewStateRestored()");
  }

  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    printLifeCycle("onHiddenChanged()-->" + hidden);
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

  protected void handleArguments(Bundle savedInstanceState) {
  }

  protected abstract void merchantUI(Bundle savedInstanceState);

  protected void setupFragmentComponent(){
    getGlobleApplication().getBaseComponent().plus(new BaseFragmentModule(this)).inject(this);
  }

  protected boolean useEventBus() {
    return false;
  }

  private void printLifeCycle(String methodName) {
    XLog.tag(TAG).i(mSimpleName + "::" + methodName);
  }

  protected void loadData() {

  }

  private GlobleApplication getGlobleApplication(){
    return ((GlobleApplication) getActivity().getApplication());
  }
}
