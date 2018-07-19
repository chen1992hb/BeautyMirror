package com.beadwallet.data.cache;

import android.text.TextUtils;

import com.beadwallet.data.net.ApiUrl;

import javax.inject.Inject;
import javax.inject.Singleton;



@Singleton
public class BaseUrlCache extends CommonCache<String> {

  @Inject
  public BaseUrlCache() {

  }

  @Override
  public void put(String put) {
    mSharedPreferenceUtil.setValue(cacheName(), put);
  }

  @Override
  public String get() {
    String stringValue = mSharedPreferenceUtil.getStringValue(cacheName());
    if (stringValue.contains(")")) {
      stringValue = stringValue.substring(stringValue.lastIndexOf(")") + 1);
    }
    return TextUtils.isEmpty(stringValue) ? ApiUrl.API_BASE_URL : stringValue;
  }

  public String getFullText(){
    return mSharedPreferenceUtil.getStringValue(cacheName());
  }
}
