package com.liuguilin.meet.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liuguilin.meet.MainActivity;
import com.liuguilin.meet.R;
import com.liuguilin.meet.base.BaseActivity;
import com.liuguilin.meet.im.IMSDK;
import com.liuguilin.meet.im.IMUser;
import com.liuguilin.meet.manager.DialogManager;
import com.liuguilin.meet.view.DialogView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * FileName: LoginActivity
 * Founder: 刘桂林
 * Create Date: 2019/3/25 22:15
 * Profile: 18679606764 123456
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout include_title_iv_back;
    private TextView include_title_text;
    private TextView title_right_text;
    private EditText et_account;
    private EditText et_password;
    private TextView tv_login_tips;
    private Button btn_login;

    private DialogView mTipsDialog;
    private TextView tv_reg_account;
    private TextView tv_forget_pw;
    private TextView tv_tips_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initTipsDialog();
        initView();
    }

    //初始化提示框
    private void initTipsDialog() {
        mTipsDialog = DialogManager.getInstance().initView(this, R.layout.dialog_tips_login_item, Gravity.BOTTOM);
        tv_reg_account = mTipsDialog.findViewById(R.id.tv_reg_account);
        tv_forget_pw = mTipsDialog.findViewById(R.id.tv_forget_pw);
        tv_tips_cancel = mTipsDialog.findViewById(R.id.tv_tips_cancel);

        tv_reg_account.setOnClickListener(this);
        tv_forget_pw.setOnClickListener(this);
        tv_tips_cancel.setOnClickListener(this);
    }

    private void initView() {
        include_title_iv_back = (RelativeLayout) findViewById(R.id.include_title_iv_back);
        include_title_text = (TextView) findViewById(R.id.include_title_text);
        title_right_text = (TextView) findViewById(R.id.title_right_text);

        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_login_tips = (TextView) findViewById(R.id.tv_login_tips);
        btn_login = (Button) findViewById(R.id.btn_login);

        tv_login_tips.setOnClickListener(this);
        btn_login.setOnClickListener(this);

        include_title_iv_back.setVisibility(View.GONE);
        include_title_text.setText(getString(R.string.str_login_login));
        title_right_text.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_tips:
                DialogManager.getInstance().show(mTipsDialog);
                break;
            case R.id.btn_login:
                loginAccount();
                break;
            case R.id.tv_reg_account:
                startPwOpActivity("reg");
                break;
            case R.id.tv_forget_pw:
                startPwOpActivity("forget");
                break;
            case R.id.tv_tips_cancel:
                DialogManager.getInstance().hide(mTipsDialog);
                break;
        }
    }

    /**
     * 跳转密码操作页面
     * @param type
     */
    private void startPwOpActivity(String type){
        DialogManager.getInstance().hide(mTipsDialog);
        Intent intent = new Intent(this,PwOpActivity.class);
        intent.putExtra("type",type);
        startActivity(intent);
    }

    /**
     * 登录
     */
    private void loginAccount() {
        String account = et_account.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, getString(R.string.str_toast_account_null), Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, getString(R.string.str_toast_pw_null), Toast.LENGTH_SHORT).show();
            return;
        }

        //登录
        IMSDK.login(account, password, new SaveListener<IMUser>() {
            @Override
            public void done(IMUser imUser, BmobException e) {
                if(e == null){
                    Toast.makeText(LoginActivity.this, getString(R.string.str_login_success), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, getString(R.string.str_login_fail) + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
