package com.sai628.androidutils.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;


/**
 * @author Sai
 * @ClassName: SPUtils
 * @Description: SharedPreferences工具类
 * @date 07/02/2018 14:18
 */
public class SPUtils
{
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    /**
     * SPUtils构造函数
     * <p>在Application中初始化</p>
     *
     * @param spName 配置文件的名称
     */
    public SPUtils(String spName)
    {
        sp = Utils.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.commit();
    }


    /**
     * 写入String类型值
     *
     * @param key   键
     * @param value 值
     */
    public void putString(String key, String value)
    {
        editor.putString(key, value).commit();
    }


    /**
     * 读取String类型值
     *
     * @param key 键
     * @return 存在时返回对应值, 否则返回默认值{@code null}
     */
    public String getString(String key)
    {
        return getString(key, null);
    }


    /**
     * 读取String类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 存在时返回对应值, 否则返回默认值{@code defValue}
     */
    public String getString(String key, String defValue)
    {
        return sp.getString(key, defValue);
    }


    /**
     * 写入int类型值
     *
     * @param key   键
     * @param value 值
     */
    public void putInt(String key, int value)
    {
        editor.putInt(key, value).commit();
    }


    /**
     * 读取int类型值
     *
     * @param key 键
     * @return 存在时返回对应值, 否则返回默认值-1
     */
    public int getInt(String key)
    {
        return getInt(key, -1);
    }


    /**
     * 读取int类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 存在时返回对应值, 否则返回默认值{@code defValue}
     */
    public int getInt(String key, int defValue)
    {
        return sp.getInt(key, defValue);
    }


    /**
     * 写入long类型值
     *
     * @param key   键
     * @param value 值
     */
    public void putLong(String key, long value)
    {
        editor.putLong(key, value).commit();
    }


    /**
     * 读取long类型值
     *
     * @param key 键
     * @return 存在时返回对应值, 否则返回默认值-1L
     */
    public long getLong(String key)
    {
        return getLong(key, -1L);
    }


    /**
     * 读取long类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 存在时返回对应值, 否则返回默认值{@code defValue}
     */
    public long getLong(String key, long defValue)
    {
        return sp.getLong(key, defValue);
    }


    /**
     * 写入float类型值
     *
     * @param key   键
     * @param value 值
     */
    public void putFloat(String key, float value)
    {
        editor.putFloat(key, value).commit();
    }


    /**
     * 读取float类型值
     *
     * @param key 键
     * @return 存在时返回对应值, 否则返回默认值-1f
     */
    public float getFloat(String key)
    {
        return getFloat(key, -1f);
    }


    /**
     * 读取float类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 存在时返回对应值, 否则返回默认值{@code defValue}
     */
    public float getFloat(String key, float defValue)
    {
        return sp.getFloat(key, defValue);
    }


    /**
     * 写入boolean类型值
     *
     * @param key   键
     * @param value 值
     */
    public void putBoolean(String key, boolean value)
    {
        editor.putBoolean(key, value).commit();
    }


    /**
     * 读取boolean类型值
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    public boolean getBoolean(String key)
    {
        return getBoolean(key, false);
    }


    /**
     * 读取boolean类型值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 存在时返回对应值, 否则返回默认值{@code defValue}
     */
    public boolean getBoolean(String key, boolean defValue)
    {
        return sp.getBoolean(key, defValue);
    }


    /**
     * 获取SP中的所有键值对
     *
     * @return Map对象
     */
    public Map<String, ?> getAll()
    {
        return sp.getAll();
    }


    /**
     * 判断SP中是否存在该key
     *
     * @param key 待判断的键名
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public boolean contains(String key)
    {
        return sp.contains(key);
    }


    /**
     * 移除SP中的键key
     *
     * @param key 待移除的键名
     */
    public void remove(String key)
    {
        editor.remove(key).commit();
    }


    /**
     * 清除SP中所有数据
     */
    public void clear()
    {
        editor.clear().commit();
    }
}
