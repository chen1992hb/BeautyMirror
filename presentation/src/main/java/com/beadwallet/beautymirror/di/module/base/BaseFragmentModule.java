package com.beadwallet.beautymirror.di.module.base;

import com.beadwallet.beautymirror.feature.base.BaseFragment;

import dagger.Module;

/**
 * @author wanggeng
 * @desc 类描述
 * @date 创建时间：2018/1/16
 */

@Module
public class BaseFragmentModule {
  private BaseFragment mBaseFragment;

  public BaseFragmentModule(BaseFragment baseFragment) {
    mBaseFragment = baseFragment;
  }
}
