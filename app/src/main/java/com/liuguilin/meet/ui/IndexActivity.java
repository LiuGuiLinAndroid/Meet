package com.liuguilin.meet.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.liuguilin.meet.MainActivity;
import com.liuguilin.meet.R;
import com.liuguilin.meet.base.BaseActivity;
import com.liuguilin.meet.im.IMSDK;
import com.liuguilin.meet.im.IMUser;

/**
 * FileName: IndexActivity
 * Founder: 刘桂林
 * Create Date: 2019/3/25 23:34
 * Profile: 引导
 */
public class IndexActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        initView();
    }

    private void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IMUser user = IMSDK.getCurrentUser();
                if(user == null){
                    startActivity(new Intent(IndexActivity.this,LoginActivity.class));
                }else{
                    startActivity(new Intent(IndexActivity.this, MainActivity.class));
                }
                finish();
            }
        },2 * 1000);
    }
}
