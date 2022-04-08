# BeautyPushSDK
Android简单集成OPPO、VIVO、小米、华为推送服务
1. [oppo推送](https://open.oppomobile.com/wiki/doc#id=10445)
2. [vivo推送](https://push.vivo.com.cn/#/)
3. [小米推送](https://dev.mi.com/console/appservice/push.html)
4. [华为推送](https://developer.huawei.com/consumer/cn/hms/huawei-pushkit)

    |    | OPPO | VIVO | 小米 | 华为 |
    | :--| :--: | :--: | :---:| :--: |
    | 透传|不支持|不支持 | 支持 | 支持 |

****************************************使用流程****************************************
### 
1. 申请各个平台的 APP_ID 和密钥，并在build.gradle配置文件中设置对应清单
  ```groovy
manifestPlaceholders = [
                                'VIVO_APP_ID'       : 'xxxxxxx',
                                'VIVO_APP_KEY'      : 'xxxxxxx',
                                'MI_APP_ID'         : 'xxxxxxx',
                                'MI_APP_KEY'        : 'xxxxxxx',
                                'OPPO_APP_KEY'      : 'xxxxxxx',
                                'OPPO_APP_SECRET'   : 'xxxxxxx',
        ]
```  
        
    特别地，华为：配置  maven { url 'https://developer.huawei.com/repo/' }；
            App中添加了“agconnect-services.json”，并在“buildscript > dependencies”中增加agcp插件配置。
                buildscript {
                    dependencies {
                        ...
                        // 增加agcp插件配置，推荐您使用最新版本的agcp插件。
                        classpath 'com.huawei.agconnect:agcp:1.6.0.300'
                    }
                }
            详见[华为推送集成配置](https://developer.huawei.com/consumer/cn/doc/development/HMSCore-Guides/android-integrating-sdk-0000001050040084)

2. APP注册推送 
    BeautyPushManager.getInstance().register()。
    为避免不合规检测，推荐在用户同意《用户协议》和《隐私协议》后进行注册


3. 注意事项：
  - 成功注册推送后，想要通知栏正常显示推送，需要APP请求[打开通知]权限。
  - 对于Android 8以上的还需要设置信息通道Channel，详见[创建和管理通知渠道](https://developer.android.google.cn/training/notify-user/channels)
  - 在推送平台控制台中发送推送时，要根据APP设置的channel创建推送信息。
  - 成功注册推送，点击通知栏的推送信息跳转到具体页面，这个具体页面的配置还需要根据各个推送平台的接入文档自行在APP的“AndroidManifest.xml”进行配置
       1. [VIVO通知消息的处理](https://dev.vivo.com.cn/documentCenter/doc/156) [Q11：跳转配置打开应用页面和自定义键值对的示例是什么？] *
       2. [OPPO通知消息的处理](https://open.oppomobile.com/wiki/doc#id=11256) [3、打开范围中，启动应用内页的地址如何填写？怎么传递参数呢？] *
       3. [小米通知消息的处理](https://dev.mi.com/console/doc/detail?pId=2625)  [5.2.2. 通知消息的处理] *
       4. [华为通知消息的处理](https://developer.huawei.com/consumer/cn/doc/development/HMSCore-Guides/andorid-basic-clickaction-0000001087554076)  [自定义点击消息动作] *



        
