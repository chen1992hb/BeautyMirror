package com.beadwallet.domain.interactor;




public abstract class LoadingObserver<T> extends DefaultObserver<T> {
  public abstract void onLoadingStart();
  public abstract void onLoadingEnd();
}
