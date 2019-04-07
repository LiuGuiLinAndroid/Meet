package com.liuguilin.meet.utils;

import android.util.Log;

/**
 * FileName: IMLog
 * Founder: 刘桂林
 * Create Date: 2019/4/7 14:58
 * Profile: Log
 */
public class IMLog {

    private static final String TAG = "IMLog";

    private static final boolean DEBUG = true;

    public static void i(String i){
        if(DEBUG){
            Log.i(TAG,i);
        }
    }

    public static void e(String e){
        if(DEBUG){
            Log.e(TAG,e);
        }
    }
}
