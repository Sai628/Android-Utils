package com.sai628.androidutils.utils;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.sai628.androidutils.utils.StringUtils.isEmpty;
import static com.sai628.androidutils.utils.StringUtils.isSpace;
import static com.sai628.androidutils.utils.StringUtils.length;
import static com.sai628.androidutils.utils.StringUtils.lowerFirstLetter;
import static com.sai628.androidutils.utils.StringUtils.null2Length0;
import static com.sai628.androidutils.utils.StringUtils.reverse;
import static com.sai628.androidutils.utils.StringUtils.toDBC;
import static com.sai628.androidutils.utils.StringUtils.toSBC;
import static com.sai628.androidutils.utils.StringUtils.upperFirstLetter;


/**
 * @author Sai
 * @ClassName: StringUtilsTest
 * @Description: StringUtils 单元测试
 * @date 17/02/2017 14:47
 */
public class StringUtilsTest
{
    @Test
    public void testIsEmpty() throws Exception
    {
        assertThat(isEmpty(null)).isTrue();
        assertThat(isEmpty("")).isTrue();
        assertThat(isEmpty(" ")).isFalse();
    }


    @Test
    public void testIsSpace() throws Exception
    {
        assertThat(isSpace(null)).isTrue();
        assertThat(isSpace("")).isTrue();
        assertThat(isSpace("  ")).isTrue();
        assertThat(isSpace("abc")).isFalse();
    }


    @Test
    public void testEquals() throws Exception
    {
        assertThat(StringUtils.equals(null, null)).isTrue();
        assertThat(StringUtils.equals("", "")).isTrue();
        assertThat(StringUtils.equals("abc", "abc")).isTrue();
        assertThat(StringUtils.equals(null, "")).isFalse();
        assertThat(StringUtils.equals(null, "abc")).isFalse();
        assertThat(StringUtils.equals("", "abc")).isFalse();
        assertThat(StringUtils.equals("Abc", "abc")).isFalse();
    }


    @Test
    public void testEqualsIgnoreCase() throws Exception
    {
        assertThat(StringUtils.equalsIgnoreCase(null, null)).isTrue();
        assertThat(StringUtils.equalsIgnoreCase("", "")).isTrue();
        assertThat(StringUtils.equalsIgnoreCase("abc", "abc")).isTrue();
        assertThat(StringUtils.equalsIgnoreCase(null, "")).isFalse();
        assertThat(StringUtils.equalsIgnoreCase(null, "abc")).isFalse();
        assertThat(StringUtils.equalsIgnoreCase("", "abc")).isFalse();
        assertThat(StringUtils.equalsIgnoreCase("Abc", "abc")).isTrue();
    }


    @Test
    public void testLength() throws Exception
    {
        assertThat(length(null)).isEqualTo(0);
        assertThat(length("")).isEqualTo(0);
        assertThat(length("abc")).isEqualTo(3);
    }


    @Test
    public void testNull2Lenght0() throws Exception
    {
        assertThat(null2Length0(null)).isEqualTo("");
        assertThat(null2Length0("abc")).isEqualTo("abc");
    }


    @Test
    public void testUpperFirstLetter() throws Exception
    {
        assertThat(upperFirstLetter(null)).isEqualTo(null);
        assertThat(upperFirstLetter("")).isEqualTo("");
        assertThat(upperFirstLetter("abc")).isEqualTo("Abc");
        assertThat(upperFirstLetter("Abc")).isEqualTo("Abc");
        assertThat(upperFirstLetter("测试")).isEqualTo("测试");
    }


    @Test
    public void testLowerFirstLetter() throws Exception
    {
        assertThat(lowerFirstLetter(null)).isEqualTo(null);
        assertThat(lowerFirstLetter("")).isEqualTo("");
        assertThat(lowerFirstLetter("abc")).isEqualTo("abc");
        assertThat(lowerFirstLetter("Abc")).isEqualTo("abc");
        assertThat(lowerFirstLetter("测试")).isEqualTo("测试");
    }


    @Test
    public void testReverse() throws Exception
    {
        assertThat(reverse(null)).isEqualTo(null);
        assertThat(reverse("")).isEqualTo("");
        assertThat(reverse("abc")).isEqualTo("cba");
        assertThat(reverse("反转字符串")).isEqualTo("串符字转反");
    }


    @Test
    public void testToDBC() throws Exception
    {
        assertThat(toDBC(null)).isEqualTo(null);
        assertThat(toDBC("")).isEqualTo("");
        assertThat(toDBC("abc")).isEqualTo("abc");
        assertThat(toDBC("ａｂｃ")).isEqualTo("abc");
        assertThat(toDBC("　，．＆")).isEqualTo(" ,.&");
    }


    @Test
    public void testToSBC() throws Exception
    {
        assertThat(toSBC(null)).isEqualTo(null);
        assertThat(toSBC("")).isEqualTo("");
        assertThat(toSBC("ａｂｃ")).isEqualTo("ａｂｃ");
        assertThat(toSBC("abc")).isEqualTo("ａｂｃ");
        assertThat(toSBC(" ,.&")).isEqualTo("　，．＆");
    }
}
