package com.adnonstop.beautypushlib.oppopush;

import android.util.Log;

import com.adnonstop.beautypushlib.BeautyPushManager;
import com.adnonstop.beautypushlib.base.PlatformType;
import com.adnonstop.beautypushlib.bean.PushPlatform;
import com.heytap.msp.push.callback.ICallBackResultService;

/**
 * @Author: ZhangJF
 * @Date: 2022/4/2
 * @Describe:
 */
public class OppoPushRegisterReceiver implements ICallBackResultService {


    @Override
    public void onRegister(int responseCode, String registerID) {
        Log.e("TAG", "onRegister: responseCode ====== " + responseCode);
        if (responseCode == 0){
            PushPlatform mixPushPlatform = new PushPlatform(PlatformType.oppo.getValue(), registerID);
            BeautyPushManager.getInstance().getHandler().getPushReceiver().onRegisterSucceed(mixPushPlatform);
        }
    }

    @Override
    public void onUnRegister(int responseCode) {

    }

    @Override
    public void onSetPushTime(int responseCode, String pushTime) {

    }

    @Override
    public void onGetPushStatus(int responseCode, int status) {

    }

    @Override
    public void onGetNotificationStatus(int responseCode, int status) {

    }

    @Override
    public void onError(int i, String s) {

    }
}
