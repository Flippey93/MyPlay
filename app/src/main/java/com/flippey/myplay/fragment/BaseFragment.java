package com.flippey.myplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flippey.myplay.utils.UiUtil;
import com.flippey.myplay.view.LoadingPage;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/2 15:00
 * @ Desc        fragment基类
 */
public abstract class BaseFragment extends Fragment {

    private LoadingPage mLoadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
       /*TextView textView = new TextView(UiUtil.getContext());
        textView.setText(getClass().getSimpleName());
        textView.setTextColor(Color.BLACK);*/
       mLoadingPage = new LoadingPage(UiUtil.getContext()) {
           @Override
           public View onCreatSuccessView() {
               return onCreatSuccess();
           }

           @Override
           public ResultState onLoad() {
               return initData();
           }
       };
        return mLoadingPage;
    }
    //加载成功的布局,必须由子类来实现
    public abstract View onCreatSuccess();
    //初始化网络加载数据,必须由子类实现
    public abstract LoadingPage.ResultState initData();

    //开始加载数据
    public void loadData() {
        if (mLoadingPage != null) {
            mLoadingPage.loadData();
        }
    }
}
