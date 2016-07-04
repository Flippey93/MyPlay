package com.flippey.myplay.holder;

import android.view.View;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/4 17:23
 * @ Desc        holder基类
 */
public abstract class BaseHolder<T> {
    //item的根布局
    private View mRootView;
    private T data;
    //当new BaseHolder对象的时候就会加载布局,初始化控件,设置tag
    public BaseHolder() {
        mRootView = initView();
        mRootView.setTag(this);
    }
    //1.初始化布局,父类不知道该填充什么布局
    //2.初始化控件
    public abstract View initView();

    //对外提供获取view的方法
    public View getRootView() {
        return mRootView;
    }

    //对外暴露方法设置item数据,并立马刷新数据
    public void setData(T data) {
        this.data = data;
        refreshView(data);
    }

    //获取item的数据
    public T getData() {
        return data;
    }
    //根据数据刷新界面
    public abstract void refreshView(T data);
}
