package com.flippey.myplay.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flippey.myplay.utils.UiUtil;
import com.flippey.myplay.view.LoadingPage;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/2 15:00
 * @ Desc        fragment基类
 */
public class BaseFragment extends Fragment {

    private LoadingPage mLoadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
       TextView textView = new TextView(UiUtil.getContext());
        textView.setText(getClass().getSimpleName());
        textView.setTextColor(Color.BLACK);
       // mLoadingPage = new LoadingPage(UiUtil.getContext());
        return textView;
    }
}
