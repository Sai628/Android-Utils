package com.sai628.androidutils.utils;

import android.util.Log;

import com.sai628.androidutils.BuildConfig;


/**
 * @author Sai
 * @ClassName: LogUtils
 * @Description: 日志工具类
 * @date 27/02/2018 16:38
 */
public class LogUtils
{
    private LogUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class");
    }


    /**
     * 是否开启日志输出
     */
    public static boolean ENABLE_LOG = true;


    /**
     * 输出Verbose日志消息
     *
     * @param tag 标签(用于标识日志消息的来源, 它通常标识日志调用时的class或Activity)
     * @param msg 日志消息
     */
    public static void v(String tag, String msg)
    {
        if (ENABLE_LOG)
        {
            tag = wrapTag(tag);
            Log.v(tag, msg);
        }
    }


    /**
     * 输出Debug日志消息
     *
     * @param tag 标签(用于标识日志消息的来源, 它通常标识日志调用时的class或Activity)
     * @param msg 日志消息
     */
    public static void d(String tag, String msg)
    {
        if (ENABLE_LOG)
        {
            tag = wrapTag(tag);
            Log.d(tag, msg);
        }
    }


    /**
     * 输出Info日志消息
     *
     * @param tag 标签(用于标识日志消息的来源, 它通常标识日志调用时的class或Activity)
     * @param msg 日志消息
     */
    public static void i(String tag, String msg)
    {
        if (ENABLE_LOG)
        {
            tag = wrapTag(tag);
            Log.i(tag, msg);
        }
    }


    /**
     * 输出Warn日志消息
     *
     * @param tag 标签(用于标识日志消息的来源, 它通常标识日志调用时的class或Activity)
     * @param msg 日志消息
     */
    public static void w(String tag, String msg)
    {
        if (ENABLE_LOG)
        {
            tag = wrapTag(tag);
            Log.w(tag, msg);
        }
    }


    /**
     * 输出Error日志消息
     *
     * @param tag 标签(用于标识日志消息的来源, 它通常标识日志调用时的class或Activity)
     * @param msg 日志消息
     */
    public static void e(String tag, String msg)
    {
        if (ENABLE_LOG)
        {
            tag = wrapTag(tag);
            Log.e(tag, msg);
        }
    }


    /**
     * 输出Verbose日志消息
     *
     * @param object 标签对象, 将使用对象的类名称字符串作为标签(用于标识日志消息的来源, 它通常标识日志调用时的class或Activity)
     * @param msg    日志消息
     */
    public static void v(Object object, String msg)
    {
        v(object.getClass().getSimpleName(), msg);
    }


    /**
     * 输出Debug日志消息
     *
     * @param object 标签对象, 将使用对象的类名称字符串作为标签(用于标识日志消息的来源, 它通常标识日志调用时的class或Activity)
     * @param msg    日志消息
     */
    public static void d(Object object, String msg)
    {
        d(object.getClass().getSimpleName(), msg);
    }


    /**
     * 输出Info日志消息
     *
     * @param object 标签对象, 将使用对象的类名称字符串作为标签(用于标识日志消息的来源, 它通常标识日志调用时的class或Activity)
     * @param msg    日志消息
     */
    public static void i(Object object, String msg)
    {
        i(object.getClass().getSimpleName(), msg);
    }


    /**
     * 输出Warn日志消息
     *
     * @param object 标签对象, 将使用对象的类名称字符串作为标签(用于标识日志消息的来源, 它通常标识日志调用时的class或Activity)
     * @param msg    日志消息
     */
    public static void w(Object object, String msg)
    {
        w(object.getClass().getSimpleName(), msg);
    }


    /**
     * 输出Error日志消息
     *
     * @param object 标签对象, 将使用对象的类名称字符串作为标签(用于标识日志消息的来源, 它通常标识日志调用时的class或Activity)
     * @param msg    日志消息
     */
    public static void e(Object object, String msg)
    {
        e(object.getClass().getSimpleName(), msg);
    }


    private static String wrapTag(String tag)
    {
        return BuildConfig.APPLICATION_ID + " LOG " + (tag != null ? tag : "");
    }
}
