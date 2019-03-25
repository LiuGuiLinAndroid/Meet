package com.liuguilin.meet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuguilin.meet.R;
import com.liuguilin.meet.base.BaseFragment;

/**
 * FileName: FriendFragment
 * Founder: 刘桂林
 * Create Date: 2019/3/3 13:41
 * Profile: 朋友
 */
public class FriendFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend,null);
        return view;
    }
}
