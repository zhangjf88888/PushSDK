package com.adnonstop.beautypushlib.mipush;

import android.content.Context;
import android.util.Log;

import com.adnonstop.beautypushlib.BeautyPushManager;
import com.adnonstop.beautypushlib.PushHandler;
import com.adnonstop.beautypushlib.base.PlatformType;
import com.adnonstop.beautypushlib.bean.PushMessage;
import com.adnonstop.beautypushlib.bean.PushPlatform;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;

/**
 * @Author: ZhangJF
 * @Date: 2022/3/31
 * @Describe: 接收推送消息, 运行在非UI线程中。
 */
public class MiPushMessageReceiver extends PushMessageReceiver {


    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
        //接收服务器发送的透传消息
//        Log.e("TAG", "onReceivePassThroughMessage: miPushMessage = " + message.toString());
        PushMessage pushMessage = new PushMessage();
        pushMessage.setPlatform(PlatformType.mi.getValue());
        pushMessage.setTitle(message.getTitle());
        pushMessage.setPayload(message.getContent());
        pushMessage.setDescription(message.getDescription());
        pushMessage.setPassThrough(true);
        BeautyPushManager.getInstance().getHandler().getPushReceiver().onPassThroughMessageArrived(pushMessage);
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
        //用来接收服务器发来的通知栏消息（消息到达客户端后会弹出通知，通知消息到达时。不需要用户点击通知）
//        Log.e("TAG", "onNotificationMessageArrived: miPushMessage = " + message.toString());
        PushMessage pushMessage = new PushMessage();
        pushMessage.setPlatform(PlatformType.mi.getValue());
        pushMessage.setTitle(message.getTitle());
        pushMessage.setPayload(message.getContent());
        pushMessage.setDescription(message.getDescription());
        pushMessage.setPassThrough(false);
        BeautyPushManager.getInstance().getHandler().getPushReceiver().onNotificationMessageArrived(pushMessage);
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
        //只对"自定义点击行为"有效
        //接收服务器发来的通知栏消息（用户点击通知栏时触发）。用户点击了预定义通知消息，消息不会通过onNotificationMessageClicked方法传到客户端。
//        Log.e("TAG", "onNotificationMessageArrived: miPushMessage = " + message.toString());
        PushMessage pushMessage = new PushMessage();
        pushMessage.setPlatform(PlatformType.mi.getValue());
        pushMessage.setTitle(message.getTitle());
        pushMessage.setPayload(message.getContent());
        pushMessage.setDescription(message.getDescription());
        pushMessage.setPassThrough(false);
        BeautyPushManager.getInstance().getHandler().getPushReceiver().onNotificationMessageClicked(pushMessage);
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        super.onCommandResult(context, message);
        //接收客户端向服务器发送命令消息后返回的响应
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        //接受客户端向服务器发送注册命令消息后返回的响应。
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                PushPlatform mixPushPlatform = new PushPlatform(PlatformType.mi.getValue(), cmdArg1);
                BeautyPushManager.getInstance().getHandler().getPushReceiver().onRegisterSucceed(mixPushPlatform);
            }
        }
    }
}
