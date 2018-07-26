package com.beadwallet.beautymirror.di.module.base;

import com.beadwallet.beautymirror.feature.base.BaseFragment;

import dagger.Module;


@Module
public class BaseFragmentModule {
  private BaseFragment mBaseFragment;

  public BaseFragmentModule(BaseFragment baseFragment) {
    mBaseFragment = baseFragment;
  }
}
