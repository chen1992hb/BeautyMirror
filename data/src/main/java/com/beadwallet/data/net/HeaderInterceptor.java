package com.beadwallet.data.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author wanggeng
 * @desc 类描述
 * @date 创建时间：2017/11/9
 */

public class HeaderInterceptor implements Interceptor {

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request()
        .newBuilder()
        .addHeader("Content-Type", "application/json; charset=UTF-8")
        .build();
    return chain.proceed(request);
  }
}
