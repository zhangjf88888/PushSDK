package com.adnonstop.beautypushlib.vivopush;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.adnonstop.beautypushlib.BeautyPushManager;
import com.adnonstop.beautypushlib.PushHandler;
import com.adnonstop.beautypushlib.base.BasePushProvider;
import com.adnonstop.beautypushlib.base.PlatformType;
import com.adnonstop.beautypushlib.bean.PushPlatform;
import com.vivo.push.IPushActionListener;
import com.vivo.push.PushClient;
import com.vivo.push.util.VivoPushException;

/**
 * @Author: ZhangJF
 * @Date: 2022/3/31
 * @Describe: vivo推送SDK接入，详见：https://dev.vivo.com.cn/documentCenter/doc/365
 */
public class VivoPushProvider extends BasePushProvider {


    @Override
    public void register(Context context) {
        try {
            //初始化push
            PushClient.getInstance(context.getApplicationContext()).initialize();
            // 打开push开关, 关闭为turnOffPush，详见api接入文档
            PushClient.getInstance(context.getApplicationContext()).turnOnPush(state -> {
                // 开关状态处理， 0代表成功
                Log.e("TAG", "register: state ======= " + state);
                if (state == 0) {
                    String regId = PushClient.getInstance(context).getRegId();
                    if (regId != null) {
                        PushPlatform pushPlatform = new PushPlatform(PlatformType.vivo.getValue(), regId);
                        BeautyPushManager.getInstance().getHandler().getPushReceiver().onRegisterSucceed(pushPlatform);
                    }
                }
            });
        } catch (VivoPushException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unRegister(Context context) {
        PushClient.getInstance(context).turnOffPush(new IPushActionListener() {
            @Override
            public void onStateChanged(int state) {
                // 开关状态处理， 0代表成功

            }
        });
    }

    @Override
    public String getRegisterId(Context context) {
        return PushClient.getInstance(context).getRegId();
    }

    @Override
    public boolean isSupport(Context context) {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            return false;
        }
        String brand = Build.BRAND.toLowerCase();
        String manufacturer = Build.MANUFACTURER.toLowerCase();
        if (manufacturer.equals("vivo") || brand.contains("vivo") || brand.contains("iqoo")) {
            return PushClient.getInstance(context).isSupport();
        }
        return false;
    }

    @Override
    public String getPlatformName() {
        return PlatformType.vivo.getValue();
    }
}
