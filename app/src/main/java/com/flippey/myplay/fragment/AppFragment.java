package com.flippey.myplay.fragment;

import android.view.View;

import com.flippey.myplay.view.LoadingPage;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/2 15:01
 * @ Desc        应用
 */
public class AppFragment extends BaseFragment {
    @Override
    public View onCreatSuccess() {
        return null;
    }

    @Override
    public LoadingPage.ResultState initData() {
        return LoadingPage.ResultState.STATE_ERROR;
    }
}
