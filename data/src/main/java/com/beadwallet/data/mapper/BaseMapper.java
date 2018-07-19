package com.beadwallet.data.mapper;

import com.beadwallet.domain.util.ObjectMapperUtil;
import javax.inject.Inject;

public class BaseMapper<From, Into> {
  @Inject
  ObjectMapperUtil mObjectMapperUtil;

  public Into transform(From from, Class<Into> to){
    return mObjectMapperUtil.map(from, to);
  }
}
