package com.beadwallet.domain.entity;

public class DemoEntity {

    private  String appKey;
    private String signature;
    private String timestamp;
    private  String sts;
    private  String model;
    private  String rmk;
    private  String appVersion;
public static  class data{
private  String packageName;
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

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

    @Override
    public String toString() {
        return "{" +
                "appKey='" + appKey + '\'' +
                ",signature='" + signature + '\'' +
                ",timestamp='" + timestamp + '\'' +
                ",sts='" + sts + '\'' +
                ",model='" + model + '\'' +
                ",rmk='" + rmk + '\'' +
                ",appVersion='" + appVersion + '\'' +
                '}';
    }
}
