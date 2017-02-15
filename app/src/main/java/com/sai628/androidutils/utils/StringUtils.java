package com.sai628.androidutils.utils;

/**
 * @author Sai
 * @ClassName: StringUtils
 * @Description: 字符串工具类
 * @date 15/02/2017 18:03
 */
public class StringUtils
{
    private StringUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * 判断字符串是否为 null 或长度为 0
     *
     * @param s 待校验的字符串
     * @return {@code true}: null 或空字符串<br>{@code false}: 不为空
     */
    public static boolean isEmpty(CharSequence s)
    {
        return (s == null || s.length() == 0);
    }


    /**
     * 判断字符串是否为 null 或全为空格
     *
     * @param s 待校验的字符串
     * @return {@code true}: null 或全为空格<br>{@code false}: 不为 null 且不全为空格
     */
    public static boolean isSpace(String s)
    {
        return (s == null || s.trim().length() == 0);
    }


    /**
     * 判断两字符串是否相等
     *
     * @param s1 待校验的字符串 s1
     * @param s2 待校验的字符串 s2
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equals(CharSequence s1, CharSequence s2)
    {
        if (s1 == s2)
        {
            return true;
        }

        if (s1 != null && s2 != null && s1.length() == s2.length())
        {
            if (s1 instanceof String && s2 instanceof String)
            {
                return s1.equals(s2);
            }
            else
            {
                for (int i = 0; i < s1.length(); i++)
                {
                    if (s1.charAt(i) != s2.charAt(i))
                    {
                        return false;
                    }
                }
                return true;
            }
        }

        return false;
    }


    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param s1 待校验字符串 s1
     * @param s2 待校验字符串 s2
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equalsIgnoreCase(String s1, String s2)
    {
        if (s1 == s2)
        {
            return true;
        }

        if (s1 != null && s2 != null && s1.length() == s2.length())
        {
            return s1.regionMatches(true, 0, s2, 0, s2.length());
        }

        return false;
    }


    /**
     * null 转为长度为 0 的字符串
     *
     * @param s 待转换的字符串
     * @return {@code String} s 为 null 时, 转为长度为 0 的字符串; 否则不改变
     */
    public static String null2Length0(String s)
    {
        return (s == null ? "" : s);
    }


    /**
     * 返回字符串的长度
     *
     * @param s 待计算的字符串
     * @return {@code int} 若 s 为 null, 返回0; 否则返回 s.length().
     */
    public static int length(CharSequence s)
    {
        return (s == null ? 0 : s.length());
    }


    /**
     * 字符串首字母转大写
     *
     * @param s 待转换的字符串
     * @return {@code String} 首字母转大写后的字符串
     */
    public static String upperFirstLetter(String s)
    {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0)))
        {
            return s;
        }

        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }


    /**
     * 字符串首字母转小写
     *
     * @param s 待转换的字符串
     * @return {@code String} 首字母转小写后的字符串
     */
    public static String lowerFirstLetter(String s)
    {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0)))
        {
            return s;
        }

        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }


    /**
     * 反转字符串
     *
     * @param s 待处理的字符串
     * @return {@code String} 反转后的字符串
     */
    public static String reverse(String s)
    {
        int len = length(s);
        if (len <= 1)
        {
            return s;
        }

        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; i++)
        {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }

        return new String(chars);
    }


    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s)
    {
        if (isEmpty(s))
        {
            return s;
        }

        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++)
        {
            if (chars[i] == 12288)
            {
                chars[i] = ' ';
            }
            else if (65281 <= chars[i] && chars[i] <= 65374)
            {
                chars[i] = (char) (chars[i] - 65248);
            }
        }

        return new String(chars);
    }


    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s)
    {
        if (isEmpty(s))
        {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++)
        {
            if (chars[i] == ' ')
            {
                chars[i] = (char) 12288;
            }
            else if (33 <= chars[i] && chars[i] <= 126)
            {
                chars[i] = (char) (chars[i] + 65248);
            }
        }
        return new String(chars);
    }
}
