package com.beadwallet.beautymirror.di.component.base;

import com.beadwallet.beautymirror.di.module.base.BaseActivityModule;
import com.beadwallet.beautymirror.feature.base.BaseActivity;

import dagger.Subcomponent;

/**
 * @author wanggeng
 * @desc 类描述
 * @date 创建时间：2017/12/16
 */

@Subcomponent(modules = BaseActivityModule.class)
public interface BaseActivityComponent {
  void inject(BaseActivity baseActivity);
}
