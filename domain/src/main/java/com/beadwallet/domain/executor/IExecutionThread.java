package com.beadwallet.domain.executor;

import io.reactivex.Scheduler;


public interface IExecutionThread {
  Scheduler getScheduler();
}
