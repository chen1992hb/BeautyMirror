package com.beadwallet.beautymirror.di.component.base;

import com.beadwallet.beautymirror.di.component.demo.DemoActivityComponent;
import com.beadwallet.beautymirror.di.module.base.BaseActivityModule;
import com.beadwallet.beautymirror.di.module.base.BaseFragmentModule;
import com.beadwallet.beautymirror.di.module.base.BaseModule;
import com.beadwallet.beautymirror.di.module.demo.DemoActivityModule;

import dagger.Subcomponent;

@Subcomponent(modules = BaseModule.class)
public interface BaseComponent {
  BaseActivityComponent plus(BaseActivityModule baseActivityModule);
  BaseFragmentComponent plus(BaseFragmentModule baseFragmentModule);
  DemoActivityComponent plus(DemoActivityModule demoActivityModule);
}
