package com.adnonstop.beautypushlib.huaweipush;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.adnonstop.beautypushlib.BeautyPushManager;
import com.adnonstop.beautypushlib.PushHandler;
import com.adnonstop.beautypushlib.base.BasePushProvider;
import com.adnonstop.beautypushlib.base.PlatformType;
import com.adnonstop.beautypushlib.bean.PushPlatform;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.AGConnectOptions;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.adapter.internal.AvailableCode;
import com.huawei.hms.api.HuaweiMobileServicesUtil;
import com.huawei.hms.common.ApiException;

/**
 * @Author: ZhangJF
 * @Date: 2022/3/31
 * @Describe:
 */
public class HWPushProvider extends BasePushProvider {
    public static String TAG = "HWPushProvider";
    PushHandler handler = BeautyPushManager.getInstance().getHandler();

    @Override
    public void register(Context context) {
        // 创建一个新线程
        new Thread() {
            @Override
            public void run() {
                String token = getRegisterId(context);
                if (!TextUtils.isEmpty(token)) {
                    PushPlatform mixPushPlatform = new PushPlatform(PlatformType.huawei.getValue(), token);
                    handler.getPushReceiver().onRegisterSucceed(mixPushPlatform);
                }
            }
        }.start();
    }

    @Override
    public void unRegister(Context context) {

    }

    @Override
    public String getRegisterId(Context context) {
        try {
            // 从agconnect-services.json文件中读取APP_ID
            AGConnectOptions options = AGConnectInstance.getInstance().getOptions();
            String appId = options.getString("client/app_id");
            String token = HmsInstanceId.getInstance(context).getToken(appId, "HCM");
            return token;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isSupport(Context context) {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return false;
        }
        String manufacturer = Build.MANUFACTURER.toLowerCase();
        if (!manufacturer.equals("huawei")) {
            return false;
        }
        int available = HuaweiMobileServicesUtil.isHuaweiMobileServicesAvailable(context);
        return (available == AvailableCode.SUCCESS);
    }

    @Override
    public String getPlatformName() {
        return PlatformType.huawei.getValue();
    }
}
