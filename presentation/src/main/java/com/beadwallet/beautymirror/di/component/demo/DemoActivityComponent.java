package com.beadwallet.beautymirror.di.component.demo;

import com.beadwallet.beautymirror.di.module.demo.DemoActivityModule;
import com.beadwallet.beautymirror.di.scope.ActivityScope;
import com.beadwallet.beautymirror.feature.demo.DemoActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = DemoActivityModule.class)
public interface DemoActivityComponent {
    void inject(DemoActivity demoActivity);
}
