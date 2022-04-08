package com.adnonstop.beautypushlib.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @Author: ZhangJF
 * @Date: 2022/3/31
 * @Describe: 推送信息封装
 */
public class PushMessage implements Serializable, Parcelable {
    /**
     * 通知栏标题,透传该字段为空
     */
    private String title;
    /**
     * 通知栏副标题,透传该字段为空
     */
    private String description;
    /**
     * 推送所属平台,比如mi/huawei
     */
    private String platform;
    /**
     * 推送附属的内容信息
     */
    private String payload;
    /**
     * 是否是透传推送
     */
    private boolean passThrough;
//    private String msgId;

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPassThrough() {
        return passThrough;
    }

    public void setPassThrough(boolean passThrough) {
        this.passThrough = passThrough;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }


    @Override
    public String toString() {
        return "UnifiedPushMessage{" +
                "title='" + title + '\'' +
                ", content='" + description + '\'' +
                ", platform='" + platform + '\'' +
                ", payload='" + payload + '\'' +
                ", passThrough=" + passThrough +
                '}';
    }


//    public void setMsgId(String msgId) {
//        this.msgId = msgId;
//    }

//    public String getMsgId() {
//        return msgId;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.platform);
        dest.writeString(this.payload);
        dest.writeByte(this.passThrough ? (byte) 1 : (byte) 0);
    }

    public PushMessage() {
    }

    protected PushMessage(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.platform = in.readString();
        this.payload = in.readString();
        this.passThrough = in.readByte() != 0;
    }

    public static final Parcelable.Creator<PushMessage> CREATOR = new Parcelable.Creator<PushMessage>() {
        @Override
        public PushMessage createFromParcel(Parcel source) {
            return new PushMessage(source);
        }

        @Override
        public PushMessage[] newArray(int size) {
            return new PushMessage[size];
        }
    };
}
