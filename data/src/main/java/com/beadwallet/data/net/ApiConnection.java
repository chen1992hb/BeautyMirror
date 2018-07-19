package com.beadwallet.data.net;

import android.content.Context;

import com.beadwallet.data.BuildConfig;
import com.beadwallet.data.cache.BaseUrlCache;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wanggeng
 * @desc 类描述
 * @date 创建时间：2017/11/9
 */

@Singleton
public class ApiConnection {

  BaseUrlCache mBaseUrlCache;
  Context      mContext;

  private static final short DEFAULT_CONNECTION_TIMEOUT = 10;
  public static final String REQUEST = "Request";
  public static final String RESPONSE = "Response";
  public static final String VERSION = "version";
  private Retrofit mRetrofit;

  @Inject
  public ApiConnection(Context context, BaseUrlCache baseUrlCache) {
    mContext = context;
    mBaseUrlCache = baseUrlCache;
    buildRetrofit();
  }

  public void buildRetrofit() {
    mRetrofit = new Builder()
        .baseUrl(mBaseUrlCache.get())
        .client(getOkhttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
  }

  private OkHttpClient getOkhttpClient() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.dns(OkHttpDns.getInstance(mContext));
    builder.connectTimeout(DEFAULT_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
    builder.writeTimeout(DEFAULT_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
    builder.addInterceptor(new HeaderInterceptor());
    if (BuildConfig.DEBUG) {
      builder.addInterceptor(new LoggingInterceptor.Builder()
          .loggable(BuildConfig.DEBUG)
          .setLevel(Level.BASIC)
          .log(Platform.INFO)
          .request(REQUEST)
          .response(RESPONSE)
          .addHeader(VERSION, BuildConfig.VERSION_NAME)
          .build());
      builder.addNetworkInterceptor(new StethoInterceptor());
      builder.sslSocketFactory(createSSLSocketFactory());
      builder.hostnameVerifier(new TrustAllHostnameVerifier());
    }
    return builder.build();
  }

  public <T> T createService(Class<T> serviceClass) {
    return mRetrofit.create(serviceClass);
  }

  private static class TrustAllCerts implements X509TrustManager {

    @Override
    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
        throws java.security.cert.CertificateException {

    }

    @Override
    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
        throws java.security.cert.CertificateException {

    }

    @Override
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
      return new java.security.cert.X509Certificate[0];
    }
  }

  private static class TrustAllHostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {
      return true;
    }
  }

  private static SSLSocketFactory createSSLSocketFactory() {
    SSLSocketFactory ssfFactory = null;

    try {
      SSLContext sc = SSLContext.getInstance("TLS");
      sc.init(null,  new TrustManager[] { new TrustAllCerts() }, new SecureRandom());

      ssfFactory = sc.getSocketFactory();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return ssfFactory;
  }

}
