package com.sai628.androidutils.utils;

import android.annotation.SuppressLint;


/**
 * @author Sai
 * @ClassName: ConvertUtils
 * @Description: 转换相关工具类
 * @date 09/01/2018 12:57
 */
public class ConvertUtils
{
    private ConvertUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    /**
     * byte数组转换为hex字符串
     * <p>例如:</p>
     * bytes2HexString(new byte[]{0, (byte)0xa8}) returns 00A8
     *
     * @param bytes 字节数组
     * @return 16进制大写字符串
     */
    public static String bytes2HexString(byte[] bytes)
    {
        if (bytes == null)
        {
            return null;
        }

        int len = bytes.length;
        if (len <= 0)
        {
            return null;
        }

        char[] result = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++)
        {
            result[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];
            result[j++] = hexDigits[bytes[i] & 0x0f];
        }
        return new String(result);
    }


    /**
     * hex字符串转换为byte数组
     * <p>例如:</p>
     * hexString2Bytes("00A8") returns {0, (byte)0xA8}
     *
     * @param hexString 十六进制字符串
     * @return 字节数组
     */
    public static byte[] hexString2Bytes(String hexString)
    {
        if (StringUtils.isSpace(hexString))
        {
            return null;
        }

        int len = hexString.length();
        if (len % 2 != 0)
        {
            hexString = "0" + hexString;
            len = len + 1;
        }

        char[] hexBytes = hexString.toUpperCase().toCharArray();
        byte[] result = new byte[len >> 1];
        for (int i = 0; i < len; i += 2)
        {
            result[i >> 1] = (byte) (hex2Dec(hexBytes[i]) << 4 | hex2Dec(hexBytes[i + 1]));
        }

        return result;
    }


    /**
     * hex字符转换为int
     *
     * @param hexChar hex单个字节
     * @return [0, 15]范围中的整数
     */
    private static int hex2Dec(char hexChar)
    {
        if (hexChar >= '0' && hexChar <= '9')
        {
            return hexChar - '0';
        }
        else if (hexChar >= 'A' && hexChar <= 'F')
        {
            return hexChar - 'A' + 10;
        }
        else
        {
            throw new IllegalArgumentException("hexChar:" + hexChar);
        }
    }


    /**
     * char数组转换为byte数组
     *
     * @param chars 字符数组
     * @return 字节数组
     */
    public static byte[] chars2Bytes(char[] chars)
    {
        if (chars == null || chars.length <= 0)
        {
            return null;
        }

        int len = chars.length;
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++)
        {
            bytes[i] = (byte) (chars[i]);
        }

        return bytes;
    }


    /**
     * byte数组转换为char数组
     *
     * @param bytes 字节数组
     * @return 字符数组
     */
    public static char[] bytes2Chars(byte[] bytes)
    {
        if (bytes == null || bytes.length <= 0)
        {
            return null;
        }

        int len = bytes.length;
        char[] chars = new char[len];
        for (int i = 0; i < len; i++)
        {
            chars[i] = (char) (bytes[i] & 0xFF);
        }

        return chars;
    }


    /**
     * 以unit为单位的内存大小, 转换为字节数
     * <p>若memorySize小于0, 将返回-1</p>
     *
     * @param memorySize 大小
     * @param unit       单位类型
     *                   <ul>
     *                   <li>{@link ConstUtils.MemoryUnit#BYTE}: 字节</li>
     *                   <li>{@link ConstUtils.MemoryUnit#KB}: 千字节</li>
     *                   <li>{@link ConstUtils.MemoryUnit#MB}: 兆字节</li>
     *                   <li>{@link ConstUtils.MemoryUnit#GB}: 千兆字节</li>
     *                   </ul>
     * @return 字节数
     */
    public static long memorySize2Byte(long memorySize, ConstUtils.MemoryUnit unit)
    {
        if (memorySize < 0)
        {
            return -1;
        }

        switch (unit)
        {
            default:
            case BYTE:
                return memorySize;
            case KB:
                return memorySize * ConstUtils.KB;
            case MB:
                return memorySize * ConstUtils.MB;
            case GB:
                return memorySize * ConstUtils.GB;
        }
    }


    /**
     * 字节数转换为以unit为单位的内存大小
     * <p>若byteSize小于0, 将返回-1</p>
     *
     * @param byteSize 字节数
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link ConstUtils.MemoryUnit#BYTE}: 字节</li>
     *                 <li>{@link ConstUtils.MemoryUnit#KB}: 千字节</li>
     *                 <li>{@link ConstUtils.MemoryUnit#MB}: 兆字节</li>
     *                 <li>{@link ConstUtils.MemoryUnit#GB}: 千兆字节</li>
     *                 </ul>
     * @return 以unit为单位的内存大小
     */
    public static double byte2MemorySize(long byteSize, ConstUtils.MemoryUnit unit)
    {
        if (byteSize < 0)
        {
            return -1;
        }

        switch (unit)
        {
            default:
            case BYTE:
                return (double) byteSize;
            case KB:
                return (double) byteSize / ConstUtils.KB;
            case MB:
                return (double) byteSize / ConstUtils.MB;
            case GB:
                return (double) byteSize / ConstUtils.GB;
        }
    }


    /**
     * 字节数转换为合适显示的内存大小字符串
     * <p>保留3位小数</p>
     *
     * @param byteSize 字节数
     * @return 合适显示的内存大小字符串
     */
    @SuppressLint("DefaultLocale")
    public static String byte2FitMemorySize(long byteSize)
    {
        if (byteSize < 0)
        {
            return "shouldn't be less than zero!";
        }
        else if (byteSize < ConstUtils.KB)
        {
            return String.format("%.3fB", byteSize + 0.0005);
        }
        else if (byteSize < ConstUtils.MB)
        {
            return String.format("%.3fKB", byteSize / ConstUtils.KB + 0.0005);
        }
        else if (byteSize < ConstUtils.GB)
        {
            return String.format("%.3fMB", byteSize / ConstUtils.MB + 0.0005);
        }
        else
        {
            return String.format("%.3fGB", byteSize / ConstUtils.GB + 0.0005);
        }
    }


    //TODO
}
