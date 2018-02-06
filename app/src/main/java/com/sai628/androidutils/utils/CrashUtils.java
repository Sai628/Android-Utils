package com.sai628.androidutils.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * @author Sai
 * @ClassName: CrashUtils
 * @Description: 崩溃工具类
 * @date 06/02/2018 17:10
 */
public class CrashUtils implements Thread.UncaughtExceptionHandler
{
    private volatile static CrashUtils mInstance;

    private Thread.UncaughtExceptionHandler mHandler;

    private boolean mIsInitialized;
    private String crashDir;
    private String versionName;
    private int versionCode;


    private CrashUtils()
    {
    }


    /**
     * 获取单例
     * <p>在Application中初始化{@code CrashUtils.getInstance().init(this);}</p>
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>}</p>
     *
     * @return
     */
    public static CrashUtils getInstance()
    {
        if (mInstance == null)
        {
            synchronized (CrashUtils.class)
            {
                if (mInstance == null)
                {
                    mInstance = new CrashUtils();
                }
            }
        }

        return mInstance;
    }


    /**
     * 初始化
     *
     * @return {@code true}: 成功<br>{@code false}: 失败
     */
    public boolean init()
    {
        if (mIsInitialized)
        {
            return true;
        }

        File baseCache = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ?
                Utils.getContext().getExternalCacheDir() : Utils.getContext().getCacheDir();
        if (baseCache == null)
        {
            return false;
        }
        crashDir = baseCache.getPath() + File.separator + "crash" + File.separator;

        try
        {
            PackageInfo packageInfo = Utils.getContext().getPackageManager().getPackageInfo(Utils.getContext().getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }

        mHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

        mIsInitialized = true;
        return true;
    }


    @Override
    public void uncaughtException(Thread thread, final Throwable throwable)
    {
        String now = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        final String fullPath = crashDir + now + ".txt";
        if (!FileUtils.createOrExistsFile(fullPath))
        {
            return;
        }

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                PrintWriter pw = null;
                try
                {
                    pw = new PrintWriter(new FileWriter(fullPath, false));
                    pw.write(getCrashHead());
                    
                    throwable.printStackTrace(pw);
                    Throwable cause = throwable.getCause();
                    while (cause != null)
                    {
                        cause.printStackTrace(pw);
                        cause = cause.getCause();
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    CloseUtils.closeIO(pw);
                }
            }
        }).start();

        if (mHandler != null)
        {
            mHandler.uncaughtException(thread, throwable);
        }
    }


    private String getCrashHead()
    {
        return "\n************* Crash Log Head ****************" +
                "\nDevice Manufacturer: " + Build.MANUFACTURER +  // 设备厂商
                "\nDevice Model       : " + Build.MODEL +  // 设备型号
                "\nAndroid Version    : " + Build.VERSION.RELEASE +  // 系统版本
                "\nAndroid SDK        : " + Build.VERSION.SDK_INT +  // SDK版本
                "\nApp VersionName    : " + versionName +
                "\nApp VersionCode    : " + versionCode +
                "\n************* Crash Log Head ****************\n\n";
    }
}
