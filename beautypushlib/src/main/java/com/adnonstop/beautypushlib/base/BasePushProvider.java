package com.adnonstop.beautypushlib.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * @Author: ZhangJF
 * @Date: 2022/3/31
 * @Describe:
 */
public abstract class BasePushProvider {
    public abstract void register(Context context);

    public abstract void unRegister(Context context);

    public abstract String getRegisterId(Context context);

    /**
     * @return 推送SDK是否支持当前设备
     */
    public abstract boolean isSupport(Context context);


    protected String getMetaData(Context context, String name) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Object value = appInfo.metaData.getString(name);
            if (value != null) {
                return value.toString();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract String getPlatformName();
}
