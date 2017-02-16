package com.example.a60440.collegestudent.application;

import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.support.multidex.MultiDexApplication;

import io.rong.imkit.RongIM;

/**
 * Created by 60440 on 2017/2/15.
 */

public class App extends MultiDexApplication {
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
        context=getApplicationContext();
        handler = new Handler(getMainLooper());
        mainThreadId = Process.myTid();
    }
    public static Context getContext(){
        return context;
    }
    public static Handler getHandler(){
        return handler;
    }
    public static int getMainThreadId(){
        return mainThreadId;
    }
}
