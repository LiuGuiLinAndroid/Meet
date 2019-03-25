package com.liuguilin.meet.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuguilin.meet.R;
import com.liuguilin.meet.base.BaseFragment;

/**
 * FileName: SessionFragment
 * Founder: 刘桂林
 * Create Date: 2019/3/3 13:40
 * Profile: 会话
 */
public class SessionFragment extends BaseFragment {

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_session,null);
        return view;
    }
}
