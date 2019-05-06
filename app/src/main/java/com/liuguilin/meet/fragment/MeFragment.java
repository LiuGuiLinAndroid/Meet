package com.liuguilin.meet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.qrcode.encoder.QRCode;
import com.liuguilin.meet.R;
import com.liuguilin.meet.base.BaseFragment;
import com.liuguilin.meet.im.IMSDK;
import com.liuguilin.meet.im.IMUser;
import com.liuguilin.meet.ui.QrCodeActivity;
import com.liuguilin.meet.ui.UserInfoActivity;
import com.liuguilin.meet.utils.GlideUtils;

import cn.bmob.v3.datatype.BmobFile;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * FileName: MeFragment
 * Founder: 刘桂林
 * Create Date: 2019/3/3 13:41
 * Profile: 我
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {

    private CircleImageView iv_user;
    private TextView tv_niname;
    private ImageView iv_sex;
    private TextView tv_account;
    private LinearLayout ll_qrcode;
    private LinearLayout ll_user;
    private ImageView iv_new_firend_point;
    private LinearLayout ll_new_friend;

    //对外的个人信息

    //头像
    public static String PhotoUrl = "";
    //性别
    public static boolean SEX = true;
    //昵称
    public static String NICKNAME = "";
    //城市
    public static String CITY = "";
    //账号
    public static String ACCOUNT = "";
    //简介
    public static String DESC = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        iv_user = (CircleImageView) view.findViewById(R.id.iv_user);
        tv_niname = (TextView) view.findViewById(R.id.tv_niname);
        iv_sex = (ImageView) view.findViewById(R.id.iv_sex);
        tv_account = (TextView) view.findViewById(R.id.tv_account);

        ll_qrcode = (LinearLayout) view.findViewById(R.id.ll_qrcode);
        ll_user = (LinearLayout) view.findViewById(R.id.ll_user);
        iv_new_firend_point = (ImageView) view.findViewById(R.id.iv_new_firend_point);
        ll_new_friend = (LinearLayout) view.findViewById(R.id.ll_new_friend);

        ll_qrcode.setOnClickListener(this);
        ll_user.setOnClickListener(this);
        ll_new_friend.setOnClickListener(this);

        updateUser();
    }

    private void updateUser() {
        IMUser imUser = IMSDK.getCurrentUser();
        if (imUser != null) {
            BmobFile bmobFile = imUser.getAvatar();
            if (bmobFile != null) {
                PhotoUrl = bmobFile.getFileUrl();
                if (!TextUtils.isEmpty(PhotoUrl)) {
                    GlideUtils.loadImg(getActivity(), PhotoUrl,R.drawable.img_def_photo, iv_user);
                }
            }

            NICKNAME = getString(R.string.str_me_not_nickname);

            if(!TextUtils.isEmpty(imUser.getNickname())){
                NICKNAME = imUser.getNickname();
            }

            //昵称
            tv_niname.setText(NICKNAME);

            ACCOUNT = imUser.getMobilePhoneNumber();
            //账号
            tv_account.setText(ACCOUNT);

            SEX = imUser.isSex();
            //性别
            iv_sex.setImageResource(SEX ? R.drawable.img_boy : R.drawable.img_girl);

            //城市
            CITY = imUser.getCity();

            //简介
            DESC = getString(R.string.str_reg_no_desc);
            if(!TextUtils.isEmpty(imUser.getDesc())){
                DESC = imUser.getDesc();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_qrcode:
                startActivity(new Intent(getActivity(), QrCodeActivity.class));
                break;
            case R.id.ll_user:
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.ll_new_friend:

                break;
        }
    }
}
