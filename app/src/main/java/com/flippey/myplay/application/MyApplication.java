package com.flippey.myplay.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/2 11:35
 * @ Desc        application
 */
public class MyApplication extends Application{

    private static Context mContext;
    private static Handler mHandler;
    private static int mTid;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    public static Context getmContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static int getTid() {
        return mTid;
    }

    private void init() {
        mContext = getApplicationContext();
        mHandler = new Handler();
        mTid = android.os.Process.myTid();
    }
}
