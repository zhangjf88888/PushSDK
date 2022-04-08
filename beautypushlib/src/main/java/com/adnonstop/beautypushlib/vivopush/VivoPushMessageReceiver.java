package com.adnonstop.beautypushlib.vivopush;

import android.content.Context;

import com.adnonstop.beautypushlib.BeautyPushManager;
import com.adnonstop.beautypushlib.PushHandler;
import com.adnonstop.beautypushlib.base.PlatformType;
import com.adnonstop.beautypushlib.bean.PushPlatform;
import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.sdk.OpenClientPushMessageReceiver;

/**
 * @Author: ZhangJF
 * @Date: 2022/3/31
 * @Describe:
 */
public class VivoPushMessageReceiver extends OpenClientPushMessageReceiver {


    @Override
    public void onNotificationMessageClicked(Context context, UPSNotificationMessage message) {
        //该接口仅用于解决v3.0.0.0_480之前的版本升级Push SDK过程中发送的老版本打开自定义通知（skiptype=3）需要依赖点击回调完成跳转时使用， 新版本通知点击该点击回调是不可用的。

    }


    @Override
    public void onReceiveRegId(Context context, String regId) {
        //RegId结果返回，只有首次获取到或regId发生变化时才会回调；
        PushPlatform mixPushPlatform = new PushPlatform(PlatformType.vivo.getValue(), regId);
        BeautyPushManager.getInstance().getHandler().getPushReceiver().onRegisterSucceed(mixPushPlatform);
    }
}
