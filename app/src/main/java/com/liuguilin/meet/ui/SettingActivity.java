package com.liuguilin.meet.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liuguilin.meet.R;
import com.liuguilin.meet.base.BaseActivity;
import com.liuguilin.meet.im.IMSDK;
import com.liuguilin.meet.utils.SharePreUtils;

/**
 * FileName: SettingActivity
 * Founder: 刘桂林
 * Create Date: 2019/4/7 15:15
 * Profile:
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout include_title_iv_back;
    private TextView include_title_text;
    private TextView title_right_text;
    private LinearLayout ll_change_pw;
    private LinearLayout ll_switch_languaue;
    private ImageView iv_new_msg;
    private Button btn_exit_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        include_title_iv_back = (RelativeLayout) findViewById(R.id.include_title_iv_back);
        include_title_text = (TextView) findViewById(R.id.include_title_text);
        title_right_text = (TextView) findViewById(R.id.title_right_text);

        ll_change_pw = (LinearLayout) findViewById(R.id.ll_change_pw);
        ll_switch_languaue = (LinearLayout) findViewById(R.id.ll_switch_languaue);
        iv_new_msg = (ImageView) findViewById(R.id.iv_new_msg);

        btn_exit_app = (Button) findViewById(R.id.btn_exit_app);

        btn_exit_app.setOnClickListener(this);
        include_title_iv_back.setOnClickListener(this);
        ll_change_pw.setOnClickListener(this);

        include_title_text.setText(getString(R.string.str_setting_title_text));
        title_right_text.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.include_title_iv_back:
                finish();
                break;
            case R.id.btn_exit_app:
                exitApp();
                break;
            case R.id.ll_change_pw:
                startActivity(new Intent(this,ChangePwActivity.class));
                break;
        }
    }

    private void exitApp() {
        IMSDK.exitUser();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
