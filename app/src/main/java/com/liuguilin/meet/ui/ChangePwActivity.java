package com.liuguilin.meet.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liuguilin.meet.R;
import com.liuguilin.meet.base.BaseActivity;
import com.liuguilin.meet.im.IMSDK;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * FileName: ChangePwActivity
 * Founder: 刘桂林
 * Create Date: 2019/4/7 17:40
 * Profile: 修改密码
 */
public class ChangePwActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout include_title_iv_back;
    private TextView include_title_text;
    private TextView title_right_text;
    private EditText et_old;
    private EditText et_new;
    private EditText et_new_to;
    private Button btn_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepw);
        initView();
    }

    private void initView() {
        include_title_iv_back = (RelativeLayout) findViewById(R.id.include_title_iv_back);
        include_title_text = (TextView) findViewById(R.id.include_title_text);
        title_right_text = (TextView) findViewById(R.id.title_right_text);

        et_old = (EditText) findViewById(R.id.et_old);
        et_new = (EditText) findViewById(R.id.et_new);
        et_new_to = (EditText) findViewById(R.id.et_new_to);
        btn_change = (Button) findViewById(R.id.btn_change);

        btn_change.setOnClickListener(this);
        include_title_iv_back.setOnClickListener(this);

        include_title_text.setText(getString(R.string.str_change_pw_title_text));
        title_right_text.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.include_title_iv_back:
                finish();
                break;
            case R.id.btn_change:
                changePw();
                break;
        }
    }

    /**
     * 修改密碼
     */
    private void changePw() {
        String old = et_old.getText().toString().trim();
        if (TextUtils.isEmpty(old)) {
            Toast.makeText(this, getString(R.string.str_toast_pw_null), Toast.LENGTH_SHORT).show();
            return;
        }

        String newPw = et_new.getText().toString().trim();
        if (TextUtils.isEmpty(newPw)) {
            Toast.makeText(this, getString(R.string.str_toast_pw_null), Toast.LENGTH_SHORT).show();
            return;
        }

        String newTo = et_new_to.getText().toString().trim();
        if (TextUtils.isEmpty(newTo)) {
            Toast.makeText(this, getString(R.string.str_toast_pw_null), Toast.LENGTH_SHORT).show();
            return;
        }

        if(!newPw.equals(newTo)){
            Toast.makeText(this, getString(R.string.str_toast_pw_to_error), Toast.LENGTH_SHORT).show();
            return;
        }

        IMSDK.updatePassword(old, newPw, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    Toast.makeText(ChangePwActivity.this, getString(R.string.str_toast_reset_success), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(ChangePwActivity.this, getString(R.string.str_toast_reset_fail), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
