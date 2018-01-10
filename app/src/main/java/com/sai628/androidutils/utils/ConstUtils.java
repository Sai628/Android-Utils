package com.sai628.androidutils.utils;

/**
 * @author Sai
 * @ClassName: ConstUtils
 * @Description: 常量相关工具类
 * @date 08/01/2018 17:19
 */
public class ConstUtils
{
    private ConstUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /*-------------------- 与存储相关 --------------------*/
    /**
     * KB与Byte的倍数
     */
    public static final int KB = 1024;
    /**
     * MB与Byte的倍数
     */
    public static final int MB = 1024 * 1024;
    /**
     * GB与Byte的倍数
     */
    public static final int GB = 1024 * 1024 * 1024;


    public enum MemoryUnit
    {
        BYTE,
        KB,
        MB,
        GB
    }


    /*-------------------- 与时间相关 --------------------*/
    /**
     * 秒与毫秒的倍数
     */
    public static final int SEC = 1000;
    /**
     * 分与毫秒的倍数
     */
    public static final int MIN = 60 * 1000;
    /**
     * 时与毫秒的倍数
     */
    public static final int HOUR = 60 * 60 * 1000;
    /**
     * 天与毫秒的倍数
     */
    public static final int DAY = 24 * 60 * 60 * 1000;


    public enum TimeUnit
    {
        MSEC,
        SEC,
        MIN,
        HOUR,
        DAY
    }


    /*-------------------- 与正则相关 --------------------*/
    /**
     * 正则: 手机号(简单)
     */
    public static final String REGEX_MOBILE = "^[1]\\d{10}$";
    /**
     * 正则: 电话号码
     */
    public static final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";
    /**
     * 正则: 身份证号码15位
     */
    public static final String REGEX_ID_CARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    /**
     * 正则: 身份证号码18位
     */
    public static final String REGEX_ID_CARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
    /**
     * 正则: 邮箱
     */
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * 正则: URL
     */
    public static final String REGEX_URL = "[a-zA-z]+://[^\\s]*";
    /**
     * 正则: 汉字
     */
    public static final String REGEX_ZH = "^[\\u4e00-\\u9fa5]+$";
    /**
     * 正则: 用户名(取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位)
     */
    public static final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";
    /**
     * 正则: yyyy-MM-dd格式的日期校验，已考虑平闰年
     */
    public static final String REGEX_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-" +
            "(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    /**
     * 正则: IP地址
     */
    public static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
}
