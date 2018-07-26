package com.beadwallet.beautymirror.di.module.demo;

import com.beadwallet.beautymirror.di.scope.ActivityScope;
import com.beadwallet.beautymirror.feature.demo.DemoActivity;
import com.beadwallet.beautymirror.feature.demo.DemoPresenter;
import com.beadwallet.beautymirror.mapper.DemoModelMapper;
import com.beadwallet.domain.interactor.DemoUserCase;

import dagger.Module;
import dagger.Provides;

@Module
public class DemoActivityModule {
    private DemoActivity mDemoActivity;
    public DemoActivityModule(DemoActivity mDemoActivity){
        this.mDemoActivity=mDemoActivity;
    }
    @ActivityScope
    @Provides
    public DemoPresenter provideRefundPresenter(DemoUserCase demoUserCase,
                                                DemoModelMapper demoModelMapper
                                              ){
        return new DemoPresenter(mDemoActivity, demoUserCase, demoModelMapper);
    }
}
