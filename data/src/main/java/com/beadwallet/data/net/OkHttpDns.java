package com.beadwallet.data.net;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.alibaba.sdk.android.httpdns.DegradationFilter;
import com.alibaba.sdk.android.httpdns.HttpDns;
import com.alibaba.sdk.android.httpdns.HttpDnsService;
import com.beadwallet.data.BuildConfig;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okhttp3.Dns;

public class OkHttpDns implements Dns {

  HttpDnsService httpdns;//httpdns 解析服务
  public static final String ACCOUNT_ID = "136558";
  public static final String SECRET_KEY = "224c1e9cef3365f4ff441a6b4ef88129";
  private static OkHttpDns   instance   = null;
  private Context mContext;

  private OkHttpDns(Context context) {
    mContext = context;
  }

  public static OkHttpDns getInstance(Context context) {
    if (instance == null) {
      instance = new OkHttpDns(context);
    }
    return instance;
  }

  public HttpDnsService getHttpdns(){
    return httpdns;
  }

  @Override
  public List<InetAddress> lookup(String hostname) throws UnknownHostException {
    //通过异步解析接口获取ip
    String ip = httpdns.getIpByHostAsync(hostname);
    if (ip != null) {
      //如果ip不为null，直接使用该ip进行网络请求
      List<InetAddress> inetAddresses = Arrays.asList(InetAddress.getAllByName(ip));
      Log.e("OkHttpDns", "inetAddresses:" + inetAddresses);
      return inetAddresses;
    }
    //如果返回null，走系统DNS服务解析域名
    return Dns.SYSTEM.lookup(hostname);
  }

  public void init() {
    httpdns = HttpDns.getService(mContext, ACCOUNT_ID, SECRET_KEY);
    // DegradationFilter用于自定义降级逻辑
    // 通过实现shouldDegradeHttpDNS方法，可以根据需要，选择是否降级
    DegradationFilter filter = new DegradationFilter() {
      @Override
      public boolean shouldDegradeHttpDNS(String hostName) {
        // 此处可以自定义降级逻辑，例如www.taobao.com不使用HttpDNS解析
        // 参照HttpDNS API文档，当存在中间HTTP代理时，应选择降级，使用Local DNS
//        return detectIfProxyExist(mContext);
        return false;
      }
    };
    // 将filter传进httpdns，解析时会回调shouldDegradeHttpDNS方法，判断是否降级
    httpdns.setDegradationFilter(filter);
    httpdns.setLogEnabled(BuildConfig.DEBUG);
    httpdns.setHTTPSRequestEnabled(true);
    // 设置预解析域名列表，真正使用时，建议您将预解析操作放在APP启动函数中执行。预解析操作为异步行为，不会阻塞您的启动流程
    ArrayList<String> hostList = new ArrayList<>(Arrays.asList(ApiUrl.API_BASE_URL.replace("https://","")));
    httpdns.setPreResolveHosts(hostList);
    // 允许返回过期的IP，通过设置允许返回过期的IP，配合异步查询接口，我们可以实现DNS懒更新策略
    httpdns.setExpiredIPEnabled(true);
  }

  /**
   * 检测系统是否已经设置代理，请参考HttpDNS API文档。
   */
  private static boolean detectIfProxyExist(Context ctx) {
    boolean IS_ICS_OR_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    String proxyHost;
    int proxyPort;
    if (IS_ICS_OR_LATER) {
      proxyHost = System.getProperty("http.proxyHost");
      String port = System.getProperty("http.proxyPort");
      proxyPort = Integer.parseInt(port != null ? port : "-1");
    } else {
      proxyHost = android.net.Proxy.getHost(ctx);
      proxyPort = android.net.Proxy.getPort(ctx);
    }
    return proxyHost != null && proxyPort != -1;
  }
}