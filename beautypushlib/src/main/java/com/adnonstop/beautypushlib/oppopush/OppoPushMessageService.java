package com.adnonstop.beautypushlib.oppopush;

import android.content.Context;

import com.heytap.msp.push.mode.DataMessage;
import com.heytap.msp.push.service.DataMessageCallbackService;

/**
 * @Author: ZhangJF
 * @Date: 2022/4/1
 * @Describe: 透传信息，兼容Android Q版本（目前只支持通知栏消息，透传消息暂不支持。）
 */
public class OppoPushMessageService extends DataMessageCallbackService {

    @Override
    public void processMessage(Context context, DataMessage dataMessage) {
        super.processMessage(context, dataMessage);
    }
}
