package com.sai628.androidutils.utils;

import java.io.File;


/**
 * @author Sai
 * @ClassName: FileUtils
 * @Description: 文件工具类
 * @date 25/12/2017 16:48
 */
public class FileUtils
{
    private FileUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return
     */
    public static File getFileByPath(String filePath)
    {
        return StringUtils.isSpace(filePath) ? null : new File(filePath);
    }


    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isFileExists(String filePath)
    {
        return isFileExists(getFileByPath(filePath));
    }


    /**
     * 判断文件是否存在
     *
     * @param file 文件
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isFileExists(File file)
    {
        return (file != null && file.exists());
    }
}
