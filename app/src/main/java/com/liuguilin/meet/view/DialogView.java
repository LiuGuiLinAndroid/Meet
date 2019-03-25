package com.liuguilin.meet.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

/**
 * FileName: DialogView
 * Founder: 刘桂林
 * Create Date: 2019/3/24 18:02
 * Profile: Dialog
 */
public class DialogView extends Dialog {

    public DialogView(Context context, int layout, int style, int gravity) {
        super(context, style);
        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = gravity;
        window.setAttributes(params);
    }
}
