package com.flippey.myplay.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.flippey.myplay.adapter.MyBaseAdapter;
import com.flippey.myplay.holder.BaseHolder;
import com.flippey.myplay.holder.HomeHolder;
import com.flippey.myplay.utils.UiUtil;
import com.flippey.myplay.view.LoadingPage;

import java.util.ArrayList;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/2 15:01
 * @ Desc        首页
 */
public class HomeFragment extends BaseFragment {
    private ArrayList<String> mData;

    //如果加载数据成功,则调用此方法,此方法是运行在主线程
    @Override
    public View onCreatSuccess() {
        ListView view = new ListView(UiUtil.getContext());
        view.setAdapter(new HomeAdapter(mData));
        return view;
    }

    //该方法就运行在子线程,可以执行耗时操作
    @Override
    public LoadingPage.ResultState initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add("数据测试" + i);
        }
        //请求网络
        return LoadingPage.ResultState.STATE_SUCCESS;
    }

    class HomeAdapter extends MyBaseAdapter<String> {

        public HomeAdapter(ArrayList<String> data) {
            super(data);
        }

        @Override
        public BaseHolder getHolder() {
            return new HomeHolder();
        }

        /*@Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                //1.加载布局文件
                convertView = UiUtil.inflate(R.layout.item_home);
                holder = new ViewHolder();
                //2.初始化控件
                holder.tv = (TextView) convertView.findViewById(R.id.home_lv_tv);
                //3.设置标记
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            //4.根据数据刷新界面
            String item = getItem(position);
            holder.tv.setText(item);
            return convertView;
        }*/
    }

    static class ViewHolder {
        public TextView tv;
    }

}
