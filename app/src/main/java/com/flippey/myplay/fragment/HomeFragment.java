package com.flippey.myplay.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.flippey.myplay.R;
import com.flippey.myplay.adapter.MyBaseAdapter;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = UiUtil.inflate(R.layout.item_home);
                holder = new ViewHolder();
                holder.tv = (TextView) convertView.findViewById(R.id.home_lv_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String item = getItem(position);
            holder.tv.setText(item);
            return convertView;

        }
    }

    static class ViewHolder {
        public TextView tv;
    }

}
