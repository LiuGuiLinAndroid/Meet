package com.liuguilin.meet.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liuguilin.meet.R;
import com.liuguilin.meet.base.BaseActivity;
import com.liuguilin.meet.fragment.MeFragment;
import com.liuguilin.meet.manager.DialogManager;
import com.liuguilin.meet.utils.CommonUtils;
import com.liuguilin.meet.utils.GlideUtils;
import com.liuguilin.meet.view.DialogView;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * FileName: QrCodeActivity
 * Founder: 刘桂林
 * Create Date: 2019/5/6 20:52
 * Profile: 二维码
 */
public class QrCodeActivity extends BaseActivity implements View.OnClickListener, View.OnLongClickListener {

    private RelativeLayout include_title_iv_back;
    private TextView include_title_text;
    private TextView title_right_text;
    private CircleImageView iv_user;
    private TextView tv_niname;
    private ImageView iv_sex;
    private TextView tv_city;
    private ImageView iv_qrcode;

    private DialogView mDialogView;
    private TextView tv_save_qr;
    private TextView tv_dialog_cancel;

    private  Bitmap mBitmap;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        initDiaog();
        initView();
    }

    private void initDiaog() {
        mDialogView = DialogManager.getInstance().initView(this,R.layout.dialog_save_qrcode, Gravity.BOTTOM);
        tv_save_qr = mDialogView.findViewById(R.id.tv_save_qr);
        tv_dialog_cancel = mDialogView.findViewById(R.id.tv_dialog_cancel);

        tv_save_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManager.getInstance().hide(mDialogView);

                boolean result = CommonUtils.saveBitmap(QrCodeActivity.this,mBitmap,System.currentTimeMillis() + ".png");
                if(result){
                    Toast.makeText(QrCodeActivity.this, getString(R.string.str_toast_save_success), Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManager.getInstance().hide(mDialogView);
            }
        });
    }

    private void initView() {
        include_title_iv_back = (RelativeLayout) findViewById(R.id.include_title_iv_back);
        include_title_text = (TextView) findViewById(R.id.include_title_text);
        title_right_text = (TextView) findViewById(R.id.title_right_text);

        iv_user = (CircleImageView) findViewById(R.id.iv_user);
        tv_niname = (TextView) findViewById(R.id.tv_niname);
        iv_sex = (ImageView) findViewById(R.id.iv_sex);

        tv_city = (TextView) findViewById(R.id.tv_city);
        iv_qrcode = (ImageView) findViewById(R.id.iv_qrcode);

        include_title_iv_back.setOnClickListener(this);
        iv_qrcode.setOnLongClickListener(this);

        include_title_text.setText(getString(R.string.str_qrcode_title_text));
        title_right_text.setVisibility(View.GONE);

        if(!TextUtils.isEmpty(MeFragment.PhotoUrl)){
            GlideUtils.loadImg(this,MeFragment.PhotoUrl,R.drawable.img_def_photo,iv_user);
        }

        tv_niname.setText(MeFragment.NICKNAME);

        iv_sex.setImageResource(MeFragment.SEX ? R.drawable.img_boy : R.drawable.img_girl);

        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();

        //Meet#18679606764
        mBitmap = CodeUtils.createImage("Meet#" + MeFragment.ACCOUNT, width * 2 / 3, height / 3, null);
        if(mBitmap != null){
            iv_qrcode.setImageBitmap(mBitmap);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.include_title_iv_back:
                finish();
                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        //长按弹出选择框
        DialogManager.getInstance().show(mDialogView);
        return false;
    }
}
