package com.sai628.androidutils.utils;

import android.content.Context;


/**
 * @author Sai
 * @ClassName: Utils
 * @Description: 工具初始化类
 * @date 13/02/2017 18:52
 */
public class Utils
{
    private static Context context;


    private Utils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * 初始化工具类. 请在自定义的 Application 类的入口处调用该初始化方法
     *
     * @param context
     */
    public static void init(Context context)
    {
        Utils.context = context.getApplicationContext();
    }


    /**
     * 获取 ApplicationContext
     *
     * @return
     */
    public static Context getContext()
    {
        if (context != null)
        {
            return context;
        }
        throw new NullPointerException("You should call init() first.");
    }
}
