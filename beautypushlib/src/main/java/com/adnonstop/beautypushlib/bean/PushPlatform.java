package com.adnonstop.beautypushlib.bean;

/**
 * @Author: ZhangJF
 * @Date: 2022/3/31
 * @Describe:
 */
public class PushPlatform {
    private String platformName;//平台名
    private String regId;//成功注册推送服务的注册ID

    public PushPlatform(String platformName, String regId) {
        this.platformName = platformName;
        this.regId = regId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getRegId() {
        return regId;
    }

    @Override
    public String toString() {
        return "PushPlatform{" +
                "platformName='" + platformName + '\'' +
                ", regId='" + regId + '\'' +
                '}';
    }
}
