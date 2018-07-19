package com.beadwallet.data.cache;

import com.beadwallet.data.cache.dao.ICache;
import com.beadwallet.data.util.SharedPreferenceUtil;

import javax.inject.Inject;


/**
 * @author wanggeng
 * @desc 类描述
 * @date 创建时间：2018/1/15
 */

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
