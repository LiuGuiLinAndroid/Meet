package com.liuguilin.meet.manager;

import android.content.Context;
import android.view.Gravity;

import com.liuguilin.meet.R;
import com.liuguilin.meet.view.DialogView;

/**
 * FileName: DialogManager
 * Founder: 刘桂林
 * Create Date: 2019/3/24 18:05
 * Profile: Dialog 管理类
 */
public class DialogManager {

    private static DialogManager mInstace = null;

    private DialogManager(){}

    public static DialogManager getInstance(){
        if(mInstace == null){
            synchronized (DialogManager.class){
                if (mInstace == null){
                    mInstace = new DialogManager();
                }
            }
        }
        return mInstace;
    }

    public DialogView initView(Context mContext,int layoutId){
         return new DialogView(mContext,layoutId,R.style.Theme_dialog, Gravity.CENTER);
    }

    public DialogView initView(Context mContext,int layoutId,int gravity){
        return new DialogView(mContext,layoutId,R.style.Theme_dialog,gravity);
    }

    /**
     * 显示
     *
     * @param mDialog
     */
    public void show(DialogView mDialog) {
        if (mDialog != null) {
            if (!mDialog.isShowing()) {
                mDialog.show();
            }
        }
    }

    /**
     * 隐藏
     *
     * @param mDialog
     */
    public void hide(DialogView mDialog) {
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
        }
    }
}
