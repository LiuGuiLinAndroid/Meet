package com.liuguilin.meet.manager;

import android.content.Context;

import com.liuguilin.meet.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * FileName: EventManager
 * Founder: 刘桂林
 * Create Date: 2019/3/3 16:49
 * Profile: EventBus 封装
 */
public class EventManager {

    //测试消息
    public static final int EVENT_TEST = 1000;

    public static void register(Context mContext) {
        EventBus.getDefault().register(mContext);
    }

    public static void unregister(Context mContext) {
        EventBus.getDefault().unregister(mContext);
    }

    public static void post(int type){
        EventBus.getDefault().post(new MessageEvent(type));
    }
}
