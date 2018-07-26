package com.beadwallet.beautymirror.thread;

import com.beadwallet.domain.executor.IExecutionThread;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;



public class IOThread implements IExecutionThread {

  @Inject
  public IOThread() {
  }

  @Override
  public Scheduler getScheduler() {
    return Schedulers.io();
  }
}
