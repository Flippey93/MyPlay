package com.flippey.myplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.flippey.myplay.R;
import com.flippey.myplay.utils.UiUtil;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/2 16:54
 * @ Desc        加载页面,根据当前状态显示不同页面的自定义控件
 * 分别是:未加载,加载中,加载失败,数据为空,加载成功
 */
public class LoadingPage extends FrameLayout{
    private static final int STATE_LOAD_UNDO = 1; //为加载
    private static final int STATE_LOAD_LOADING = 2; //正在加载
    private static final int STATE_LOAD_ERROR = 3; //加载失败
    private static final int STATE_LOAD_EMPTY = 4; //加载为空
    private static final int STATE_LOAD_SUCCESS = 5; //加载成功

    private int mCurrentState = STATE_LOAD_UNDO;  //当前状态
    private View mLoadingPage;
    private View mErrorPage;
    private View mEmptyPage;

    public LoadingPage(Context context) {
        this(context,null);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    //初始化布局
    private void initView() {
        //初始化加载中的布局
        if (mLoadingPage == null) {
            mLoadingPage = UiUtil.inflate(R.layout.page_loading);
            addView(mLoadingPage);
        }
        //初始化加载失败布局
        if (mErrorPage == null) {
            mErrorPage = UiUtil.inflate(R.layout.page_error);
            addView(mErrorPage);
        }
        //初始化加载无数据布局
        if (mEmptyPage == null) {
            mEmptyPage = UiUtil.inflate(R.layout.page_empty);
            addView(mEmptyPage);
        }
        showRightPage();
    }
    //根据当前状态,决定要显示的布局
    private void showRightPage() {
        mLoadingPage.setVisibility((mCurrentState == STATE_LOAD_UNDO || mCurrentState ==
                STATE_LOAD_LOADING) ? VISIBLE : GONE);
        mErrorPage.setVisibility((mCurrentState == STATE_LOAD_ERROR) ? VISIBLE : GONE);
        mEmptyPage.setVisibility((mCurrentState == STATE_LOAD_EMPTY) ? VISIBLE : GONE);
    }
}
