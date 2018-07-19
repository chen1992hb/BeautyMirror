package com.beadwallet.domain.util;

import com.beadwallet.domain.Serializer;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * @author wanggeng
 * @desc 类描述
 * @date 创建时间：2017/11/14
 */

@Singleton
public class ObjectMapperUtil {

  @Inject
  Serializer mSerializer;

  @Inject
  public ObjectMapperUtil() {
  }

  /**
   * 相同属性映射
   *
   * @param source 源对象
   * @param classObject 目标对象对应的class
   */
  public <T> T map(Object source, Class<T> classObject) {
    return mSerializer.deserialize(mSerializer.serialize(source), classObject);
  }
}


