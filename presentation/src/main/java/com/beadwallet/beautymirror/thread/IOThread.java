package com.beadwallet.beautymirror.thread;

import com.beadwallet.domain.executor.IExecutionThread;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wanggeng
 * @desc 类描述
 * @date 创建时间：2017/11/14
 */

public class IOThread implements IExecutionThread {

  @Inject
  public IOThread() {
  }

  @Override
  public Scheduler getScheduler() {
    return Schedulers.io();
  }
}
