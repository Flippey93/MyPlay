package com.flippey.myplay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/3 20:29
 * @ Desc        adapter的封装
 */
public class MyBaseAdapter<T> extends BaseAdapter{
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
        return null;
    }
}
