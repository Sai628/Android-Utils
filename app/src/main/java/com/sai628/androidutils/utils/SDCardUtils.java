package com.sai628.androidutils.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;


/**
 * @author Sai
 * @ClassName: SDCardUtils
 * @Description: SD卡工具类
 * @date 29/01/2018 19:40
 */
public class SDCardUtils
{
    private SDCardUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * 判断SD卡是否可用
     *
     * @return {@code true}: 可用<br/>{@code false}: 不可用
     */
    public static boolean isSDCardEnable()
    {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }


    /**
     * 获取SD卡路径
     * <p>先用shell去获取, shell失败后再用普通方法去获取. 一般是/storage/emulated/0/</p>
     *
     * @return SD卡路径. 若SD卡不可用时, 将返回null
     */
    public static String getSDCardPath()
    {
        if (!isSDCardEnable())
        {
            return null;
        }

        String cmd = "cat /proc/mounts";
        Runtime runtime = Runtime.getRuntime();
        BufferedReader reader = null;
        try
        {
            Process process = runtime.exec(cmd);
            reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(process.getInputStream())));
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (line.contains("sdcard") && line.contains(".android_secure"))
                {
                    String[] strArray = line.split(" ");
                    if (strArray.length >= 5)
                    {
                        return strArray[1].replace("/.android_secure", "") + File.separator;
                    }
                }
                if (process.waitFor() != 0 && process.exitValue() == 1)
                {
                    break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            CloseUtils.closeIO(reader);
        }

        return Environment.getExternalStorageDirectory().getPath() + File.separator;
    }


    /**
     * 获取SD卡的data路径
     *
     * @return SD卡的data路径. 若SD卡不可用时, 将返回null
     */
    public static String getDataPath()
    {
        if (!isSDCardEnable())
        {
            return null;
        }

        return Environment.getExternalStorageDirectory().getPath() + File.separator + "data" + File.separator;
    }


    /**
     * 获取SD卡剩余空间
     *
     * @return 合适显示的SD卡剩余空间字符串. 若SD卡不可用时, 将返回空字符串
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static String getFreeSpace()
    {
        if (!isSDCardEnable())
        {
            return "";
        }

        StatFs fs = new StatFs(getSDCardPath());
        long availableBlocks = fs.getAvailableBlocksLong();
        long blockSize = fs.getBlockSizeLong();
        return ConvertUtils.byte2FitMemorySize(availableBlocks * blockSize);
    }


    /**
     * 获取SD卡信息
     *
     * @return sd卡信息字符串. 若SD卡不可用时, 将返回null
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static String getSDCardInfo()
    {
        if (!isSDCardEnable())
        {
            return "";
        }

        SDCardInfo info = new SDCardInfo();
        StatFs sf = new StatFs(Environment.getExternalStorageDirectory().getPath());
        info.isExist = true;
        info.totalBlocks = sf.getBlockCountLong();
        info.blockByteSize = sf.getBlockSizeLong();
        info.availableBlocks = sf.getAvailableBlocksLong();
        info.availableBytes = sf.getAvailableBytes();
        info.totalBytes = sf.getTotalBytes();
        info.freeBlocks = sf.getFreeBlocksLong();
        info.freeBytes = sf.getFreeBytes();

        return info.toString();
    }


    private static class SDCardInfo
    {
        boolean isExist;
        long totalBlocks;
        long blockByteSize;
        long availableBlocks;
        long availableBytes;
        long totalBytes;
        long freeBlocks;
        long freeBytes;


        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            sb.append("isExists=").append(isExist).append("\n");
            sb.append("totalBlocks=").append(totalBlocks).append("\n");
            sb.append("blockByteSize=").append(blockByteSize).append("\n");
            sb.append("availableBlocks=").append(availableBlocks).append("\n");
            sb.append("availableBytes=").append(availableBytes).append("\n");
            sb.append("totalBytes=").append(totalBytes).append("\n");
            sb.append("freeBlocks=").append(freeBlocks).append("\n");
            sb.append("freeBytes=").append(freeBytes);

            return sb.toString();
        }
    }
}
