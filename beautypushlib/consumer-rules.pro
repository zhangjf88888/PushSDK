
-keep public class * extends com.adnonstop.beautypushlib.base.BasePushProvider

# oppo混淆配置
-keep public class * extends android.app.Service
-keep class com.heytap.msp.** { *;}

# vivo混淆配置
-dontwarn com.vivo.push.**
-keep class com.vivo.push.**{*; }
-keep class com.vivo.vms.**{*; }
-keep class com.adnonstop.beautypushlib.vivopush.VivoPushMessageReceiver{*;}

#小米混淆配置
-keep class com.adnonstop.beautypushlib.mipush.MiPushMessageReceiver {*;}

#华为混淆配置
-ignorewarnings
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.huawei.hianalytics.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}