package com.sai628.androidutils.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Sai
 * @ClassName: AppUtils
 * @Description: App相关工具类
 * @date 28/02/2018 16:21
 */
public class AppUtils
{
    private AppUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * 安装app(支持6.0)
     *
     * @param context  上下文
     * @param filePath 文件路径
     */
    public static void installApp(Context context, String filePath)
    {
        installApp(context, FileUtils.getFileByPath(filePath));
    }


    /**
     * 安装app(支持6.0)
     *
     * @param context 上下文
     * @param file    文件
     */
    public static void installApp(Context context, File file)
    {
        if (!FileUtils.isFileExists(file))
        {
            return;
        }

        context.startActivity(IntentUtils.getInstallAppIntent(file));
    }


    /**
     * 安装App(支持6.0)
     *
     * @param activity    activity
     * @param filePath    文件路径
     * @param requestCode 请求值
     */
    public static void installApp(Activity activity, String filePath, int requestCode)
    {
        installApp(activity, FileUtils.getFileByPath(filePath), requestCode);
    }


    /**
     * 安装App(支持6.0)
     *
     * @param activity    activity
     * @param file        文件
     * @param requestCode 请求值
     */
    public static void installApp(Activity activity, File file, int requestCode)
    {
        if (!FileUtils.isFileExists(file))
        {
            return;
        }

        activity.startActivityForResult(IntentUtils.getInstallAppIntent(file), requestCode);
    }


    /**
     * 静默安装App
     * <p>非root需添加权限 {@code <uses-permission android:name="android.permission.INSTALL_PACKAGES"/>}</p>
     *
     * @param filePath 文件路径
     * @return {@code true}: 安装成功<br>{@code false}: 安装失败
     */
    public static boolean installAppSilent(String filePath)
    {
        File file = FileUtils.getFileByPath(filePath);
        if (!FileUtils.isFileExists(file))
        {
            return false;
        }

        String command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install " + filePath;
        ShellUtils.CommandResult result = ShellUtils.execCmd(command, !isSystemApp(Utils.getContext()), true);
        return result.getSuccessMsg() != null && result.getSuccessMsg().toLowerCase().contains("success");
    }


    /**
     * 卸载App
     *
     * @param context     上下文
     * @param packageName 包名
     */
    public static void uninstallApp(Context context, String packageName)
    {
        if (StringUtils.isSpace(packageName))
        {
            return;
        }

        context.startActivity(IntentUtils.getUninstallAppIntent(packageName));
    }


    /**
     * 卸载App
     *
     * @param activity    activity
     * @param packageName 包名
     * @param requestCode 请求值
     */
    public static void uninstallApp(Activity activity, String packageName, int requestCode)
    {
        if (StringUtils.isSpace(packageName))
        {
            return;
        }

        activity.startActivityForResult(IntentUtils.getUninstallAppIntent(packageName), requestCode);
    }


    /**
     * 静默卸载App
     * <p>非root需添加权限 {@code <uses-permission android:name="android.permission.DELETE_PACKAGES"/>}</p>
     *
     * @param context     上下文
     * @param packageName 包名
     * @param isKeepData  是否保留数据
     * @return {@code true}: 卸载成功<br>{@code false}: 卸载失败
     */
    public static boolean uninstallAppSilent(Context context, String packageName, boolean isKeepData)
    {
        if (StringUtils.isSpace(packageName))
        {
            return false;
        }

        String command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib pm uninstall " + (isKeepData ? "-k " : "") + packageName;
        ShellUtils.CommandResult result = ShellUtils.execCmd(command, !isSystemApp(context), true);
        return result.getSuccessMsg() != null && result.getSuccessMsg().toLowerCase().contains("success");
    }


    /**
     * 打开App
     *
     * @param packageName 包名
     */
    public static void launchApp(String packageName)
    {
        if (StringUtils.isSpace(packageName))
        {
            return;
        }

        Utils.getContext().startActivity(IntentUtils.getLaunchAppIntent(packageName));
    }


