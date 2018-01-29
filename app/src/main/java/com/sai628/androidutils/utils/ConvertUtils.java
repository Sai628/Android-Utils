package com.sai628.androidutils.utils;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;


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
     * byte数组转换为bit字符串
     * <p>例如:</p>
     * bytes2Bits(new byte[]{0x7F, (byte)0xFA}) returns "0111111111111010"
     *
     * @param bytes 字节数组
     * @return bit字符串
     */
    public static String bytes2Bits(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes)
        {
            for (int i = 7; i >= 0; i--)
            {
                sb.append(((aByte >> i) & 0x01) == 0 ? '0' : '1');
            }
        }

        return sb.toString();
    }


    /**
     * bit字符串转换为byte数组
     * <p>例如:</p>
     * bits2Bytes("111111111111010") returns 7FFA
     *
     * @param bits 二进制字符串
     * @return 字节数组
     */
    public static byte[] bits2Bytes(String bits)
    {
        int lenMod = bits.length() % 8;
        int byteLen = bits.length() / 8;

        // 不是8的倍数时, 前面补0
        if (lenMod != 0)
        {
            for (int i = lenMod; i < 8; i++)
            {
                bits = "0" + bits;
            }
            byteLen += 1;
        }

        byte[] bytes = new byte[byteLen];
        for (int i = 0; i < byteLen; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                bytes[i] <<= 1;
                bytes[i] |= bits.charAt(i * 8 + j) - '0';
            }
        }

        return bytes;
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
     * @return 合适显示的内存大小字符串. 当字节数小于0时, 结果返回空字符串
     */
    @SuppressLint("DefaultLocale")
    public static String byte2FitMemorySize(long byteSize)
    {
        if (byteSize < 0)
        {
            return "";
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


    /**
     * 以unit为单位的时间长度, 转换为毫秒时间
     *
     * @param timeSpan 时间长度
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *                 <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *                 <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *                 <li>{@link ConstUtils.TimeUnit#HOUR}: 时</li>
     *                 <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *                 </ul>
     * @return 毫秒时间
     */
    public static long timeSpan2Millis(long timeSpan, ConstUtils.TimeUnit unit)
    {
        switch (unit)
        {
            default:
            case MSEC:
                return timeSpan;
            case SEC:
                return timeSpan * ConstUtils.SEC;
            case MIN:
                return timeSpan * ConstUtils.MIN;
            case HOUR:
                return timeSpan * ConstUtils.HOUR;
            case DAY:
                return timeSpan * ConstUtils.DAY;
        }
    }


    /**
     * 将毫秒时间转换为以unit为单位的时间长度
     *
     * @param millis 毫秒时间
     * @param unit   单位类型
     *               <ul>
     *               <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *               <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *               <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *               <li>{@link ConstUtils.TimeUnit#HOUR}: 时</li>
     *               <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *               </ul>
     * @return 以unit为单位的时间长度
     */
    public static double millis2TimeSpan(long millis, ConstUtils.TimeUnit unit)
    {
        switch (unit)
        {
            default:
            case MSEC:
                return (double) millis;
            case SEC:
                return (double) millis / ConstUtils.SEC;
            case MIN:
                return (double) millis / ConstUtils.MIN;
            case HOUR:
                return (double) millis * ConstUtils.HOUR;
            case DAY:
                return (double) millis * ConstUtils.DAY;
        }
    }


    /**
     * inputStream转换为outputStream
     *
     * @param is 输入流
     * @return outputStream子类
     */
    public static ByteArrayOutputStream input2OutputStream(InputStream is)
    {
        if (is == null)
        {
            return null;
        }

        try
        {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = is.read(bytes, 0, 1024)) != -1)
            {
                os.write(bytes, 0, len);
            }

            return os;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            CloseUtils.closeIO(is);
        }
    }


    /**
     * outputStream转换为inputStream
     *
     * @param os 输出流
     * @return inputStream子类
     */
    public static ByteArrayInputStream output2InputStream(OutputStream os)
    {
        if (os == null)
        {
            return null;
        }

        return new ByteArrayInputStream(((ByteArrayOutputStream) os).toByteArray());
    }


    /**
     * inputStream转换为byte数组
     *
     * @param is 输入流
     * @return 字节数组
     */
    public static byte[] inputStream2Bytes(InputStream is)
    {
        if (is == null)
        {
            return null;
        }

        return input2OutputStream(is).toByteArray();
    }


    /**
     * byte数组转换为inputStream
     *
     * @param bytes 字节数组
     * @return 输入流
     */
    public static InputStream bytes2InputStream(byte[] bytes)
    {
        if (bytes == null || bytes.length <= 0)
        {
            return null;
        }

        return new ByteArrayInputStream(bytes);
    }


    /**
     * outputStream转换为byte数组
     *
     * @param os 输出流
     * @return 字节数组
     */
    public static byte[] outputStream2Bytes(OutputStream os)
    {
        if (os == null)
        {
            return null;
        }

        return ((ByteArrayOutputStream) os).toByteArray();
    }


    /**
     * byte数组转换为outputStream
     *
     * @param bytes 字节数组
     * @return outputStream子类
     */
    public static OutputStream bytes2OutputStream(byte[] bytes)
    {
        if (bytes == null || bytes.length <= 0)
        {
            return null;
        }

        ByteArrayOutputStream os = null;
        try
        {
            os = new ByteArrayOutputStream();
            os.write(bytes);
            return os;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            CloseUtils.closeIO(os);
        }
    }


    /**
     * inputStream按编码格式转换为string
     *
     * @param is          输入流
     * @param charsetName 编码格式
     * @return 字符串
     */
    public static String inputStream2String(InputStream is, String charsetName)
    {
        if (is == null || StringUtils.isSpace(charsetName))
        {
            return null;
        }

        try
        {
            return new String(inputStream2Bytes(is), charsetName);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * string按编码格式转换为inputStream
     *
     * @param string      字符串
     * @param charsetName 编码格式
     * @return 输入流
     */
    public static InputStream string2InputStream(String string, String charsetName)
    {
        if (string == null || StringUtils.isSpace(charsetName))
        {
            return null;
        }

        try
        {
            return new ByteArrayInputStream(string.getBytes(charsetName));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * outputStream按编码格式转换为string
     *
     * @param os          输出流
     * @param charsetName 编码格式
     * @return 字符串
     */
    public static String outputStream2String(OutputStream os, String charsetName)
    {
        if (os == null || StringUtils.isSpace(charsetName))
        {
            return null;
        }

        try
        {
            return new String(outputStream2Bytes(os), charsetName);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * string按编码格式转换为outputStream
     *
     * @param string      字符串
     * @param charsetName 编码格式
     * @return 输出流
     */
    public static OutputStream string2OutputStream(String string, String charsetName)
    {
        if (string == null || StringUtils.isSpace(charsetName))
        {
            return null;
        }

        try
        {
            return bytes2OutputStream(string.getBytes(charsetName));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * bitmap转换为byte数组
     *
     * @param bitmap bitmap对象
     * @param format 格式
     * @return 字节数组
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat format)
    {
        if (bitmap == null)
        {
            return null;
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(format, 100, os);
        return os.toByteArray();
    }


    /**
     * byte数组转换为bitmap
     *
     * @param bytes 字节数组
     * @return bitmap对象
     */
    public static Bitmap bytes2Bitmap(byte[] bytes)
    {
        if (bytes == null || bytes.length <= 0)
        {
            return null;
        }

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


    /**
     * drawable转换为bitmap
     *
     * @param drawable drawable对象
     * @return bitmap对象
     */
    public static Bitmap drawable2Bitmap(Drawable drawable)
    {
        return drawable == null ? null : ((BitmapDrawable) drawable).getBitmap();
    }


    /**
     * bitmap转换为drawable
     *
     * @param res    resources对象
     * @param bitmap bitmap对象
     * @return drawable对象
     */
    public static Drawable bitmap2Drawable(Resources res, Bitmap bitmap)
    {
        return bitmap == null ? null : new BitmapDrawable(res, bitmap);
    }


    /**
     * drawable转换为byte数组
     *
     * @param drawable drawable对象
     * @param format   格式
     * @return 字节数组
     */
    public static byte[] drawable2Bytes(Drawable drawable, Bitmap.CompressFormat format)
    {
        return drawable == null ? null : bitmap2Bytes(drawable2Bitmap(drawable), format);
    }


    /**
     * byte数组转换为drawable
     *
     * @param res   resources对象
     * @param bytes 字节数组
     * @return drawable对象
     */
    public static Drawable bytes2Drawable(Resources res, byte[] bytes)
    {
        return res == null ? null : bitmap2Drawable(res, bytes2Bitmap(bytes));
    }


    /**
     * view转换为bitmap
     *
     * @param view 视图
     * @return bitmap对象
     */
    public static Bitmap view2Bitmap(View view)
    {
        if (view == null)
        {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
        {
            bgDrawable.draw(canvas);
        }
        else
        {
            canvas.drawColor(Color.WHITE);
        }

        view.draw(canvas);
        return bitmap;
    }
}
