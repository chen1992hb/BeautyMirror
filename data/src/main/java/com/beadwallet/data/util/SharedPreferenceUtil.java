package com.beadwallet.data.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPreferenceUtil {

  protected Context                  mContext;
  private   SharedPreferences        mSharedPreferences;
  private   SharedPreferences.Editor mSharedPreferencesEditor;

  @Inject
  public SharedPreferenceUtil(Context context) {
    mContext = context;
    mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    mSharedPreferencesEditor = mSharedPreferences.edit();
  }

  /**
   * Stores String value in preference
   *
   * @param key key of preference
   * @param value value for that key
   */
  public void setValue(String key, String value) {
    mSharedPreferencesEditor.putString(key, value);
    mSharedPreferencesEditor.apply();
  }

  /**
   * Stores int value in preference
   *
   * @param key key of preference
   * @param value value for that key
   */
  public void setValue(String key, int value) {
    mSharedPreferencesEditor.putInt(key, value);
    mSharedPreferencesEditor.apply();
  }

  /**
   * Stores Double value in String format in preference
   *
   * @param key key of preference
   * @param value value for that key
   */
  public void setValue(String key, double value) {
    setValue(key, Double.toString(value));
  }

  /**
   * Stores long value in preference
   *
   * @param key key of preference
   * @param value value for that key
   */
  public void setValue(String key, long value) {
    mSharedPreferencesEditor.putLong(key, value);
    mSharedPreferencesEditor.apply();
  }

  /**
   * Stores boolean value in preference
   *
   * @param key key of preference
   * @param value value for that key
   */
  public void setValue(String key, boolean value) {
    mSharedPreferencesEditor.putBoolean(key, value);
    mSharedPreferencesEditor.apply();
  }

  /**
   * Retrieves String value from preference
   *
   * @param key key of preference
   * @param defaultValue default value if no key found
   */
  public String getStringValue(String key, String defaultValue) {
    return mSharedPreferences.getString(key, defaultValue);
  }

  public String getStringValue(String key) {
    return getStringValue(key, "");
  }

  /**
   * Retrieves int value from preference
   *
   * @param key key of preference
   * @param defaultValue default value if no key found
   */
  public int getIntValue(String key, int defaultValue) {
    return mSharedPreferences.getInt(key, defaultValue);
  }

  /**
   * Retrieves long value from preference
   *
   * @param key key of preference
   * @param defaultValue default value if no key found
   */
  public long getLongValue(String key, long defaultValue) {
    return mSharedPreferences.getLong(key, defaultValue);
  }

  /**
   * Retrieves boolean value from preference
   *
   * @param keyFlag key of preference
   * @param defaultValue default value if no key found
   */
  public boolean getBoolanValue(String keyFlag, boolean defaultValue) {
    return mSharedPreferences.getBoolean(keyFlag, defaultValue);
  }

  /**
   * Removes key from preference
   *
   * @param key key of preference that is to be deleted
   */
  public void removeKey(String key) {
    if (mSharedPreferencesEditor != null) {
      mSharedPreferencesEditor.remove(key);
      mSharedPreferencesEditor.apply();
    }
  }


  /**
   * Clears all the preferences stored
   */
  public void clear() {
    mSharedPreferencesEditor.clear().apply();
  }
}