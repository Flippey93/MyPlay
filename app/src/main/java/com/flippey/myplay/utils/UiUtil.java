package com.flippey.myplay.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;
import android.view.View;

import com.flippey.myplay.application.MyApplication;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/2 11:39
 * @ Desc        公共工具类
 */
public class UiUtil {

    public static Context getContext() {
        return MyApplication.getmContext();
    }

    public static Handler getHandler() {
        return MyApplication.getHandler();
    }

    public static int getmMainThreadId() {
        return MyApplication.getTid();
    }

    ///////////////////////加载资源文件////////////////////////////
    //获取res文件下面values的字符串
    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }
    //获取字符串数组
    public static String[] getStringArray(int id) {
        return getContext().getResources().getStringArray(id);
    }
    //获取图片
    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }
    //获取颜色
    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    //根据id获取颜色状态选择器
    public static ColorStateList getColorStateList(int id) {
        return getContext().getResources().getColorStateList(id);
    }
    //获取尺寸,直接返回具体的像素值
    public static int getDimen(int id) {
        return getContext().getResources().getDimensionPixelSize(id);
    }

    ///////////////////////dip和px之间转换//////////////////////////////
    public static int dip2px(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }
    public static float px2dip(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    /////////////////////////加载布局文件////////////////////////////
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }

    ///////////////////////////////////////////////////
    //判断项目是否运行在主线程,防止在子线程里面进行UI更新
    public static boolean isRunOnUiThread() {
        //获取当前线程id
        int myTid = Process.myTid();
        if (myTid == getmMainThreadId()) {
            return true;
        }
        return false;
    }
    //让当前线程运行在主线程
    public static void runOnUiThread(Runnable r) {
        if (isRunOnUiThread()) {
            //如果当前是主线程,则直接运行
            r.run();
        } else {
            //如果是子线程,则利用handler运行在主线程,把runnable对象post到消息队列
            getHandler().post(r);
        }
    }


}
