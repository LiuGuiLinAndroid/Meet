package com.liuguilin.meet.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * FileName: PwOpActivity
 * Founder: 刘桂林
 * Create Date: 2019/3/25 22:39
 * Profile: 密码操作
 */
public class PwOpActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout include_title_iv_back;
    private TextView include_title_text;
    private TextView title_right_text;
    private EditText et_phone;
    private EditText et_password;
    private EditText et_code;
    private TextView tv_get_code;
    private Button btn_op;
    private TextView tv_forget_tips;

    private static int TYPE = -1;

    private static final int WHAT_GET_SMS_CODE = 1001;
    private static int SMS_TIME = 60;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case WHAT_GET_SMS_CODE:
                    tv_get_code.setText(SMS_TIME + "s");
                    if (SMS_TIME <= 0) {
                        SMS_TIME = 60;
                        tv_get_code.setEnabled(true);
                        tv_get_code.setText(getString(R.string.str_reg_get_code));
                        mHandler.removeMessages(WHAT_GET_SMS_CODE);
                    } else {
                        SMS_TIME--;
                        mHandler.sendEmptyMessageDelayed(WHAT_GET_SMS_CODE, 1000);
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwop);

        initView();
    }

    private void initView() {
        include_title_iv_back = (RelativeLayout) findViewById(R.id.include_title_iv_back);
        include_title_text = (TextView) findViewById(R.id.include_title_text);
        title_right_text = (TextView) findViewById(R.id.title_right_text);

        et_phone = (EditText) findViewById(R.id.et_phone);
        et_password = (EditText) findViewById(R.id.et_password);
        et_code = (EditText) findViewById(R.id.et_code);
        tv_get_code = (TextView) findViewById(R.id.tv_get_code);
        btn_op = (Button) findViewById(R.id.btn_op);
        tv_forget_tips = findViewById(R.id.tv_forget_tips);

        include_title_iv_back.setOnClickListener(this);
        btn_op.setOnClickListener(this);
        tv_get_code.setOnClickListener(this);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if (type.equals("reg")) {
            TYPE = 0;
            include_title_text.setText(getString(R.string.str_reg_reg));
            btn_op.setText(getString(R.string.str_reg_reg));
            tv_forget_tips.setVisibility(View.GONE);
        } else if (type.equals("forget")) {
            TYPE = 1;
            include_title_text.setText(getString(R.string.str_forget_pw));
            btn_op.setText(getString(R.string.str_forget_reset));
            tv_forget_tips.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.include_title_iv_back:
                finish();
                break;
            case R.id.btn_op:
                accountOp();
                break;
            case R.id.tv_get_code:
                getSmsCode();
                break;
        }
    }

    /**
     * 获取短信验证码
     */
    private void getSmsCode() {
        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, getString(R.string.str_toast_phone_null), Toast.LENGTH_SHORT).show();
            return;
        }

        IMSDK.requestSMSCode(phone, new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    //倒计时60s
                    mHandler.sendEmptyMessage(WHAT_GET_SMS_CODE);
                    tv_get_code.setEnabled(false);
                    Toast.makeText(PwOpActivity.this, getString(R.string.str_toast_get_code_success), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PwOpActivity.this, getString(R.string.str_toast_get_code_fail), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 账户操作
     */
    private void accountOp() {

        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, getString(R.string.str_toast_phone_null), Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, getString(R.string.str_toast_pw_null), Toast.LENGTH_SHORT).show();
            return;
        }

        String code = et_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, getString(R.string.str_toast_code_null), Toast.LENGTH_SHORT).show();
            return;
        }

        verifySmsCode(phone, password, code);
    }

    /**
     * 验证短信验证码
     *
     * @param phone
     * @param password
     * @param code
     */
    private void verifySmsCode(final String phone, final String password, final String code) {
        IMSDK.verifySmsCode(phone, code, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    //注册
                    if (TYPE == 0) {
                        signUp(phone, password);
                        //忘记密码
                    } else if (TYPE == 1) {
                        forgetPw(code,password);
                    }
                } else {
                    Toast.makeText(PwOpActivity.this, getString(R.string.str_toast_code_error), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * 重置密码
     * @param code
     * @param password
     */
    private void forgetPw(String code, String password) {
        IMSDK.resetPasswordBySMSCode(code, password, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    Toast.makeText(PwOpActivity.this, getString(R.string.str_toast_reset_success), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(PwOpActivity.this, getString(R.string.str_toast_reset_fail), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 注册
     *
     * @param phone
     * @param password
     */
    private void signUp(String phone, String password) {
        IMSDK.signUp(phone, password, new SaveListener<Object>() {
            @Override
            public void done(Object o, BmobException e) {
                if (e == null) {
                    Toast.makeText(PwOpActivity.this, getString(R.string.str_toast_reg_success), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PwOpActivity.this, getString(R.string.str_toast_reg_fail) + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
