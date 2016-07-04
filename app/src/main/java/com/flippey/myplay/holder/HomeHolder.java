package com.flippey.myplay.holder;

import android.view.View;
import android.widget.TextView;

import com.flippey.myplay.R;
import com.flippey.myplay.utils.UiUtil;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/4 18:54
 * @ Desc        首页Holder
 */
public class HomeHolder extends BaseHolder<String>{

    private TextView mTvContent;

    @Override
    public View initView() {
        //1.加载布局
        View view = UiUtil.inflate(R.layout.item_home);
        mTvContent = (TextView) view.findViewById(R.id.home_lv_tv);
        return view;
    }

    @Override
    public void refreshView(String data) {
        mTvContent.setText(data);
    }

}