    /**
     * 打开App
     *
     * @param activity    activity
     * @param packageName 包名
     * @param requestCode 请求值
     */
    public static void launchApp(Activity activity, String packageName, int requestCode)
    {
        if (StringUtils.isSpace(packageName))
        {
            return;
        }

        activity.startActivityForResult(IntentUtils.getLaunchAppIntent(packageName), requestCode);
    }


    /**
     * 获取App具体设置
     *
     * @param context 上下文
     */
    public static void getAppDetailsSettings(Context context)
    {
        getAppDetailsSettings(context, context.getPackageName());
    }


    /**
     * 获取App具体设置
     *
     * @param context     上下文
     * @param packageName 包名
     */
    public static void getAppDetailsSettings(Context context, String packageName)
    {
        if (StringUtils.isSpace(packageName))
        {
            return;
        }

        context.startActivity(IntentUtils.getAppDetailsSettingsIntent(packageName));
    }


    /**
     * 获取App名称
     *
     * @param context 上下文
     * @return App名称
     */
    public static String getAppName(Context context)
    {
        return getAppName(context, context.getPackageName());
    }


    /**
     * 获取App名称
     *
     * @param context     上下文
     * @param packageName 包名
     * @return App名称
     */
    public static String getAppName(Context context, String packageName)
    {
        if (StringUtils.isSpace(packageName))
        {
            return null;
        }

        try
        {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            return info == null ? null : info.applicationInfo.loadLabel(pm).toString();
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取App图标
     *
     * @param context 上下文
     * @return App图标
     */
    public static Drawable getAppIcon(Context context)
    {
        return getAppIcon(context, context.getPackageName());
    }


    /**
     * 获取App图标
     *
     * @param context     上下文
     * @param packageName 包名
     * @return App图标
     */
    public static Drawable getAppIcon(Context context, String packageName)
    {
        if (StringUtils.isSpace(packageName))
        {
            return null;
        }

        try
        {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            return info == null ? null : info.applicationInfo.loadIcon(pm);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取App路径
     *
     * @param context 上下文
     * @return App路径
     */
    public static String getAppPath(Context context)
    {
        return getAppPath(context, context.getPackageName());
    }


    /**
     * 获取App路径
     *
     * @param context     上下文
     * @param packageName 包名
     * @return App路径
     */
    public static String getAppPath(Context context, String packageName)
    {
        if (StringUtils.isSpace(packageName))
        {
            return null;
        }

        try
        {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            return info == null ? null : info.applicationInfo.sourceDir;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取App版本号
     *
     * @param context 上下文
     * @return App版本号
     */
    public static String getAppVersionName(Context context)
    {
        return getAppVersionName(context, context.getPackageName());
    }


    /**
     * 获取App版本号
     *
     * @param context     上下文
     * @param packageName 包名
     * @return App版本号
     */
    public static String getAppVersionName(Context context, String packageName)
    {
        if (StringUtils.isSpace(packageName))
        {
            return null;
        }

        try
        {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            return info == null ? null : info.versionName;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取App版本码
     *
     * @param context 上下文
     * @return App版本码<br>当发生异常时, 将返回-1
     */
    public static int getAppVersionCode(Context context)
    {
        return getAppVersionCode(context, context.getPackageName());
    }


    /**
     * 获取App版本码
     *
     * @param context     上下文
     * @param packageName 包名
     * @return App版本码<br>当发生异常时, 将返回-1
     */
    public static int getAppVersionCode(Context context, String packageName)
    {
        if (StringUtils.isSpace(packageName))
        {
            return -1;
        }

        try
        {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            return info == null ? -1 : info.versionCode;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            return -1;
        }
    }


    /**
     * 获取App签名
     *
     * @param context 上下文
     * @return App签名数组
     */
    public static Signature[] getAppSignature(Context context)
    {
        return getAppSignature(context, context.getPackageName());
    }


    /**
     * 获取App签名
     *
     * @param context     上下文
     * @param packageName 包名
     * @return App签名数组
     */
    public static Signature[] getAppSignature(Context context, String packageName)
    {
        if (StringUtils.isSpace(packageName))
        {
            return null;
        }

        try
        {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            return info == null ? null : info.signatures;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取应用签名的SHA1值
     *
     * @param context 上下文
     * @return 应用签名的SHA1字符串. 如: 75:97:30:A9:7E:43:73:F3:A0:EE:12:80:5D:B0:65:E3:A4:A6:49:A5
     */
    public static String getAppSignatureSHA1(Context context)
    {
        return getAppSignatureSHA1(context, context.getPackageName());
    }


    /**
     * 获取应用签名的SHA1值
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 应用签名的SHA1字符串. 如: 75:97:30:A9:7E:43:73:F3:A0:EE:12:80:5D:B0:65:E3:A4:A6:49:A5
     */
    public static String getAppSignatureSHA1(Context context, String packageName)
    {
        Signature[] signatures = getAppSignature(context, packageName);
        if (signatures == null || signatures.length == 0)
        {
            return null;
        }

        return EncryptUtils.encryptSHA1ToString(signatures[0].toByteArray()).replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
    }


    /**
     * 获取App信息
     * <p>AppInfo（名称，图标，包名，版本号，版本Code，是否系统应用）</p>
     *
     * @param context 上下文
     * @return 当前应用的AppInfo
     */
    public static AppInfo getAppInfo(Context context)
    {
        return getAppInfo(context, context.getPackageName());
    }


    /**
     * 获取App信息
     * <p>AppInfo（名称，图标，包名，版本号，版本Code，是否系统应用）</p>
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 应用的AppInfo
     */
    public static AppInfo getAppInfo(Context context, String packageName)
    {
        try
        {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            return AppInfo.newInstance(pm, info);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取所有已安装App的信息
     *
     * @param context 上下文
     * @return 所有已安装的AppInfo列表
     */
    public static List<AppInfo> getAppsInfo(Context context)
    {
        List<AppInfo> list = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
        for (PackageInfo info : installedPackages)
        {
            AppInfo appInfo = AppInfo.newInstance(pm, info);
            if (appInfo != null)
            {
                list.add(appInfo);
            }
        }

        return list;
    }


    /**
     * 判断App是否安装
     *
     * @param context     上下文
     * @param packageName 包名
     * @return {@code true}: 已安装<br>{@code false}: 未安装
     */
    public static boolean isInstallApp(Context context, String packageName)
    {
        return !StringUtils.isSpace(packageName) && IntentUtils.getLaunchAppIntent(packageName) != null;
    }


    /**
     * 判断App是否有root权限
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isAppRoot()
    {
        ShellUtils.CommandResult result = ShellUtils.execCmd("echo root", true);
        if (result.getResult() == 0)
        {
            return true;
        }
        if (result.getErrorMsg() != null)
        {
            LogUtils.d("isAppRoot", result.getErrorMsg());
        }

        return false;
    }


    /**
     * 判断App是否为系统应用
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isSystemApp(Context context)
    {
        return isSystemApp(context, context.getPackageName());
    }


    /**
     * 判断App是否为系统应用
     *
     * @param context     上下文
     * @param packageName 包名
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isSystemApp(Context context, String packageName)
    {
        if (StringUtils.isSpace(packageName))
        {
            return false;
        }

        try
        {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(packageName, 0);
            return info != null && (info.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 判断App是否为Debug版本
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: 否<br>当发生异常时, 将返回false
     */
    public static boolean isAppDebug(Context context)
    {
        return isAppDebug(context, context.getPackageName());
    }


    /**
     * 判断App是否为Debug版本
     *
     * @param context     上下文
     * @param packageName 包名
     * @return {@code true}: 是<br>{@code false}: 否<br>当发生异常时, 将返回false
     */
    public static boolean isAppDebug(Context context, String packageName)
    {
        if (StringUtils.isSpace(packageName))
        {
            return false;
        }

        try
        {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(packageName, 0);
            return info != null && (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 判断App是否处于前台
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isAppForeground(Context context)
    {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        if (infos == null || infos.size() == 0)
        {
            return false;
        }

        for (ActivityManager.RunningAppProcessInfo info : infos)
        {
            if (info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
            {
                return info.processName.equals(context.getPackageName());
            }
        }

        return false;
    }


    /**
     * 清除App所有数据
     *
     * @param dirPaths 目录路径
     * @return {@code true}: 成功<br>{@code false}: 失败
     */
    public static boolean cleanAppData(String... dirPaths)
    {
        File[] dirs = new File[dirPaths.length];
        int i = 0;
        for (String dirPath : dirPaths)
        {
            dirs[i++] = new File(dirPath);
        }

        return cleanAppData(dirs);
    }


    /**
     * 清除App所有数据
     *
     * @param dirs 目录
     * @return {@code true}: 成功<br>{@code false}: 失败
     */
    public static boolean cleanAppData(File... dirs)
    {
        boolean isSuccess = CleanUtils.cleanInternalCache();
        isSuccess &= CleanUtils.cleanInternalDBs();
        isSuccess &= CleanUtils.cleanInternalSP();
        isSuccess &= CleanUtils.cleanInternalFiles();
        isSuccess &= CleanUtils.cleanExternalCache();
        for (File dir : dirs)
        {
            isSuccess &= CleanUtils.cleanCustomCache(dir);
        }

        return isSuccess;
    }


    /**
     * App信息类
     */
    public static class AppInfo
    {
        private String name;
        private Drawable icon;
        private String packageName;
        private String packagePath;
        private String versionName;
        private int versionCode;
        private boolean isSystem;


        public AppInfo(String name, Drawable icon, String packageName, String packagePath,
                       String versionName, int versionCode, boolean isSystem)
        {
            this.setName(name);
            this.setIcon(icon);
            this.setPackageName(packageName);
            this.setPackagePath(packagePath);
            this.setVersionName(versionName);
            this.setVersionCode(versionCode);
            this.setIsSystem(isSystem);
        }


        public static AppInfo newInstance(PackageManager pm, PackageInfo pi)
        {
            if (pm == null || pi == null)
            {
                return null;
            }

            ApplicationInfo ai = pi.applicationInfo;
            String name = ai.loadLabel(pm).toString();
            Drawable icon = ai.loadIcon(pm);
            String packageName = pi.packageName;
            String packagePath = ai.sourceDir;
            String versionName = pi.versionName;
            int versionCode = pi.versionCode;
            boolean isSystem = (ai.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
            return new AppInfo(name, icon, packageName, packagePath, versionName, versionCode, isSystem);
        }


        public String getName()
        {
            return name;
        }


        public void setName(String name)
        {
            this.name = name;
        }


        public Drawable getIcon()
        {
            return icon;
        }


        public void setIcon(Drawable icon)
        {
            this.icon = icon;
        }


        public String getPackageName()
        {
            return packageName;
        }


        public void setPackageName(String packageName)
        {
            this.packageName = packageName;
        }


        public String getPackagePath()
        {
            return packagePath;
        }


        public void setPackagePath(String packagePath)
        {
            this.packagePath = packagePath;
        }


        public String getVersionName()
        {
            return versionName;
        }


        public void setVersionName(String versionName)
        {
            this.versionName = versionName;
        }


        public int getVersionCode()
        {
            return versionCode;
        }


        public void setVersionCode(int versionCode)
        {
            this.versionCode = versionCode;
        }


        public boolean isSystem()
        {
            return isSystem;
        }


        public void setIsSystem(boolean isSystem)
        {
            this.isSystem = isSystem;
        }


        @Override
        public String toString()
        {
            return "App名称：" + getName() +
                    "\nApp图标：" + getIcon() +
                    "\nApp包名：" + getPackageName() +
                    "\nApp路径：" + getPackagePath() +
                    "\nApp版本号：" + getVersionName() +
                    "\nApp版本码：" + getVersionCode() +
                    "\n是否系统App：" + isSystem();
        }
    }
}
