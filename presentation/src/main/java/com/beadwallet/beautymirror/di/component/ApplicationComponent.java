package com.beadwallet.beautymirror.di.component;


import com.beadwallet.beautymirror.app.GlobleApplication;
import com.beadwallet.beautymirror.di.module.ApplicationModule;
import com.beadwallet.beautymirror.di.module.RepositoryModule;

import dagger.Component;
import javax.inject.Singleton;


@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class})
public interface ApplicationComponent {

  void inject(GlobleApplication globleApplication);
}
