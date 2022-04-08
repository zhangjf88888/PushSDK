package com.adnonstop.beautypushlib.mipush;

import android.content.Context;

import com.adnonstop.beautypushlib.base.BasePushProvider;
import com.adnonstop.beautypushlib.base.PlatformType;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

/**
 * @Author: ZhangJF
 * @Date: 2022/3/31
 * @Describe:
 */
public class MiPushProvider extends BasePushProvider {
    public static String TAG = "MiPushProvider";

    @Override
    public void register(Context context) {
        String appId = getMetaData(context, "MI_APP_ID");
        String appKey = getMetaData(context, "MI_APP_KEY");
        LoggerInterface newLogger = new LoggerInterface() {
            @Override
            public void setTag(String tag) {
                // ignore
            }

            @Override
            public void log(String content, Throwable throwable) {

            }

            @Override
            public void log(String content) {

            }
        };
        Logger.setLogger(context, newLogger);
        MiPushClient.registerPush(context.getApplicationContext(), appId, appKey);

    }

    @Override
    public void unRegister(Context context) {
        MiPushClient.unregisterPush(context.getApplicationContext());
    }

    @Override
    public String getRegisterId(Context context) {
        return MiPushClient.getRegId(context);
    }

    @Override
    public boolean isSupport(Context context) {
        //
        return true;
    }

    @Override
    public String getPlatformName() {
        return PlatformType.mi.getValue();
    }
}
