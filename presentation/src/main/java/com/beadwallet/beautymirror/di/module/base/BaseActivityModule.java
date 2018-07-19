package com.beadwallet.beautymirror.di.module.base;

import com.beadwallet.beautymirror.feature.base.BaseActivity;
import dagger.Module;

/**
 * @author wanggeng
 * @desc 类描述
 * @date 创建时间：2017/12/16
 */

@Module
public class BaseActivityModule {
  private BaseActivity mBaseActivity;

  public BaseActivityModule(BaseActivity baseActivity) {
    mBaseActivity = baseActivity;
  }
}
