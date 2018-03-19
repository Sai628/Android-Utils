package com.sai628.androidutils.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * @author Sai
 * @ClassName: NetworkUtils
 * @Description: 网络工具类
 * @date 19/03/2018 18:57
 */
public class NetworkUtils
{
    private NetworkUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    // 在 Build.VERSION.SDK_INT < 25时,
    // TelephonyManager.NETWORK_TYPE_GSM TelephonyManager.NETWORK_TYPE_TD_SCDMA TelephonyManager.NETWORK_TYPE_IWLAN 是隐藏的.
    // 这里通过私有的方式, 将这三个类型值声明出来
    private static final int NETWORK_TYPE_GSM = 16;
    private static final int NETWORK_TYPE_TD_SCDMA = 17;
    private static final int NETWORK_TYPE_IWLAN = 18;


    public enum NetworkType
    {
        NETWORK_WIFI,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }


    /**
     * 打开网络设置界面
     */
    public static void openWirelessSettings()
    {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Utils.getContext().startActivity(intent);
    }


    /**
     * 判断网络是否连接
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isConnected()
    {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isConnected();
    }


    /**
     * 判断网络是否可用(通过Ping的检测方式)
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @return {@code true}: 可用<br>{@code false}: 不可用
     */
    public static boolean isAvailableByPing()
    {
        // Ping Aibaba Public DNS
        ShellUtils.CommandResult result = ShellUtils.execCmd("ping -c 1 -w 1 223.5.5.5", false);
        if (result.getErrorMsg() != null)
        {
            LogUtils.d("isAvailableByPing errorMsg", result.getErrorMsg());
        }
        if (result.getSuccessMsg() != null)
        {
            LogUtils.d("isAvailableByPing successMsg", result.getSuccessMsg());
        }

        return (result.getResult() == 0);
    }


