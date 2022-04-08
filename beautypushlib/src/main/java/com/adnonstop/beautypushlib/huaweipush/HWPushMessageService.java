package com.adnonstop.beautypushlib.huaweipush;

import android.util.Log;

import com.adnonstop.beautypushlib.BeautyPushManager;
import com.adnonstop.beautypushlib.PushHandler;
import com.adnonstop.beautypushlib.base.PlatformType;
import com.adnonstop.beautypushlib.bean.PushMessage;
import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;

/**
 * @Author: ZhangJF
 * @Date: 2022/4/1
 * @Describe: 获取信息数据，详见：https://developer.huawei.com/consumer/cn/doc/development/HMSCore-Guides/android-basic-receivemsg-0000001087370610
 */
public class HWPushMessageService extends HmsMessageService {

    @Override
    public void onMessageReceived(RemoteMessage message) {
        //透传信息 和 设置了应用在前台时不由NC展示通知栏消息
        // 获取消息内容
        Log.e("TAG", "HWPushMessageService  get Data: " + message.getData()
                + "\n getFrom: " + message.getFrom()
                + "\n getTo: " + message.getTo()
                + "\n getMessageId: " + message.getMessageId()
                + "\n getSentTime: " + message.getSentTime()
                + "\n getDataMap: " + message.getDataOfMap()
                + "\n getMessageType: " + message.getMessageType()
                + "\n getTtl: " + message.getTtl()
                + "\n getToken: " + message.getToken());
        PushMessage pushMessage = new PushMessage();
        pushMessage.setPlatform(PlatformType.huawei.getValue());
        pushMessage.setPayload(message.getData());
        pushMessage.setPassThrough(true);
        BeautyPushManager.getInstance().getHandler().getPushReceiver().onPassThroughMessageArrived(pushMessage);
    }
}
