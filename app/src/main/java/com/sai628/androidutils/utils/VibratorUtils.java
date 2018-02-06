package com.sai628.androidutils.utils;

import android.content.Context;
import android.os.Vibrator;


/**
 * @author Sai
 * @ClassName: VibratorUtils
 * @Description: 振动工具类
 * @date 06/02/2018 12:15
 */
public class VibratorUtils
{
    private VibratorUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class");
    }


    /**
     * 振动
     * <p>调用该方法需要添加振动权限</p>
     * {@link android.Manifest.permission#VIBRATE}
     *
     * @param context      上下文
     * @param milliseconds 振动时长(单位:毫秒)
     */
    public static void vibrate(Context context, long milliseconds)
    {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(milliseconds);
    }


    /**
     * 指定以pattern的模式振动
     * <p>调用该方法需要添加振动权限</p>
     * {@link android.Manifest.permission#VIBRATE}
     *
     * @param context 上下文
     * @param pattern 振动模式数组.(数组参数意义：第一个参数为等待指定时间后开始振动，振动时间为第二个参数。后边的参数依次为等待振动和振动的时间)
     * @param repeat  指定pattern数组中从repeat索引开始的振动进行循环。-1表示只振动一次，非-1表示从 pattern的指定下标开始重复振动
     */
    public static void vibrate(Context context, long[] pattern, int repeat)
    {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern, repeat);
    }


    /**
     * 取消振动
     * <p>调用该方法需要添加振动权限</p>
     * {@link android.Manifest.permission#VIBRATE}
     *
     * @param context 上下文
     */
    public static void cancel(Context context)
    {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.cancel();
    }
}
