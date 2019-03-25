package com.liuguilin.meet.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liuguilin.meet.event.MessageEvent;
import com.liuguilin.meet.manager.EventManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * FileName: BaseActivity
 * Founder: 刘桂林
 * Create Date: 2019/3/3 13:35
 * Profile: 基类
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventManager.register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()){
            case EventManager.EVENT_TEST:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventManager.unregister(this);
    }
}
