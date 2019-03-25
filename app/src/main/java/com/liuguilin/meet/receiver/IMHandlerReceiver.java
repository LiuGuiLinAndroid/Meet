package com.liuguilin.meet.receiver;

import android.content.Context;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

/**
 * FileName: IMHandlerReceiver
 * Founder: 刘桂林
 * Create Date: 2019/3/24 19:03
 * Profile:
 */
public class IMHandlerReceiver extends BmobIMMessageHandler {

    private Context mContext;

    public IMHandlerReceiver(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onMessageReceive(MessageEvent messageEvent) {
        super.onMessageReceive(messageEvent);
    }

    @Override
    public void onOfflineReceive(OfflineMessageEvent offlineMessageEvent) {
        super.onOfflineReceive(offlineMessageEvent);
    }
}
