package com.sai628.androidutils.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author Sai
 * @ClassName: ProcessUtils
 * @Description: 进程工具类
 * @date 02/03/2018 11:43
 */
public class ProcessUtils
{
    private ProcessUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * 获取后台服务进程
     *
     * @return 后台服务进程包名的集合
     */
    public static Set<String> getAllBackgroundProcesses()
    {
        ActivityManager am = (ActivityManager) Utils.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
        Set<String> result = new HashSet<>();
        for (ActivityManager.RunningAppProcessInfo info : infos)
        {
            Collections.addAll(result, info.pkgList);
        }

        return result;
    }


    /**
     * 杀死所有的后台服务进程
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES"/>}</p>
     *
     * @return 被暂时杀死的服务进程的包名集合
     */
    public static Set<String> killAllBackgroundProcesses()
    {
        ActivityManager am = (ActivityManager) Utils.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
        Set<String> result = new HashSet<>();
        for (ActivityManager.RunningAppProcessInfo info : infos)
        {
            for (String pkg : info.pkgList)
            {
                am.killBackgroundProcesses(pkg);
                result.add(pkg); // 将杀死的进程包名先保存起来
            }
        }

        // 重新获取运行中的进程信息, 将还存活的进程包名从结果中移除
        infos = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos)
        {
            for (String pkg : info.pkgList)
            {
                result.remove(pkg);
            }
        }
        return result;
    }


    /**
     * 杀死后台服务进程
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES"/>}</p>
     *
     * @param packageName 待杀死进程的包名
     * @return {@code true}: 杀死成功<br>{@code false}: 杀死失败
     */
    public static boolean killBackgroundProcesses(String packageName)
    {
        if (StringUtils.isSpace(packageName))
        {
            return false;
        }

        ActivityManager am = (ActivityManager) Utils.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
        if (infos == null || infos.size() == 0)
        {
            return true;
        }

        for (ActivityManager.RunningAppProcessInfo info : infos)
        {
            if (Arrays.asList(info.pkgList).contains(packageName))
            {
                am.killBackgroundProcesses(packageName);
            }
        }

        infos = am.getRunningAppProcesses();
        if (infos == null || infos.size() == 0)
        {
            return true;
        }
        for (ActivityManager.RunningAppProcessInfo info : infos)
        {
            // 若杀死后, 进程又重新存活, 则返回false
            if (Arrays.asList(info.pkgList).contains(packageName))
            {
                return false;
            }
        }

        return true;
    }
}
