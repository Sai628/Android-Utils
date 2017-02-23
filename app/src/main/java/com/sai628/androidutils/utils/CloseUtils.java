package com.sai628.androidutils.utils;

import java.io.Closeable;
import java.io.IOException;


/**
 * @author Sai
 * @ClassName: CloseUtils
 * @Description: 关闭工具类
 * @date 23/02/2017 17:10
 */
public class CloseUtils
{
    private CloseUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class");
    }


    /**
     * 关闭IO
     *
     * @param closeables
     */
    public static void closeIO(Closeable... closeables)
    {
        if (closeables == null)
        {
            return;
        }

        for (Closeable closeable : closeables)
        {
            if (closeable != null)
            {
                try
                {
                    closeable.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 安静的关闭IO
     *
     * @param closeables
     */
    public static void closeIOQuietly(Closeable... closeables)
    {
        if (closeables == null)
        {
            return;
        }

        for (Closeable closeable : closeables)
        {
            if (closeable != null)
            {
                try
                {
                    closeable.close();
                }
                catch (IOException e)
                {
                }
            }
        }
    }
}
