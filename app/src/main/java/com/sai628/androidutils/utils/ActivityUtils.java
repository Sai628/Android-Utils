package com.sai628.androidutils.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Sai
 * @ClassName: ActivityUtils
 * @Description: Activity 工具类
 * @date 14/02/2017 14:42
 */
public class ActivityUtils
{
    private ActivityUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * 判断是否存在某 Activity
     *
     * @param packageName 包名
     * @param className   activity 的全路径类名(包含所在的包路径)
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isActivityExists(String packageName, String className)
    {
        Intent intent = new Intent();
        intent.setClassName(packageName, className);

        return !(Utils.getContext().getPackageManager().resolveActivity(intent, 0) == null ||
                intent.resolveActivity(Utils.getContext().getPackageManager()) == null ||
                Utils.getContext().getPackageManager().queryIntentActivities(intent, 0).size() == 0);
    }


    /**
     * 打开 Activity
     *
     * @param packageName 包名
     * @param className   activity 的全路径类名(包含所在的包路径)
     */
    public static void launchActivity(String packageName, String className)
    {
        launchActivity(packageName, className, null);
    }


    /**
     * 打开 Activity
     *
     * @param packageName 包名
     * @param className   activity 的全路径类名(包含所在的包路径)
     * @param bundle
     */
    public static void launchActivity(String packageName, String className, Bundle bundle)
    {
        Intent intent = IntentUtils.getComponentIntent(packageName, className, bundle);
        Utils.getContext().startActivity(intent);
    }


    /**
     * 获取某包下的 Launcher Activity
     *
     * @param packageName 包名
     * @return {@code String} Launcher Activity 的全路径类名. 若不存在时, 返回 {@code null}
     */
    public static String getLauncherActivity(String packageName)
    {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PackageManager pm = Utils.getContext().getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo info : infos)
        {
            if (info.activityInfo.packageName.equals(packageName))
            {
                return info.activityInfo.name;
            }
        }

        return null;
    }


    /**
     * 获取栈顶 Activity
     * @return {@code Activity}
     */
    public static Activity getTopActivity()
    {
        try
        {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);

            Map activities = null;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            {
                activities = (HashMap) activitiesField.get(activityThread);
            }
            else
            {
                activities = (ArrayMap) activitiesField.get(activityThread);
            }

            for (Object activityRecord : activities.values())
            {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord))
                {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    return (Activity) activityField.get(activityRecord);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
