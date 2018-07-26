package com.beadwallet.data.cache;

import com.beadwallet.data.cache.dao.ICache;
import com.beadwallet.data.util.SharedPreferenceUtil;
import com.beadwallet.domain.entity.UserEntity;

import javax.inject.Inject;




public abstract class UserCache implements ICache<String> {

  protected boolean isFirst;

  @Inject
  SharedPreferenceUtil mSharedPreferenceUtil;

  @Override
  public String cacheName() {
    return this.getClass().getSimpleName();
  }

  @Override
  public void put(String put) {
    isFirst = true;
    mSharedPreferenceUtil.setValue(cacheName(), put);
  }

  @Override
  public String get() {
    return mSharedPreferenceUtil.getStringValue(cacheName());
  }

  public abstract UserEntity getUserEntity();

  @Override
  public void clearCache() {
    mSharedPreferenceUtil.removeKey(cacheName());
  }
}
