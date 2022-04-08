package com.adnonstop.beautypushlib.oppopush;

import android.content.Context;
import android.os.Build;

import com.adnonstop.beautypushlib.BeautyPushManager;
import com.adnonstop.beautypushlib.base.BasePushProvider;
import com.adnonstop.beautypushlib.base.PlatformType;
import com.adnonstop.beautypushlib.bean.PushPlatform;
import com.heytap.msp.push.HeytapPushManager;

public class OppoPushProvider extends BasePushProvider {
    public static final String TAG = "OppoPushProvider";


    @Override
    public void register(Context context) {
        String appSecret = getMetaData(context, "OPPO_APP_SECRET");
        String appKey = getMetaData(context, "OPPO_APP_KEY");
        HeytapPushManager.init(context, BeautyPushManager.isDeBug);
        HeytapPushManager.register(context, appKey, appSecret, new OppoPushRegisterReceiver());
    }

    @Override
    public void unRegister(Context context) {
        HeytapPushManager.unRegister();
    }

    @Override
    public String getRegisterId(final Context context) {
        return HeytapPushManager.getRegisterID();
    }

    @Override
    public boolean isSupport(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return false;
        }
        String brand = Build.BRAND.toLowerCase();
        String manufacturer = Build.MANUFACTURER.toLowerCase();
        if (manufacturer.equals("oneplus") || manufacturer.equals("oppo") || brand.equals("oppo") || brand.equals("realme")) {
            HeytapPushManager.init(context, true);
            return HeytapPushManager.isSupportPush(context);
        }
        return false;
    }

    @Override
    public String getPlatformName() {
        return PlatformType.oppo.getValue();
    }
}
