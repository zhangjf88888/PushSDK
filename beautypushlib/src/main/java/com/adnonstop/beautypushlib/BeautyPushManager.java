package com.adnonstop.beautypushlib;

import static com.adnonstop.beautypushlib.base.PlatformType.*;

import android.content.Context;

import com.adnonstop.beautypushlib.base.BasePushProvider;
import com.adnonstop.beautypushlib.base.PlatformType;
import com.adnonstop.beautypushlib.base.IPushReceiver;
import com.adnonstop.beautypushlib.huaweipush.HWPushProvider;
import com.adnonstop.beautypushlib.mipush.MiPushProvider;
import com.adnonstop.beautypushlib.oppopush.OppoPushProvider;
import com.adnonstop.beautypushlib.utils.ProcessUtils;
import com.adnonstop.beautypushlib.vivopush.VivoPushProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: ZhangJF
 * @Date: 2022/3/29
 * @Describe:
 */
public class BeautyPushManager {
    private static String TAG = "BeautyPush";
    public static boolean isDeBug = true;
    private static volatile BeautyPushManager manager;
    private Map<String, BasePushProvider> pushManagerMap = new HashMap<>();
    private BasePushProvider notificationPushProvider;
    private PushHandler handler = new PushHandler();

    private BeautyPushManager() {
    }

    public static BeautyPushManager getInstance() {
        if (manager == null) {
            synchronized (BeautyPushManager.class) {
                if (manager == null) {
                    manager = new BeautyPushManager();
                }
            }
        }
        return manager;
    }


    public PushHandler getHandler() {
        return handler;
    }


    /**
     * 默认初始化方式
     * * 1. 根据用户的手机型号优先注册厂家的推送平台。
     * * 2. 不支持手机厂商推送平台的手机使用小米推送。
     * * 3. 不使用透传
     *
     * @param context
     * @param IPushReceiver 设置监听
     */
    public void register(Context context, IPushReceiver IPushReceiver) {
        register(context, mi, IPushReceiver);
    }


    /**
     * @param context
     * @param defaultPlatform 默认推送方式 (始终优先使用手机支持的推送平台)
     * @param IPushReceiver    设置监听
     */
    public void register(Context context, PlatformType defaultPlatform, IPushReceiver IPushReceiver) {
        if (!ProcessUtils.isMainProcess(context)) {
            return;
        }
        handler.setCallPushReceiver(IPushReceiver);

        addPlatformProviderByType(mi);
        addPlatformProviderByType(oppo);
        addPlatformProviderByType(vivo);
        addPlatformProviderByType(huawei);

        BasePushProvider pushProvider = null;
        // 获取厂商推送
        Set<String> keys = pushManagerMap.keySet();
        for (String key : keys) {
            // 除开默认的推送
            if (!key.equals(defaultPlatform.getValue())) {
                BasePushProvider tmp = pushManagerMap.get(key);
                if (tmp != null && tmp.isSupport(context)) {
                    pushProvider = tmp;
                }
            }
        }
        BasePushProvider defaultProvider = pushManagerMap.get(defaultPlatform.getValue());
        if (defaultProvider == null) {
            return;
        }
        if (pushProvider == null) {
            defaultProvider.register(context);
            notificationPushProvider = defaultProvider;
        } else {
            pushProvider.register(context);
            notificationPushProvider = pushProvider;
        }
    }

    protected void addPlatformProviderByType(PlatformType platformType) {
        switch (platformType) {
            case mi:
                addPlatformProvider(mi.getValue(), new MiPushProvider());
                break;
            case oppo:
                addPlatformProvider(oppo.getValue(), new OppoPushProvider());
                break;
            case vivo:
                addPlatformProvider(vivo.getValue(), new VivoPushProvider());
                break;
            case huawei:
                addPlatformProvider(huawei.getValue(), new HWPushProvider());
                break;
        }
    }


    public void addPlatformProvider(String platformName, BasePushProvider provider) {
        if (pushManagerMap.containsKey(platformName)) {
            return;
        }
        pushManagerMap.put(platformName, provider);
    }
}
