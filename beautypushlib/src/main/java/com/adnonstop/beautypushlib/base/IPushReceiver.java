package com.adnonstop.beautypushlib.base;


import com.adnonstop.beautypushlib.bean.PushMessage;
import com.adnonstop.beautypushlib.bean.PushPlatform;

/**
 * @Author: ZhangJF
 * @Date: 2022/3/31
 * @Describe:
 */
public interface IPushReceiver {
    /**
     * 通知栏推送SDK注册成功回调
     */
    void onRegisterSucceed(PushPlatform platform);

    /**
     * 通知栏消息被点击回调
     */
    void onNotificationMessageClicked(PushMessage message);

    /**
     * 通知栏消息消息到达回调
     */
    void onNotificationMessageArrived(PushMessage message);

    /**
     * 透传信息
     */
    void onPassThroughMessageArrived(PushMessage message);

}
