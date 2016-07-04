package com.flippey.myplay.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flippey.myplay.R;
import com.flippey.myplay.utils.UiUtil;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/4 19:34
 * @ Desc        加载更多布局
 */
public class MoreHolder extends BaseHolder<Integer>{
    //加载更多的几种状态
    //1.正在加载..2.加载更多失败3.没有更多数据(隐藏更多布局)
    public static final int STATE_LOADMORE_ING = 0;
    public static final int STATE_LOADMORE_ERROR = 1;
    public static final int STATE_LOADMORE_NOMOER = 2;
    private LinearLayout mLoadmore;
    private TextView mLoaderror;

    public MoreHolder(boolean hasMore) {
        //如果有更多数据,状态为ing,将状态传递给父类的data.父类会同时刷新界面
        setData(hasMore?STATE_LOADMORE_ING:STATE_LOADMORE_NOMOER);
    }
    @Override
    public View initView() {
        View view = UiUtil.inflate(R.layout.item_more);
        mLoadmore = (LinearLayout) view.findViewById(R.id.ll_load_more);
        mLoaderror = (TextView) view.findViewById(R.id.tv_load_error);
        return view;
    }

    @Override
    public void refreshView(Integer data) {
        switch (data) {
            case STATE_LOADMORE_ING:
                //显示加载更多
                mLoadmore.setVisibility(View.VISIBLE);
                mLoaderror.setVisibility(View.GONE);
                break;
            case STATE_LOADMORE_NOMOER:
                //隐藏加载更多
                mLoaderror.setVisibility(View.GONE);
                mLoadmore.setVisibility(View.GONE);
                break;
            case STATE_LOADMORE_ERROR:
                //显示加载失败的布局
                break;
        }
    }
}
