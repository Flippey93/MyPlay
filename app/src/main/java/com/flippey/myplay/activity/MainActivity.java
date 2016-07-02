package com.flippey.myplay.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flippey.myplay.R;
import com.flippey.myplay.fragment.BaseFragment;
import com.flippey.myplay.fragment.FragmentFactory;
import com.flippey.myplay.utils.UiUtil;
import com.flippey.myplay.view.PagerTab;

public class MainActivity extends BaseActivity {

    private PagerTab mPagerTab;
    private ViewPager mViewPager;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mPagerTab = (PagerTab) findViewById(R.id.pager_tab);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mAdapter = new MainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        //将指针和viwepager绑定在一起
        mPagerTab.setViewPager(mViewPager);

    }
    //viewpager的页面是fragment的话就用fragmentpageradapter
    class MainAdapter extends FragmentPagerAdapter {
        private final String[] mTabNames;
        public MainAdapter(FragmentManager fm) {
            super(fm);
            //加载页面标题数组
            mTabNames = UiUtil.getStringArray(R.array.tab_names);
        }
        //返回页签的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabNames[position];
        }

        //返回当前页面位置的fragment对象
        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment = FragmentFactory.createFragment(position);
            return fragment;
        }
        //fragment的数量
        @Override
        public int getCount() {
            return mTabNames.length;
        }
    }

}
