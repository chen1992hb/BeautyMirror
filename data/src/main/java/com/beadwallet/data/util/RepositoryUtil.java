package com.beadwallet.data.util;

import com.beadwallet.data.bean.base.BaseResponse;
import com.beadwallet.domain.util.ObjectMapperUtil;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

@Singleton
public class RepositoryUtil {
  @Inject
  ObjectMapperUtil mObjectMapperUtil;

  @Inject
  public RepositoryUtil() {
  }

  public <T> Observable<T> extractData(Observable<? extends BaseResponse> observable,
      final Class<T> clazz) {
    return observable.flatMap(new Function<BaseResponse, ObservableSource<T>>() {
      @Override
      public ObservableSource<T> apply(BaseResponse baseResponse) throws Exception {


          return Observable.just(mObjectMapperUtil.map(baseResponse, clazz));

      }
    });
  }
}
