package com.liuguilin.meet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuguilin.meet.R;
import com.liuguilin.meet.base.BaseFragment;

/**
 * FileName: MeFragment
 * Founder: 刘桂林
 * Create Date: 2019/3/3 13:41
 * Profile: 我
 */
public class MeFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,null);
        return view;
    }

}
