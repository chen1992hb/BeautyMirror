package com.beadwallet.beautymirror.feature.base;

import com.beadwallet.beautymirror.exception.ErrorMessageFactory;
import com.beadwallet.domain.interactor.LoadingObserver;


public abstract class CommonLoadingObserver<T> extends LoadingObserver<T> {
  @Override
  public void onError(Throwable exception) {
    super.onError(exception);
    onErrorMessage(ErrorMessageFactory.create((Exception) exception));
  }

  public abstract void onErrorMessage(String msg);
}
