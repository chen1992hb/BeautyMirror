package com.beadwallet.data.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author wanggeng
 * @desc 类描述
 * @date 创建时间：2017/12/29
 */

@Singleton
public class PhoneInfoUtil {

  Context mContext;

  @Inject
  public PhoneInfoUtil(Context context) {
    mContext = context;
  }

  /**
   * 返回手机串号
   */
  @SuppressLint("MissingPermission")
  public String getIMEI() {
    TelephonyManager telephonyManager = (TelephonyManager) mContext
        .getSystemService(Context.TELEPHONY_SERVICE);
    return telephonyManager.getDeviceId();
  }

  public String getDeviceUniqueToken() {
    if (TextUtils.isEmpty(getIMEI())) {
      return Build.SERIAL;
    } else {
      return Build.SERIAL + "_" + getIMEI();
    }
  }
}
