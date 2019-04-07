package com.liuguilin.meet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liuguilin.meet.base.BaseActivity;
import com.liuguilin.meet.entity.Constans;
import com.liuguilin.meet.fragment.FriendFragment;
import com.liuguilin.meet.fragment.MeFragment;
import com.liuguilin.meet.fragment.NewsFragment;
import com.liuguilin.meet.fragment.SessionFragment;
import com.liuguilin.meet.im.IMSDK;
import com.liuguilin.meet.im.IMUser;
import com.liuguilin.meet.manager.EventManager;
import com.liuguilin.meet.ui.SettingActivity;
import com.liuguilin.meet.utils.IMLog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private SessionFragment mSessionFragment;
    private FragmentTransaction mSessionTransaction;

    private FriendFragment mFriendFragment;
    private FragmentTransaction mFriendTransaction;

    private NewsFragment mNewsFragment;
    private FragmentTransaction mNewsTransaction;

    private MeFragment mMeFragment;
    private FragmentTransaction mMeTransaction;

    private RelativeLayout include_title_iv_back;
    private TextView include_title_text;
    private TextView title_right_text;

    private ImageView iv_session;
    private TextView tv_unread_size;
    private TextView tv_session;
    private LinearLayout ll_session;
    private ImageView iv_friend;
    private TextView tv_friend;
    private LinearLayout ll_friend;
    private ImageView iv_news;
    private TextView tv_news;
    private LinearLayout ll_news;
    private ImageView iv_me;
    private ImageView iv_new_msg;
    private TextView tv_me;
    private LinearLayout ll_me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initView();
    }

    private void initFragment() {

        if (mSessionFragment == null) {
            mSessionFragment = new SessionFragment();
            mSessionTransaction = getSupportFragmentManager().beginTransaction();
            mSessionTransaction.add(R.id.mMainLayout, mSessionFragment);
            mSessionTransaction.commit();
        }

        if (mFriendFragment == null) {
            mFriendFragment = new FriendFragment();
            mFriendTransaction = getSupportFragmentManager().beginTransaction();
            mFriendTransaction.add(R.id.mMainLayout, mFriendFragment);
            mFriendTransaction.commit();
        }

        if (mNewsFragment == null) {
            mNewsFragment = new NewsFragment();
            mNewsTransaction = getSupportFragmentManager().beginTransaction();
            mNewsTransaction.add(R.id.mMainLayout, mNewsFragment);
            mNewsTransaction.commit();
        }

        if (mMeFragment == null) {
            mMeFragment = new MeFragment();
            mMeTransaction = getSupportFragmentManager().beginTransaction();
            mMeTransaction.add(R.id.mMainLayout, mMeFragment);
            mMeTransaction.commit();
        }

        checkPhone();
    }

    /**
     * 检查号码
     */
    private void checkPhone() {
        IMUser imUser = IMSDK.getCurrentUser();
        if(imUser != null){
            String phone = imUser.getMobilePhoneNumber();
            IMLog.i("phone:" + phone);
            if(TextUtils.isEmpty(phone)){
                imUser.setMobilePhoneNumber(imUser.getUsername());
                IMSDK.updateUser(imUser, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {

                    }
                });
            }
        }
    }

    private void initView() {

        include_title_iv_back = (RelativeLayout) findViewById(R.id.include_title_iv_back);
        include_title_text = (TextView) findViewById(R.id.include_title_text);
        title_right_text = (TextView) findViewById(R.id.title_right_text);

        iv_session = (ImageView) findViewById(R.id.iv_session);
        tv_unread_size = (TextView) findViewById(R.id.tv_unread_size);
        tv_session = (TextView) findViewById(R.id.tv_session);
        ll_session = (LinearLayout) findViewById(R.id.ll_session);
        iv_friend = (ImageView) findViewById(R.id.iv_friend);
        tv_friend = (TextView) findViewById(R.id.tv_friend);
        ll_friend = (LinearLayout) findViewById(R.id.ll_friend);
        iv_news = (ImageView) findViewById(R.id.iv_news);
        tv_news = (TextView) findViewById(R.id.tv_news);
        ll_news = (LinearLayout) findViewById(R.id.ll_news);
        iv_me = (ImageView) findViewById(R.id.iv_me);
        iv_new_msg = (ImageView) findViewById(R.id.iv_new_msg);
        tv_me = (TextView) findViewById(R.id.tv_me);
        ll_me = (LinearLayout) findViewById(R.id.ll_me);

        include_title_iv_back.setVisibility(View.GONE);

        ll_session.setOnClickListener(this);
        ll_friend.setOnClickListener(this);
        ll_news.setOnClickListener(this);
        ll_me.setOnClickListener(this);
        title_right_text.setOnClickListener(this);

        checkMainTab(0);
    }

    private void showFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //隐藏全部
            hideAllFragment(transaction);
            transaction.show(fragment);
            transaction.commitAllowingStateLoss();
            Constans.mCurrentFragment = fragment;
        }
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (mSessionFragment != null) {
            transaction.hide(mSessionFragment);
        }
        if (mFriendFragment != null) {
            transaction.hide(mFriendFragment);
        }
        if (mNewsFragment != null) {
            transaction.hide(mNewsFragment);
        }
        if (mMeFragment != null) {
            transaction.hide(mMeFragment);
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (mSessionFragment == null && fragment instanceof SessionFragment) {
            mSessionFragment = (SessionFragment) fragment;
        }
        if (mFriendFragment == null && fragment instanceof FriendFragment) {
            mFriendFragment = (FriendFragment) fragment;
        }
        if (mNewsFragment == null && fragment instanceof NewsFragment) {
            mNewsFragment = (NewsFragment) fragment;
        }
        if (mMeFragment == null && fragment instanceof MeFragment) {
            mMeFragment = (MeFragment) fragment;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_session:
                checkMainTab(0);
                 break;
            case R.id.ll_friend:
                checkMainTab(1);
                break;
            case R.id.ll_news:
                checkMainTab(2);
                break;
            case R.id.ll_me:
                checkMainTab(3);
                break;
            case R.id.title_right_text:
                if(Constans.mCurrentFragment instanceof SessionFragment){

                }else if(Constans.mCurrentFragment instanceof  MeFragment){
                    startActivity(new Intent(this, SettingActivity.class));
                }
                break;
        }
    }

    /**
     * 切换主页选项卡
     *
     * @param index
     */
    private void checkMainTab(int index) {
        switch (index) {
            case 0:
                showFragment(mSessionFragment);

                iv_session.setImageResource(R.drawable.img_session_b);
                iv_friend.setImageResource(R.drawable.img_firend_a);
                iv_news.setImageResource(R.drawable.img_news_a);
                iv_me.setImageResource(R.drawable.img_me_a);

                tv_session.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_friend.setTextColor(getResources().getColor(R.color.color_black));
                tv_news.setTextColor(getResources().getColor(R.color.color_black));
                tv_me.setTextColor(getResources().getColor(R.color.color_black));

                include_title_text.setText(getString(R.string.str_main_title_session));

                title_right_text.setVisibility(View.VISIBLE);
                title_right_text.setText(getString(R.string.str_main_title_add));

                break;
            case 1:
                showFragment(mFriendFragment);

                iv_session.setImageResource(R.drawable.img_session_a);
                iv_friend.setImageResource(R.drawable.img_firend_b);
                iv_news.setImageResource(R.drawable.img_news_a);
                iv_me.setImageResource(R.drawable.img_me_a);

                tv_session.setTextColor(getResources().getColor(R.color.color_black));
                tv_friend.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_news.setTextColor(getResources().getColor(R.color.color_black));
                tv_me.setTextColor(getResources().getColor(R.color.color_black));

                include_title_text.setText(getString(R.string.str_main_title_friend));

                title_right_text.setVisibility(View.GONE);

                break;
            case 2:
                showFragment(mNewsFragment);

                iv_session.setImageResource(R.drawable.img_session_a);
                iv_friend.setImageResource(R.drawable.img_firend_a);
                iv_news.setImageResource(R.drawable.img_news_b);
                iv_me.setImageResource(R.drawable.img_me_a);

                tv_session.setTextColor(getResources().getColor(R.color.color_black));
                tv_friend.setTextColor(getResources().getColor(R.color.color_black));
                tv_news.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_me.setTextColor(getResources().getColor(R.color.color_black));

                include_title_text.setText(getString(R.string.str_main_title_news));

                title_right_text.setVisibility(View.GONE);

                break;
            case 3:
                showFragment(mMeFragment);

                iv_session.setImageResource(R.drawable.img_session_a);
                iv_friend.setImageResource(R.drawable.img_firend_a);
                iv_news.setImageResource(R.drawable.img_news_a);
                iv_me.setImageResource(R.drawable.img_me_b);

                tv_session.setTextColor(getResources().getColor(R.color.color_black));
                tv_friend.setTextColor(getResources().getColor(R.color.color_black));
                tv_news.setTextColor(getResources().getColor(R.color.color_black));
                tv_me.setTextColor(getResources().getColor(R.color.colorPrimary));

                include_title_text.setText(getString(R.string.str_main_title_me));

                title_right_text.setVisibility(View.VISIBLE);
                title_right_text.setText(getString(R.string.str_main_title_more));

                EventManager.post(EventManager.EVENT_TEST);

                break;
        }
    }

}
