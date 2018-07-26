package com.beadwallet.data.cache;

import com.beadwallet.data.cache.dao.ICache;
import com.beadwallet.data.util.SharedPreferenceUtil;

import javax.inject.Inject;



public abstract class CommonCache<T> implements ICache<T> {

  @Inject
  protected SharedPreferenceUtil mSharedPreferenceUtil;

  @Override
  public String cacheName() {
    return getClass().getSimpleName();
  }

  @Override
  public void clearCache() {
    mSharedPreferenceUtil.removeKey(cacheName());
  }
}
