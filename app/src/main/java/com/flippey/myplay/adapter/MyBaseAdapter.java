package com.flippey.myplay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.flippey.myplay.holder.BaseHolder;
import com.flippey.myplay.holder.MoreHolder;

import java.util.ArrayList;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/3 20:29
 * @ Desc        adapter的封装
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter{
    //声明两种布局类型
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_MORE = 1;
    private ArrayList<T> mData;
    //泛型T
    public MyBaseAdapter(ArrayList<T> data) {
        this.mData = data;
    }
    @Override
    public int getCount() {
        //加1表示增加更多布局
        return mData.size() + 1;
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //返回布局类型的个数
    @Override
    public int getViewTypeCount() {
        //返回两种类型,分别为普通布局,以及记载更多布局
        return 2;
    }
    //返回当前位置要展示的布局类型
    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            //这是最后一个位置,返回加载更多
            return TYPE_MORE;
        } else {
            return TYPE_NORMAL;
        }
    }
    //获取listview的布局类型,子类可以重写该方法来更改返回的布局类型
    public int getInnerType() {
        return TYPE_NORMAL; //默认返回普通类型
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder;
        if (convertView == null) {
            //在new holder的时候并不知道该填充什么数据,只能呢个提供抽象方法,给子类来返回具体对象
            //1.加载布局文件 2.初始化控件findViewById 3.设置标记tag
            //判断加载什么布局
            if (getItemViewType(position) == TYPE_MORE) {
                //加载更多
                holder = new MoreHolder(hasMore());
            } else {
                holder = getHolder();
            }
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        //4.根据数据刷新界面
        if (getItemViewType(position) == TYPE_MORE) {

        } else {
            holder.setData(getItem(position));
        }
        //注意这边的返回值,可能会导致空指针
        return holder.getRootView();
    }
    //子类可以重写此方法来决定是否可以加载更多
    public boolean hasMore() {
        return true;//默认都是有更多数据的
    }
    //返回当前页面的holder对象,必须由子类实现
    public abstract BaseHolder getHolder();
}
