package com.sai628.androidutils;

import android.app.Application;

import com.sai628.androidutils.utils.Utils;


/**
 * @author Sai
 * @ClassName:
 * @Description:
 * @date 13/02/2017 18:58
 */
public class AppContext extends Application
{
    private static AppContext instance;


    public synchronized static AppContext getInstance()
    {
        return instance;
    }


    @Override
    public void onCreate()
    {
        super.onCreate();
        doInit();
    }


    private void doInit()
    {
        instance = this;
        Utils.init(instance);
    }
}
