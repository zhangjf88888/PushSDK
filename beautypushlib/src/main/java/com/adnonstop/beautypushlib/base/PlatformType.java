package com.adnonstop.beautypushlib.base;

/**
 * @Author: ZhangJF
 * @Date: 2022/3/31
 * @Describe: 推送平台
 */
public enum PlatformType {
    mi("xiaomi"),
    oppo("oppo"),
    vivo("vivo"),
    huawei("huawei");

    private String value;

    PlatformType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
