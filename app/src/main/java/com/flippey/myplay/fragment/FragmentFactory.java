package com.flippey.myplay.fragment;

import java.util.HashMap;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/2 14:59
 * @ Desc        生产fragment的工厂
 */
public class FragmentFactory {
    //用集合将fragment保存起来
    private static HashMap<Integer, BaseFragment> mFragment = new HashMap<>();
    public static BaseFragment createFragment(int pos) {
        //先从集合中取出,如果没有再new
        BaseFragment fragment = mFragment.get(pos);
        if (fragment == null) {
            switch (pos) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AppFragment();
                    break;
                case 2:
                    fragment = new GameFragment();
                    break;
                case 3:
                    fragment = new SubjectFragment();
                    break;
                case 4:
                    fragment = new RecommendFragment();
                    break;
                case 5:
                    fragment = new CategoryFragment();
                    break;
                case 6:
                    fragment = new HotFragment();
                    break;
                default:
                    break;
            }
            //将fragment保存在集合中
            mFragment.put(pos, fragment);
        }
        return fragment;
    }
}
