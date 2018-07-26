package com.beadwallet.beautymirror.di.component.base;

import com.beadwallet.beautymirror.di.module.base.BaseActivityModule;
import com.beadwallet.beautymirror.feature.base.BaseActivity;

import dagger.Subcomponent;


@Subcomponent(modules = BaseActivityModule.class)
public interface BaseActivityComponent {
  void inject(BaseActivity baseActivity);
}
