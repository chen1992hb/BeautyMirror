package com.beadwallet.beautymirror.di.module.base;

import com.beadwallet.beautymirror.feature.base.BaseActivity;
import dagger.Module;


@Module
public class BaseActivityModule {
  private BaseActivity mBaseActivity;

  public BaseActivityModule(BaseActivity baseActivity) {
    mBaseActivity = baseActivity;
  }
}
