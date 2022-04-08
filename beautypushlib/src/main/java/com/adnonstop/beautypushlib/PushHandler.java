package com.adnonstop.beautypushlib;

import android.os.Looper;

import com.adnonstop.beautypushlib.bean.PushMessage;
import com.adnonstop.beautypushlib.bean.PushPlatform;
import com.adnonstop.beautypushlib.base.IPushReceiver;

/**
 * @Author: ZhangJF
 * @Date: 2022/3/31
 * @Describe:
 */
public class PushHandler {
    final DefaultIPushReceiver pushReceiver;
    private IPushReceiver callIPushReceiver;

    public PushHandler() {
        pushReceiver = new DefaultIPushReceiver(this);
    }


    public IPushReceiver getPushReceiver() {
        return pushReceiver;
    }

    public void setCallPushReceiver(IPushReceiver callIPushReceiver) {
        this.callIPushReceiver = callIPushReceiver;
    }

    static class DefaultIPushReceiver implements IPushReceiver {
        private PushHandler handler;
        static PushPlatform notificationPlatform = null;

        public DefaultIPushReceiver(PushHandler handler) {
            this.handler = handler;
        }

        @Override
        public void onRegisterSucceed(final PushPlatform platform) {
            if (platform == null) {
                return;
            }
            if (notificationPlatform != null) {
                return;
            }
            notificationPlatform = platform;
            if (handler.callIPushReceiver == null) {
                return;
            }
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                // 在异步进程回调,避免阻塞主进程
                new Thread(() -> handler.callIPushReceiver.onRegisterSucceed(platform)).start();
            } else {
                handler.callIPushReceiver.onRegisterSucceed(platform);
            }
        }

        @Override
        public void onNotificationMessageClicked(PushMessage message) {
            if (handler.callIPushReceiver == null) {
                return;
            }
            handler.callIPushReceiver.onNotificationMessageClicked(message);
        }


        @Override
        public void onNotificationMessageArrived(PushMessage message) {
            if (handler.callIPushReceiver == null) {
                return;
            }
            handler.callIPushReceiver.onNotificationMessageArrived(message);
        }

        @Override
        public void onPassThroughMessageArrived(PushMessage message) {
            if (handler.callIPushReceiver == null) {
                return;
            }
            handler.callIPushReceiver.onPassThroughMessageArrived(message);
        }
    }
}
