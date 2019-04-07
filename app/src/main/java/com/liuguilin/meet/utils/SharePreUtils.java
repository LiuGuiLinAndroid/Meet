package com.liuguilin.meet.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * FileName: SharePreUtils
 * Founder: 刘桂林
 * Create Date: 2019/4/7 15:25
 * Profile:
 */
public class SharePreUtils {

    private static SharePreUtils mInstance = null;

    private static final String SP_NAME = "config";

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private SharePreUtils(){}

    public static SharePreUtils getInstance(){
        if(mInstance == null){
            synchronized (SharePreUtils.class){
                if(mInstance == null){
                    mInstance = new SharePreUtils();
                }
            }
        }
        return mInstance;
    }

    public void initSp(Context mContext){
        sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void putString(String key,String value){
        editor.putString(key,value);
        editor.apply();
    }

    public String getString(String key){
        return sp.getString(key,"");
    }

    public void deleteKey(String key){
        editor.remove(key);
        editor.apply();
    }
}
