package com.beadwallet.beautymirror.di.component.base;


import com.beadwallet.beautymirror.di.module.base.BaseFragmentModule;
import com.beadwallet.beautymirror.di.scope.FragmentScope;
import com.beadwallet.beautymirror.feature.base.BaseFragment;

import dagger.Subcomponent;



@FragmentScope
@Subcomponent(modules = BaseFragmentModule.class)
public interface BaseFragmentComponent {
  void inject(BaseFragment baseFragment);
}
