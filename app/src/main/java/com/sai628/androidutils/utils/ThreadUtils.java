package com.sai628.androidutils.utils;

/**
 * @author Sai
 * @ClassName: ThreadUtils
 * @Description: 线程工具类
 * @date 27/02/2018 12:21
 */
public class ThreadUtils
{
    private ThreadUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class");
    }


    /**
     * 安全地进行休眠(方法内部会捕获异常)
     *
     * @param time 休眠时间(单位: 毫秒)
     */
    public static void safeSleep(long time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
