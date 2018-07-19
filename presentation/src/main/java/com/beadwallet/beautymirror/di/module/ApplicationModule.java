package com.beadwallet.beautymirror.di.module;

import android.content.Context;

import com.beadwallet.beautymirror.thread.IOThread;
import com.beadwallet.beautymirror.thread.UIThread;
import com.beadwallet.domain.executor.IExecutionThread;
import com.beadwallet.domain.executor.ILoadingThread;
import com.beadwallet.domain.executor.IPostExecutionThread;

import javax.inject.Singleton;


import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

  private Context mContext;

  public ApplicationModule(Context context) {
    mContext = context;
  }

  @Singleton
  @Provides
  public Context provideContext() {
    return mContext;
  }

  @Singleton
  @Provides
  public IExecutionThread provideExecutionThread(IOThread ioThread) {
    return ioThread;
  }

  @Singleton
  @Provides
  public IPostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Singleton
  @Provides
  public ILoadingThread provideLoadingThread(UIThread uiThread) {
    return uiThread;
  }

}
