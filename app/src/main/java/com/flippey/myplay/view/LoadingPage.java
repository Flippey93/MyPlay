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
public abstract class LoadingPage extends FrameLayout {
    private static final int STATE_LOAD_UNDO = 1; //为加载
    private static final int STATE_LOAD_LOADING = 2; //正在加载
    private static final int STATE_LOAD_ERROR = 3; //加载失败
    private static final int STATE_LOAD_EMPTY = 4; //加载为空
    private static final int STATE_LOAD_SUCCESS = 5; //加载成功

    private int mCurrentState = STATE_LOAD_UNDO;  //当前状态
    private View mLoadingPage;
    private View mErrorPage;
    private View mEmptyPage;
    private View mSuccessPage;

    public LoadingPage(Context context) {
        this(context, null);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
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
        //当前状态是加载成功时显示的页面
        if (mSuccessPage == null && mCurrentState == STATE_LOAD_SUCCESS) {
            mSuccessPage = onCreatSuccessView();
            if (mSuccessPage != null) {
                addView(mSuccessPage);
            }
        }
        if (mSuccessPage != null) {
            mSuccessPage.setVisibility(mCurrentState == STATE_LOAD_SUCCESS ? VISIBLE : GONE);
        }
    }

    //开始加载数据
    public void loadData() {
        //如果当前没有加载,就开始加载数据
        if (mCurrentState != STATE_LOAD_LOADING) {
            mCurrentState = STATE_LOAD_LOADING;
            new Thread() {
                @Override
                public void run() {
                    //暴露抽象方法给子类实现
                    final ResultState resultState = onLoad();
                    //在主线程更新ui
                    UiUtil.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (resultState != null) {
                                //加载结束后,将返回的状态更新给当前的状态
                                mCurrentState = resultState.getState();
                                //根据最新的状态刷新页面
                                showRightPage();
                            }
                        }
                    });
                }
            }.start();
        }
    }

    //加载成功后显示的布局,必须由调用者来实现
    public abstract View onCreatSuccessView();

    //加载网路数据,添加返回值,表示请求网络结束后的状态
    public abstract ResultState onLoad();

    //定义枚举表示返回的状态,枚举类似于一个个对象的集合
    public enum ResultState {
        STATE_SUCCESS(STATE_LOAD_SUCCESS),
        STATE_EMPTY(STATE_LOAD_EMPTY),
        STATE_ERROR(STATE_LOAD_ERROR);

        private int state;

        private ResultState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }
}
