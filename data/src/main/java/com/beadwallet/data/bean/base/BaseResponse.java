package com.beadwallet.data.bean.base;


public class BaseResponse {

  public static final String STATUS_OK = "200";

  private  String appKey;
  private String signature;
  private String timestamp;
  private  String sts;
  private  String model;
  private  String rmk;
  private  String appVersion;

  public static String getStatusOk() {
    return STATUS_OK;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getSts() {
    return sts;
  }

  public void setSts(String sts) {
    this.sts = sts;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getRmk() {
    return rmk;
  }

  public void setRmk(String rmk) {
    this.rmk = rmk;
  }

  public String getAppVersion() {
    return appVersion;
  }

  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }
}
