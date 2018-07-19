package com.beadwallet.data.cache.impl;

import android.text.TextUtils;

import com.beadwallet.data.cache.UserCache;
import com.beadwallet.domain.Serializer;
import com.beadwallet.domain.entity.UserEntity;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * @author wanggeng
 * @desc 类描述
 * @date 创建时间：2017/11/20
 */

@Singleton
public class UserCacheImp extends UserCache {

  @Inject
  Serializer mSerializer;
  private UserEntity mUserEntity;

  @Inject
  public UserCacheImp() {
  }

  @Override
  public UserEntity getUserEntity() {
    String cache = get();
    if (TextUtils.isEmpty(cache)) {
      return null;
    }else if (mUserEntity == null || isFirst) {
      isFirst = false;
      mUserEntity = mSerializer.deserialize(cache, UserEntity.class);
    }
    return mUserEntity;
  }
}
