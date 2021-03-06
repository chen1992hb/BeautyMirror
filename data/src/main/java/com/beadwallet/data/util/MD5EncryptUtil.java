package com.beadwallet.data.util;

import java.security.MessageDigest;

/**
 * MD5加密
 *
 * @author zhouying 2014-04-18
 */
public class MD5EncryptUtil {
  private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
      "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

  public MD5EncryptUtil() {
  }

  /**
   * byte[]类型转String类型
   */
  public static String byteArrayToString(byte[] b) {
    StringBuffer resultSb = new StringBuffer();
    for (int i = 0; i < b.length; i++) {
      resultSb.append(byteToNumString(b[i]));
    }
    return resultSb.toString();
  }

  /**
   * byte类型转String具体方法
   */
  private static String byteToNumString(byte b) {
    int _b = b;
    if (_b < 0) {
      _b = 256 + _b;
    }
    return String.valueOf(_b);
  }

  /**
   * 加密转化
   */
  private static String byteToHexString(byte b) {
    int n = b;
    if (n < 0) {
      n = 256 + n;
    }
    int d1 = n / 16;
    int d2 = n % 16;
    return hexDigits[d1] + hexDigits[d2];
  }

  /**
   * 将字符串MD5加密
   */
  public static String MD5Encode(String origin) {
    String resultString = null;
    try {
      resultString = new String(origin);
      MessageDigest md = MessageDigest.getInstance("MD5");
      resultString = byteArrayToString(md.digest(resultString.getBytes()));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return resultString;
  }

  /**
   * 测试方法
   */
  public static void main(String[] args) {
    System.out.println(MD5EncryptUtil.MD5Encode("123456"));
  }
}
