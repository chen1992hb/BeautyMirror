package com.beadwallet.beautymirror.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.appcompat.BuildConfig;

public class PermissionManagementUtil {

  /**
   * Build.MANUFACTURER
   */
  private static final String MANUFACTURER_HUAWEI  = "huawei";//华为
  private static final String MANUFACTURER_MEIZU   = "meizu";//魅族
  private static final String MANUFACTURER_XIAOMI  = "xiaomi";//小米
  private static final String MANUFACTURER_SONY    = "sony";//索尼
  private static final String MANUFACTURER_OPPO    = "oppo";
  private static final String MANUFACTURER_LG      = "lg";
  private static final String MANUFACTURER_VIVO    = "vivo";
  private static final String MANUFACTURER_SAMSUNG = "samsung";//三星
  private static final String MANUFACTURER_LETV    = "letv";//乐视
  private static final String MANUFACTURER_ZTE     = "zte";//中兴
  private static final String MANUFACTURER_YULONG  = "yulong";//酷派
  private static final String MANUFACTURER_LENOVO  = "lenovo";//联想

  /**
   * 此函数可以自己定义
   */
  public static void GoToSetting(Activity activity) {
    try {
      switch (Build.MANUFACTURER.toLowerCase()) {
        case MANUFACTURER_HUAWEI:
          Huawei(activity);
          break;
        case MANUFACTURER_MEIZU:
          Meizu(activity);
          break;
        case MANUFACTURER_XIAOMI:
          Xiaomi(activity);
          break;
        case MANUFACTURER_SONY:
          Sony(activity);
          break;
        case MANUFACTURER_OPPO:
          OPPO(activity);
          break;
        case MANUFACTURER_LG:
          LG(activity);
          break;
        case MANUFACTURER_LETV:
          Letv(activity);
          break;
        default:
          ApplicationInfo(activity);
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
      ApplicationInfo(activity);
    }
  }

  public static void Huawei(Activity activity) {
    Intent intent = new Intent();
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
    ComponentName comp = new ComponentName("com.huawei.systemmanager",
        "com.huawei.permissionmanager.ui.MainActivity");
    intent.setComponent(comp);
    activity.startActivity(intent);
  }

  public static void Meizu(Activity activity) {
    Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
    intent.addCategory(Intent.CATEGORY_DEFAULT);
    intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
    activity.startActivity(intent);
  }

  public static void Xiaomi(Activity activity) {
    Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
    ComponentName componentName = new ComponentName("com.miui.securitycenter",
        "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
    intent.setComponent(componentName);
    intent.putExtra("extra_pkgname", BuildConfig.APPLICATION_ID);
    activity.startActivity(intent);
  }

  public static void Sony(Activity activity) {
    Intent intent = new Intent();
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
    ComponentName comp = new ComponentName("com.sonymobile.cta",
        "com.sonymobile.cta.SomcCTAMainActivity");
    intent.setComponent(comp);
    activity.startActivity(intent);
  }

  public static void OPPO(Activity activity) {
    Intent intent = new Intent();
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
    ComponentName comp = new ComponentName("com.color.safecenter",
        "com.color.safecenter.permission.PermissionManagerActivity");
    intent.setComponent(comp);
    activity.startActivity(intent);
  }

  public static void LG(Activity activity) {
    Intent intent = new Intent("android.intent.action.MAIN");
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
    ComponentName comp = new ComponentName("com.android.settings",
        "com.android.settings.Settings$AccessLockSummaryActivity");
    intent.setComponent(comp);
    activity.startActivity(intent);
  }

  public static void Letv(Activity activity) {
    Intent intent = new Intent();
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
    ComponentName comp = new ComponentName("com.letv.android.letvsafe",
        "com.letv.android.letvsafe.PermissionAndApps");
    intent.setComponent(comp);
    activity.startActivity(intent);
  }

  /**
   * 只能打开到自带安全软件
   */
  public static void _360(Activity activity) {
    Intent intent = new Intent("android.intent.action.MAIN");
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
    ComponentName comp = new ComponentName("com.qihoo360.mobilesafe",
        "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
    intent.setComponent(comp);
    activity.startActivity(intent);
  }

  public static void Smartisan(Activity activity) {
    Intent intent = new Intent();
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
    ComponentName comp = new ComponentName("com.smartisanos.security",
        "com.smartisanos.security.MainActivity");
    intent.setComponent(comp);
    activity.startActivity(intent);
  }

  /**
   * 应用信息界面
   */
  public static void ApplicationInfo(Activity activity) {
    Intent localIntent = new Intent();
    localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    if (Build.VERSION.SDK_INT >= 9) {
      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
    } else if (Build.VERSION.SDK_INT <= 8) {
      localIntent.setAction(Intent.ACTION_VIEW);
      localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
      localIntent.putExtra("com.android.settings.ApplicationPkgName", activity.getPackageName());
    }
    activity.startActivity(localIntent);
  }

  /**
   * 系统设置界面
   */
  public static void SystemConfig(Activity activity) {
    Intent intent = new Intent(Settings.ACTION_SETTINGS);
    activity.startActivity(intent);
  }
}