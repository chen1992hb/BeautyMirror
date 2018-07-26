package com.beadwallet.beautymirror.di.component;


import com.beadwallet.beautymirror.app.GlobleApplication;
import com.beadwallet.beautymirror.di.component.base.BaseComponent;
import com.beadwallet.beautymirror.di.module.ApplicationModule;
import com.beadwallet.beautymirror.di.module.RepositoryModule;
import com.beadwallet.beautymirror.di.module.base.BaseModule;

import dagger.Component;
import javax.inject.Singleton;


@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class})
public interface ApplicationComponent {
  BaseComponent plus(BaseModule baseModule);
  void inject(GlobleApplication globleApplication);
}
