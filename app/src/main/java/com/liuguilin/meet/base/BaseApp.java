package com.liuguilin.meet.base;

import android.app.Application;

import com.liuguilin.meet.receiver.IMHandlerReceiver;
import com.liuguilin.meet.utils.SharePreUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.newim.BmobIM;

/**
 * FileName: BaseApp
 * Founder: 刘桂林
 * Create Date: 2019/3/3 13:36
 * Profile: 基类
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initApp();
    }

    private void initApp() {
        if (getApplicationInfo().packageName.equals(getMyProcessName())){
            BmobIM.init(this);
            BmobIM.registerDefaultMessageHandler(new IMHandlerReceiver(this));
            SharePreUtils.getInstance().initSp(this);
        }
    }

    /**
     * 获取当前运行的进程名
     *
     * @return
     */
    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
