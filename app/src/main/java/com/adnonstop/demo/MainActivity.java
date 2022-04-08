package com.adnonstop.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.adnonstop.beautypushlib.BeautyPushManager;
import com.adnonstop.beautypushlib.base.IPushReceiver;
import com.adnonstop.beautypushlib.bean.PushMessage;
import com.adnonstop.beautypushlib.bean.PushPlatform;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BeautyPushManager.getInstance().register(this, new IPushReceiver() {
            @Override
            public void onRegisterSucceed(PushPlatform platform) {
                Log.e("TAG", "onRegisterSucceed: " + platform.toString());
            }

            @Override
            public void onNotificationMessageClicked(PushMessage message) {
                Log.e("TAG", "onNotificationMessageClicked: " + message.toString());
            }

            @Override
            public void onNotificationMessageArrived(PushMessage message) {
                Log.e("TAG", "onNotificationMessageArrived: " + message.toString());
            }

            @Override
            public void onPassThroughMessageArrived(PushMessage message) {
                Log.e("TAG", "onPassThroughMessageArrived: " + message.toString());
            }
        });
    }
}