    /**
     * 判断移动数据是否打开
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDataEnabled()
    {
        try
        {
            TelephonyManager manager = (TelephonyManager) Utils.getContext().getSystemService(Context.TELEPHONY_SERVICE);
            Method method = manager.getClass().getDeclaredMethod("getDataEnabled");
            if (method != null)
            {
                return (boolean) method.invoke(manager);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * 打开/关闭移动数据
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.MODIFY_PHONE_STATE"/>}</p>
     *
     * @param enabled {@code true}: 打开<br>{@code false}: 关闭
     */
    public static void setDataEnabled(boolean enabled)
    {
        try
        {
            TelephonyManager manager = (TelephonyManager) Utils.getContext().getSystemService(Context.TELEPHONY_SERVICE);
            Method method = manager.getClass().getDeclaredMethod("setDataEnabled", boolean.class);
            if (method != null)
            {
                method.invoke(manager, enabled);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 判断网络是否是4G
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean is4G()
    {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isAvailable() && info.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE;
    }


    /**
     * 判断wifi是否打开
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isWifiEnabled()
    {
        WifiManager manager = (WifiManager) Utils.getContext().getSystemService(Context.WIFI_SERVICE);
        return manager.isWifiEnabled();
    }


    /**
     * 打开/关闭wifi
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>}</p>
     *
     * @param enabled {@code true}: 打开<br>{@code false}: 关闭
     */
    public static void setWifiEnabled(boolean enabled)
    {
        WifiManager manager = (WifiManager) Utils.getContext().getSystemService(Context.WIFI_SERVICE);
        if (enabled && !manager.isWifiEnabled())
        {
            manager.setWifiEnabled(true);
        }
        else if (!enabled && manager.isWifiEnabled())
        {
            manager.setWifiEnabled(false);
        }
    }


    /**
     * 判断wifi是否连接状态
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return {@code true}: 连接<br>{@code false}: 未连接
     */
    public static boolean isWifiConnected()
    {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.getType() == ConnectivityManager.TYPE_WIFI;
    }


    /**
     * 判断wifi数据是否可用
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @return
     */
    public static boolean isWifiAvailable()
    {
        return isWifiEnabled() && isAvailableByPing();
    }


    /**
     * 获取网络运营商名称
     * <p>如: 中国移动/中国联通/中国电信</p>
     *
     * @return 运营商名称
     */
    public static String getNetworkOperatorName()
    {
        TelephonyManager manager = (TelephonyManager) Utils.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return manager != null ? manager.getNetworkOperatorName() : null;
    }


    /**
     * 获取当前网络类型
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return 网络类型
     * <ul>
     *     <li>{@link NetworkUtils.NetworkType#NETWORK_WIFI}</li>
     *     <li>{@link NetworkUtils.NetworkType#NETWORK_4G}</li>
     *     <li>{@link NetworkUtils.NetworkType#NETWORK_3G}</li>
     *     <li>{@link NetworkUtils.NetworkType#NETWORK_2G}</li>
     *     <li>{@link NetworkUtils.NetworkType#NETWORK_UNKNOWN}</li>
     *     <li>{@link NetworkUtils.NetworkType#NETWORK_NO}</li>
     * </ul>
     *
     */
    public static NetworkType getNetworkType()
    {
        NetworkInfo netInfo = getActiveNetworkInfo();
        if (netInfo == null || !netInfo.isAvailable())  // 网络不可用
        {
            return NetworkType.NETWORK_NO;
        }

        int type = netInfo.getType();
        int subType = netInfo.getSubtype();
        if (type == ConnectivityManager.TYPE_WIFI)  // wifi网络
        {
            return NetworkType.NETWORK_WIFI;
        }
        else if (type == ConnectivityManager.TYPE_MOBILE)  // 移动数据网络
        {
            switch (subType)
            {
                case NETWORK_TYPE_GSM:
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return NetworkType.NETWORK_2G;  // 2G

                case NETWORK_TYPE_TD_SCDMA:
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                    return NetworkType.NETWORK_3G; // 3G

                case NETWORK_TYPE_IWLAN:
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return NetworkType.NETWORK_4G;  // 4G

                default:
                    String subtypeName = netInfo.getSubtypeName().toUpperCase();
                    return (subtypeName.equals("TD-SCDMA") ||
                            subtypeName.equals("WCDMA") ||
                            subtypeName.equals("CDMA2000")) ? NetworkType.NETWORK_3G : NetworkType.NETWORK_UNKNOWN;
            }
        }
        else // 未知网络类型
        {
            return NetworkType.NETWORK_UNKNOWN;
        }
    }


    /**
     * 获取IP地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @param useIPv4 是否使用IPv4
     * @return IP地址
     */
    public static String getIPAddress(boolean useIPv4)
    {
        try
        {

            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements())
            {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if (!networkInterface.isUp())  // 防止小米手机返回10.0.2.15
                {
                    continue;
                }

                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements())
                {
                    InetAddress address = addresses.nextElement();
                    if (!address.isLoopbackAddress())  // 非本地回环地址
                    {
                        String host = address.getHostAddress();
                        boolean isIPv4 = host.indexOf(':') < 0;
                        if (useIPv4 && isIPv4)
                        {
                            return host;
                        }
                        else if (!useIPv4 && !isIPv4)
                        {
                            int index = host.indexOf('%');
                            return index < 0 ? host.toUpperCase() : host.substring(0, index).toUpperCase();
                        }
                    }
                }
            }
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 获取域名的IP地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @param domain
     * @return
     */
    public static String getDomainAddress(final String domain)
    {
        try
        {
            ExecutorService service = Executors.newCachedThreadPool();
            Future<String> future = service.submit(new Callable<String>()
            {
                @Override
                public String call() throws Exception
                {
                    try
                    {
                        InetAddress address = InetAddress.getByName(domain);
                        return address.getHostAddress();
                    }
                    catch (UnknownHostException e)
                    {
                        e.printStackTrace();
                    }

                    return null;
                }
            });

            return future.get();
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 获取活动网络信息
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return NetworkInfo
     */
    private static NetworkInfo getActiveNetworkInfo()
    {
        ConnectivityManager manager = (ConnectivityManager) Utils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager != null ? manager.getActiveNetworkInfo() : null;
    }
}
