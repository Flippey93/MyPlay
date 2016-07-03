package com.flippey.myplay.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flippey.myplay.R;
import com.flippey.myplay.fragment.BaseFragment;
import com.flippey.myplay.fragment.FragmentFactory;
import com.flippey.myplay.utils.UiUtil;
import com.flippey.myplay.view.PagerTab;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles
        .ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    public  String[] mTabNames;
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
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new MainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        final MagicIndicator magic_indicator1 = (MagicIndicator) findViewById(R.id.magic_indicator);
        final CommonNavigator commonNavigator1 = new CommonNavigator(this);
        commonNavigator1.setFollowTouch(false);
        commonNavigator1.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTabNames == null ? 0 : mTabNames.length;
            }
            @Override
            public IPagerTitleView getItemView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setText(mTabNames[index]);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }
            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setWrapContentMode(true);
                List<String> colorList = new ArrayList<String>();
                colorList.add("#ff4a42");
                colorList.add("#fcde64");
                colorList.add("#73e8f4");
                colorList.add("#76b0ff");
                colorList.add("#c683fe");
                indicator.setColorList(colorList);
                return indicator;
            }
        });
        magic_indicator1.setNavigator(commonNavigator1);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magic_indicator1.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }
            @Override
            public void onPageSelected(int position) {
                magic_indicator1.onPageSelected(position);
                BaseFragment fragment = FragmentFactory.createFragment(position);
                fragment.loadData();
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                magic_indicator1.onPageScrollStateChanged(state);
            }
        });
        mViewPager.setCurrentItem(0);
    }

    //viewpager的页面是fragment的话就用fragmentpageradapter
    class MainAdapter extends FragmentPagerAdapter {
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
