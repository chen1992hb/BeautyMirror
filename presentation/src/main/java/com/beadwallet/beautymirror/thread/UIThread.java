package com.beadwallet.beautymirror.thread;

import com.beadwallet.domain.executor.ILoadingThread;
import com.beadwallet.domain.executor.IPostExecutionThread;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;



public class UIThread implements IPostExecutionThread, ILoadingThread {

  @Inject
  public UIThread() {
  }

  @Override
  public Scheduler getScheduler() {
    return AndroidSchedulers.mainThread();
  }
}
