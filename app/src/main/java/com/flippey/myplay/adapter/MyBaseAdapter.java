package com.flippey.myplay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.flippey.myplay.holder.BaseHolder;

import java.util.ArrayList;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/3 20:29
 * @ Desc        adapter的封装
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter{
    private ArrayList<T> mData;
    //泛型T
    public MyBaseAdapter(ArrayList<T> data) {
        this.mData = data;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder;
        if (convertView == null) {
            //在new holder的时候并不知道该填充什么数据,只能呢个提供抽象方法,给子类来返回具体对象
            //1.加载布局文件 2.初始化控件findViewById 3.设置标记tag
            holder = getHolder();
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        //4.根据数据刷新界面
        holder.setData(getItem(position));
        //注意这边的返回值,可能会导致空指针
        return holder.getRootView();
    }

    public abstract BaseHolder getHolder();
}
