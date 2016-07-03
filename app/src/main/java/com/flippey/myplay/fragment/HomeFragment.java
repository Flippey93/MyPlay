package com.flippey.myplay.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.flippey.myplay.utils.UiUtil;
import com.flippey.myplay.view.LoadingPage;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/2 15:01
 * @ Desc        首页
 */
public class HomeFragment extends BaseFragment {
    //如果加载数据成功,则调用此方法,此方法是运行在主线程
    @Override
    public View onCreatSuccess() {
        TextView textView = new TextView(UiUtil.getContext());
        textView.setText(getClass().getSimpleName());
        textView.setTextColor(Color.BLACK);
        return textView;
    }

    //该方法就运行在子线程,可以执行耗时操作
    @Override
    public LoadingPage.ResultState initData() {
        //请求网络
        return LoadingPage.ResultState.STATE_SUCCESS;
    }
}
