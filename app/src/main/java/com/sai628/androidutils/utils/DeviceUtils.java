package com.sai628.androidutils.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

import java.io.File;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;


/**
 * @author Sai
 * @ClassName: DeviceUtils
 * @Description: 设备工具类
 * @date 05/03/2018 19:40
 */
public class DeviceUtils
{
    // Android系统中Mac地址"假"的固定值
    private static final String DEFAULT_MAC_ADDRESS = "02:00:00:00:00:00";


    private DeviceUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * 判断设备是否已root
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isRooted()
    {
        String su = "su";
        String[] locations = {
                "/system/bin/",
                "/system/xbin/",
                "/sbin/",
                "/system/sd/xbin/",
                "/system/bin/failsafe/",
                "/data/local/xbin/",
                "/data/local/bin/",
                "/data/local/"
        };
        for (String location : locations)
        {
            File file = new File(location + su);
            if (file.exists())
            {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取设备系统版本号
     *
     * @return 设备系统版本号
     */
    public static int getSDKVersion()
    {
        return Build.VERSION.SDK_INT;
    }


    /**
     * 获取设备的AndroidID
     *
     * @return 设备的AndroidID
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID()
    {
        return Settings.Secure.getString(Utils.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    /**
     * 获取设备厂商信息
     *
     * @return 设备厂商信息
     */
    public static String getManufacturer()
    {
        return Build.MANUFACTURER;
    }


    /**
     * 获取设备型号
     *
     * @return 设备型号
     */
    public static String getModel()
    {
        String model = Build.MODEL;
        return model == null ? "" : model.trim().replaceAll("\\s*", "");
    }


    /**
     * 获取设备的MAC地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @return 设备的MAC地址
     */
    public static String getMacAddress()
    {
        String macAddress = getMacAddressByWifiInfo();
        if (!macAddress.equals(DEFAULT_MAC_ADDRESS))
        {
            return macAddress;
        }

        macAddress = getMacAddressByNetworkInterface();
        if (!macAddress.equals(DEFAULT_MAC_ADDRESS))
        {
            return macAddress;
        }

        macAddress = getMacAddressByFile();
        if (!macAddress.equals(DEFAULT_MAC_ADDRESS))
        {
            return macAddress;
        }

        return DEFAULT_MAC_ADDRESS;
    }


    /**
     * 通过Wifi信息获取设备的MAC地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     *
     * @return 设备的MAC地址
     */
    public static String getMacAddressByWifiInfo()
    {
        try
        {
            WifiManager manager = (WifiManager) Utils.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (manager != null)
            {
                WifiInfo info = manager.getConnectionInfo();
                if (info != null)
                {
                    return info.getMacAddress();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return DEFAULT_MAC_ADDRESS;
    }


    /**
     * 通过网卡信息获取设备的Mac地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @return 设备的MAC地址
     */
    public static String getMacAddressByNetworkInterface()
    {
        try
        {
            List<NetworkInterface> networkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : networkInterfaces)
            {
                if (networkInterface.getName().equals("wlan0"))
                {
                    byte[] address = networkInterface.getHardwareAddress();
                    if (address != null && address.length > 0)
                    {
                        StringBuilder buf = new StringBuilder();
                        for (byte b : address)
                        {
                            buf.append(String.format("%02x:", b));
                        }
                        buf.deleteCharAt(buf.length() - 1);
                        return buf.toString();
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return DEFAULT_MAC_ADDRESS;
    }


    /**
     * 通过系统文件信息获取设备的Mac地址
     *
     * @return 设备的MAC地址
     */
    public static String getMacAddressByFile()
    {
        ShellUtils.CommandResult result = ShellUtils.execCmd("getprop wifi.interface", false);
        if (result.getResult() == 0)  // 运行命令成功
        {
            String name = result.getSuccessMsg();
            if (name != null)
            {
                result = ShellUtils.execCmd("cat /sys/class/net/" + name + "/address", false);
                if (result.getResult() == 0 && result.getSuccessMsg() != null)  // 运行命令成功, 并且存在结果信息
                {
                    return result.getSuccessMsg();
                }
            }
        }

        return DEFAULT_MAC_ADDRESS;
    }


    /**
     * 关机
     * <p>需要root权限或者系统权限 {@code <android:shareUserId="android.uid.system"}</p>
     */
    public static void shutdown()
    {
        ShellUtils.execCmd("reboot -p", true);

        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false); // 不需要用户确认
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Utils.getContext().startActivity(intent);
    }


    /**
     * 重启
     * <p>需要root权限或者系统权限 {@code <android:shareUserId="android.uid.system"}</p>
     */
    public static void reboot()
    {
        ShellUtils.execCmd("reboot", true);

        Intent intent = new Intent(Intent.ACTION_REBOOT);
        intent.putExtra("nowait", 1);
        intent.putExtra("interval", 1);
        intent.putExtra("window", 0);
        Utils.getContext().sendBroadcast(intent);
    }


    /**
     * 重启到某引导模式
     * <p>需要root权限或者系统权限 {@code <android:shareUserId="android.uid.system"}</p>
     *
     * @param reason 传递给内核来请求特殊的引导模式. 如"recovery"
     */
    public static void reboot(String reason)
    {
        PowerManager manager = (PowerManager) Utils.getContext().getSystemService(Context.POWER_SERVICE);
        try
        {
            manager.reboot(reason);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 重启到Recovery模式
     * <p>需要root权限</p>
     */
    public static void reboot2Recovery()
    {
        ShellUtils.execCmd("reboot recovery", true);
    }


    /**
     * 重启到bootloader模式
     * <p>需要root权限</p>
     */
    public static void reboot2Bootloader()
    {
        ShellUtils.execCmd("reboot bootloader", true);
    }
}
