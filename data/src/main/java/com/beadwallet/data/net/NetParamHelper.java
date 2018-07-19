package com.beadwallet.data.net;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author wanggeng
 * @desc 类描述
 * @date 创建时间：2017/6/8
 */

public class NetParamHelper {

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
}
