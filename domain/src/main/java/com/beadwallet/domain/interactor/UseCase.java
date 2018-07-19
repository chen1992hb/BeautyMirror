package com.beadwallet.domain.interactor;


import com.beadwallet.domain.executor.IExecutionThread;
import com.beadwallet.domain.executor.ILoadingThread;
import com.beadwallet.domain.executor.IPostExecutionThread;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import javax.inject.Inject;

/**
 * @author wanggeng
 * @desc 类描述
 * @date 创建时间：2017/11/10
 */

public abstract class UseCase<T, Params> {

  @Inject
  ILoadingThread mILoadingThread;

  private IExecutionThread mIExecutionThread;
  private IPostExecutionThread mIPostExecutionThread;
  private CompositeDisposable disposables;

  UseCase(IExecutionThread IExecutionThread, IPostExecutionThread IPostExecutionThread) {
    this.mIExecutionThread = IExecutionThread;
    this.mIPostExecutionThread = IPostExecutionThread;
    this.disposables = new CompositeDisposable();
  }

  abstract Observable<T> buildUseCaseObservable(Params params);

  public void execute(DisposableObserver<T> observer, Params params) {
    final Observable<T> observable = getObservable(params);
    addDisposable(observable.subscribeWith(observer));
  }

  public Observable<T> getObservable(Params params) {
    return this.buildUseCaseObservable(params)
          .subscribeOn(mIExecutionThread.getScheduler())
          .observeOn(mIPostExecutionThread.getScheduler()).onTerminateDetach();
  }

  public void executeWithLoading(LoadingObserver<T> observer, Params params) {
    final Observable<T> observable = getLoadingObservable(observer, params);
    addDisposable(observable.subscribeWith(observer));
  }

  public Observable<T> getLoadingObservable(LoadingObserver<T> observer, Params params) {
    return this.buildUseCaseObservable(params)
          .subscribeOn(mIExecutionThread.getScheduler())
          .doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
              observer.onLoadingStart();
            }
          })
          .subscribeOn(mILoadingThread.getScheduler())
          .doFinally(new Action() {
            @Override
            public void run() throws Exception {
              observer.onLoadingEnd();
            }
          })
          .observeOn(mIPostExecutionThread.getScheduler()).onTerminateDetach();
  }

  /**
   * Dispose from current {@link CompositeDisposable}.
   */
  public void dispose() {
    if (!disposables.isDisposed()) {
      disposables.dispose();
    }
  }

  public void clear(){
    disposables.clear();
  }



  /**
   * Dispose from current {@link CompositeDisposable}.
   */
  public void addDisposable(Disposable disposable) {
    disposables.add(disposable);
  }
}
