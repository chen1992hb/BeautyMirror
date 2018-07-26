package com.beadwallet.data.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;


public class RequestParamUtil {
  /**
   * 固定机构号，部分接口签名用
   */
  public static final String INST_NUMBER = "52100001 ";

  public static final String INST_NUMBER_KEY = "8b6ab12dc49d49598d4cdd4c2e79d6c2";

  public static String getTerminalTrace(){
    return UUID.randomUUID().toString().replace("-", "");
  }

  public static String getCurrTerminaltime() {
    return getRandomUUID();
  }

  public static String getRandomUUID() {
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
    String t = format.format(new Date());
    return t;
  }

  public static String fen2Yuan(int fen){
    Double double1 = Double.parseDouble((fen + ""));
    Double double2 = double1 / 100;
    return String.format("%.2f", double2);
  }

  public static String Yuan2FenString(String yuan){
    Double double1 = Double.parseDouble(yuan);
    Double double2 = double1 * 100;
    String fen = String.valueOf(double2.intValue());
    return fen;
  }

  /*
   * 获取应用名
   */
  public static String getVersionName(Context context) {
    String versionName = null;
    try {
      //获取包管理者
      PackageManager pm = context.getPackageManager();
      //获取packageInfo
      PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
      //获取versionName
      versionName = info.versionName;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return versionName;
  }

  /**
   *
   * @param object
   * @return
   */
  public static String alphabetOrderSignViaInstNumber(Object object){
    TreeMap<String, Object> map = transBean2Map(object);
    StringBuffer stringBuffer = new StringBuffer();
    Set<String> strings = map.keySet();
    Iterator<String> iterator = strings.iterator();
    while (iterator.hasNext()) {
      String key = iterator.next();
      Object value = map.get(key);
      if (value instanceof String && TextUtils.isEmpty((String) value)) {
        continue;
      }
      stringBuffer.append(key + "=" + value + "&");
    }
    String keySign = stringBuffer.deleteCharAt(stringBuffer.length() - 1).toString() + "&key="
        + INST_NUMBER_KEY;
    return MD5.MD5Encode(keySign);
  }

  public static String alphabetOrderSign(Object object, String accessToken){
    TreeMap<String, Object> map = transBean2Map(object);
    StringBuffer stringBuffer = new StringBuffer();
    Set<String> strings = map.keySet();
    Iterator<String> iterator = strings.iterator();
    while (iterator.hasNext()) {
      String key = iterator.next();
      Object value = map.get(key);
      if (value instanceof String && TextUtils.isEmpty((String) value)) {
        continue;
      }
      stringBuffer.append(key + "=" + value + "&");
    }
    String keySign = stringBuffer.deleteCharAt(stringBuffer.length() - 1).toString() + "&access_token="
        + accessToken;
    return MD5.MD5Encode(keySign);
  }

  public static TreeMap transBean2Map(Object obj) {
    TreeMap<String, Object> map = null;
    ArrayList<Class> clazzs = new ArrayList<>();
    try {
      map = new TreeMap<>();
      Class<?> clazz = obj.getClass();
      while (!clazz.equals(Object.class)){ //过滤掉Android系统可能为object添加的属性
        clazzs.add(clazz);
        clazz = clazz.getSuperclass();
      }
      for (Class c : clazzs) {
        Field[] declaredFields = c.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
          Field field = declaredFields[i];
          if("serialVersionUID".equals(field.getName())){ //AS 自动生成的字段 此处过滤
            continue;
          }
          if(java.lang.reflect.Modifier.isStatic(field.getModifiers())){ //过滤掉static变量
            continue;
          }
          if("key_sign".equals(field.getName())){ //过滤key_sign
            continue;
          }
          field.setAccessible(true);
          Object value = field.get(obj);
          map.put(field.getName(), value);
        }
      }
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } finally {
      return map;
    }
  }

  /**
   * 传JavaBean 和签名用的key ,有值参数进行字典排序
   * 只过滤null （仅限于外部使用。支付接口）
   * 比FilterNullSign(Object object, String key, String Filter) 兼容性更广
   * @param object
   * @param keyname
   * @param keyvalue
   * @param Filter
   * @return
   */
  public static String FilterNullSign(Object object, String keyname,String keyvalue, String Filter) {
    Sign signContent = new Sign();
    String sign = null;
    ArrayList<Class> clazzs = new ArrayList();
    try {
      Class<?> clazz = object.getClass();
      while(!clazz.equals(Object.class)){
        clazzs.add(clazz);
        clazz = clazz.getSuperclass();
      }
      for (Class c : clazzs) {
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
          Field f = fields[i];
          String name = f.getName();

          if("serialVersionUID".equals(name)){ //AS 自动生成的字段 此处过滤
            continue;
          }
          // 私有变量必须先设置Accessible为true
          f.setAccessible(true);
          Object valueObject = f.get(object);
          // System.out.println(valueObject);
          if (valueObject == null) {
            continue;
          }

          if (Filter != null && !Filter.equals("") && Filter.equals(name)) {
            continue;
          }
          String value = valueObject.toString();

          if (value != null) {
            signContent.putParam(name, value);
          }
        }
      }
      signContent.putLastParam(keyname, keyvalue);
      String parm = signContent.getSignStr();
      sign = MD5.MD5Encode(parm);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return sign;
  }

  public static class Sign{
    private Map<String,String> map;
    private String lastStr = "";

    private Sign() {
      map=new HashMap<>();
    }

    public Sign putParam(String key,String value){
      map.put(key,value);
      return this;
    }

    //只过滤null
    public Sign putParamFilterNull(String key,String value){
      if(value!=null){
        map.put(key,value);
      }
      return this;
    }

    public Sign putLastParam(String key,String value){
      lastStr="&"+key+"="+value;
      return this;
    }

    public String getParam(String key){
      return map.get(key);
    }

    /**
     * 获取签名字符串
     * @return
     */
    public String getSignStr(){
      List<String> keys = new ArrayList<String>(map.keySet());
      Collections.sort(keys);
      String prestr = "";
      for (int i = 0; i < keys.size(); i++) {
        String key = keys.get(i);
        String value = map.get(key);
        if (i == keys.size() - 1) {
          prestr = prestr + key + "=" + value;
        } else {
          prestr = prestr + key + "=" + value + "&";
        }
      }
      return prestr+lastStr;
    }

    /**
     * 获取签名
     * @return
     */
    public String getSign(){
      return MD5.MD5Encode(getSignStr());
    }
  }
}
