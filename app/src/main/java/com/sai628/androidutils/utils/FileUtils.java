package com.sai628.androidutils.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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


    /**
     * 重命令文件
     *
     * @param filePath 文件路径
     * @param newName  新文件名
     * @return {@code true}: 重命令成功<br>{@code false}: 重命令失败
     */
    public static boolean rename(String filePath, String newName)
    {
        return rename(getFileByPath(filePath), newName);
    }


    /**
     * 重命令文件
     *
     * @param file    文件
     * @param newName 新文件名
     * @return {@code true}: 重命令成功<br>{@code false}: 重命令失败
     */
    public static boolean rename(File file, String newName)
    {
        // 文件为null或者不存在时, 返回false
        if (file == null || !file.exists())
        {
            return false;
        }

        // 新文件名为空时, 返回false
        if (StringUtils.isSpace(newName))
        {
            return false;
        }

        // 新文件名与旧文件名一样时, 返回true
        if (newName.equals(file.getName()))
        {
            return true;
        }

        File newFile = new File(file.getParent() + File.separator + newName);
        // 若重合名的文件已存在时, 返回false
        return !newFile.exists() && file.renameTo(newFile);
    }


    /**
     * 判断是否为目录
     *
     * @param dirPath 目录路径
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDir(String dirPath)
    {
        return isDir(getFileByPath(dirPath));
    }


    /**
     * 判断是否为目录
     *
     * @param file 文件
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDir(File file)
    {
        return (isFileExists(file) && file.isDirectory());
    }


    /**
     * 判断是否为文件
     *
     * @param filePath 文件路径
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isFile(String filePath)
    {
        return isFile(getFileByPath(filePath));
    }


    /**
     * 判断是否为文件
     *
     * @param file 文件
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isFile(File file)
    {
        return (isFileExists(file) && file.isFile());
    }


    /**
     * 判断目录是否存在, 不存在则判断是否创建目录成功
     *
     * @param dirPath 目录路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(String dirPath)
    {
        return createOrExistsDir(getFileByPath(dirPath));
    }


    /**
     * 判断目录是否存在, 不存在则判断是否创建目录成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(File file)
    {
        return (file != null && (file.exists() ? file.isDirectory() : file.mkdirs()));
    }


    /**
     * 判断文件是否存在, 不存在则判断是否创建成功
     *
     * @param filePath 文件路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsFile(String filePath)
    {
        return createOrExistsFile(getFileByPath(filePath));
    }


    /**
     * 判断文件是否存在, 不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsFile(File file)
    {
        if (file == null)
        {
            return false;
        }

        if (file.exists())
        {
            return file.isFile();
        }

        if (!createOrExistsDir(file.getParentFile()))
        {
            return false;
        }

        try
        {
            return file.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 判断文件是否存在, 存在则在创建之前删除旧文件, 然后返回创建的结果
     *
     * @param filePath 文件路径
     * @return {@code true}: 创建成功<br>{@code false}: 创建失败
     */
    public static boolean createFileByDeleteOldFile(String filePath)
    {
        return createFileByDeleteOldFile(getFileByPath(filePath));
    }


    /**
     * 判断文件是否存在, 存在则在创建之前删除旧文件, 然后返回创建的结果
     *
     * @param file 文件
     * @return {@code true}: 创建成功<br>{@code false}: 创建失败
     */
    public static boolean createFileByDeleteOldFile(File file)
    {
        if (file == null)
        {
            return false;
        }

        // 文件存在并且删除失败的话, 返回false
        if (file.exists() && file.isFile() && !file.delete())
        {
            return false;
        }

        // 创建目录失败的话, 返回false
        if (!createOrExistsDir(file.getParentFile()))
        {
            return false;
        }

        try
        {
            return file.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 复制或移动目录
     *
     * @param srcDirPath  源目录路径
     * @param destDirPath 目标目录路径
     * @param isMove      是否移动
     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
     */
    public static boolean copyOrMoveDir(String srcDirPath, String destDirPath, boolean isMove)
    {
        return copyOrMoveDir(getFileByPath(srcDirPath), getFileByPath(destDirPath), isMove);
    }


    /**
     * 复制或移动目录
     *
     * @param srcDir  源目录
     * @param destDir 目标目录
     * @param isMove  是否移动
     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
     */
    public static boolean copyOrMoveDir(File srcDir, File destDir, boolean isMove)
    {
        if (srcDir == null || destDir == null)
        {
            return false;
        }

        // 在目录路径的后面添加上路径分隔符, 避免如 "./a" 与 "./abc" 的目录名出现误判
        String srcPath = srcDir.getPath() + File.separator;
        String destPath = destDir.getPath() + File.separator;

        // 若目标目录为源目录的子目录时, 返回false
        if (destPath.contains(srcPath))
        {
            return false;
        }

        // 若源目录不存在, 或者不是目录时, 返回false
        if (!srcDir.exists() || !srcDir.isDirectory())
        {
            return false;
        }

        // 目标目录不存在或创建失败时, 返回false
        if (!createOrExistsDir(destPath))
        {
            return false;
        }

        File[] files = srcDir.listFiles();
        for (File file : files)
        {
            File destFile = new File(destPath, file.getName());
            if (file.isFile())
            {
                if (!copyOrMoveFile(file, destFile, isMove))
                {
                    return false;
                }
            }
            else if (file.isDirectory())
            {
                if (!copyOrMoveDir(file, destFile, isMove))
                {
                    return false;
                }
            }
        }

        // 若不是移动目录操作时, 直接返回true. 否则返回删除目录后的结果
        if (!isMove)
        {
            return true;
        }
        else
        {
            return deleteDir(srcDir);
        }
    }


    /**
     * 复制或移动文件
     *
     * @param srcFilePath  源文件路径
     * @param destFilePath 目标文件路径
     * @param isMove       是否移动
     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
     */
    public static boolean copyOrMoveFile(String srcFilePath, String destFilePath, boolean isMove)
    {
        return copyOrMoveFile(getFileByPath(srcFilePath), getFileByPath(destFilePath), isMove);
    }


    /**
     * 复制或移动文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @param isMove   是否移动
     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
     */
    public static boolean copyOrMoveFile(File srcFile, File destFile, boolean isMove)
    {
        if (srcFile == null || destFile == null)
        {
            return false;
        }

        // 若源文件不存在, 或者不是文件时, 返回false
        if (!srcFile.exists() || !srcFile.isFile())
        {
            return false;
        }

        // 若目标文件存在, 并且是文件时, 返回false
        if (destFile.exists() && destFile.isFile())
        {
            return false;
        }

        // 若目标文件的上层目录不存在或者创建失败时, 返回false
        if (!createOrExistsDir(destFile.getParentFile()))
        {
            return false;
        }

        boolean result = false;
        try
        {
            result = writeFileFromIS(destFile, new FileInputStream(srcFile), false);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }

        // 若写入目标文件失败, 直接返回false
        if (!result)
        {
            return false;
        }

        // 若不是移动文件操作时, 直接返回true. 否则返回删除文件后的结果
        if (!isMove)
        {
            return true;
        }
        else
        {
            return deleteFile(srcFile);
        }
    }


    /**
     * 复制目录
     *
     * @param srcDirPath  源目录路径
     * @param destDirPath 目标目录路径
     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
     */
    public static boolean copyDir(String srcDirPath, String destDirPath)
    {
        return copyDir(getFileByPath(srcDirPath), getFileByPath(destDirPath));
    }


    /**
     * 复制目录
     *
     * @param srcDir  源目录
     * @param destDir 目标目录
     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
     */
    public static boolean copyDir(File srcDir, File destDir)
    {
        return copyOrMoveDir(srcDir, destDir, false);
    }


    /**
     * 复制文件
     *
     * @param srcFilePath  源文件路径
     * @param destFilePath 目标文件路径
     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
     */
    public static boolean copyFile(String srcFilePath, String destFilePath)
    {
        return copyFile(getFileByPath(srcFilePath), getFileByPath(destFilePath));
    }


    /**
     * 复制文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
     */
    public static boolean copyFile(File srcFile, File destFile)
    {
        return copyOrMoveFile(srcFile, destFile, false);
    }


    /**
     * 移动目录
     *
     * @param srcDirPath  源目录路径
     * @param destDirPath 目标目录路径
     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
     */
    public static boolean moveDir(String srcDirPath, String destDirPath)
    {
        return moveDir(getFileByPath(srcDirPath), getFileByPath(destDirPath));
    }


    /**
     * 移动目录
     *
     * @param srcDir  源目录
     * @param destDir 目标目录
     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
     */
    public static boolean moveDir(File srcDir, File destDir)
    {
        return copyOrMoveDir(srcDir, destDir, true);
    }


    /**
     * 移动文件
     *
     * @param srcFilePath  源文件路径
     * @param destFilePath 目标文件路径
     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
     */
    public static boolean moveFile(String srcFilePath, String destFilePath)
    {
        return moveFile(getFileByPath(srcFilePath), getFileByPath(destFilePath));
    }


    /**
     * 移动文件
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
     */
    public static boolean moveFile(File srcFile, File destFile)
    {
        return copyOrMoveFile(srcFile, destFile, true);
    }


    /**
     * 删除目录
     *
     * @param dirPath 目录路径
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteDir(String dirPath)
    {
        return deleteDir(getFileByPath(dirPath));
    }


    /**
     * 删除目录
     *
     * @param dir 目录
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteDir(File dir)
    {
        if (dir == null)
        {
            return false;
        }

        // 目录不存在时, 返回true
        if (!dir.exists())
        {
            return true;
        }

        // 不是目录时, 返回false
        if (!dir.isDirectory())
        {
            return false;
        }

        // 目录中存在文件或者子目录时, 先删除里面的内容
        File[] files = dir.listFiles();
        if (files != null && files.length > 0)
        {
            for (File file : files)
            {
                if (file.isFile() && !deleteFile(file))
                {
                    return false;
                }
                else if (file.isDirectory() && !deleteDir(file))
                {
                    return false;
                }
            }
        }

        return dir.delete();
    }


    /**
     * 删除文件
     *
     * @param filePath 文件路径
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFile(String filePath)
    {
        return deleteFile(getFileByPath(filePath));
    }


    /**
     * 删除文件
     *
     * @param file 文件
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFile(File file)
    {
        if (file == null)
        {
            return false;
        }

        // 当文件不存在时, 返回true
        if (!file.exists())
        {
            return true;
        }

        return (file.isFile() && file.delete());
    }


    /**
     * 删除目录下的所有文件
     *
     * @param dirPath 目录路径
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFilesInDir(String dirPath)
    {
        return deleteFilesInDir(getFileByPath(dirPath));
    }


    /**
     * 删除目录下的所有文件
     *
     * @param dir 目录
     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
     */
    public static boolean deleteFilesInDir(File dir)
    {
        if (dir == null)
        {
            return false;
        }

        // 目录不存在时, 返回true
        if (!dir.exists())
        {
            return true;
        }

        // 不是目录时, 返回false
        if (!dir.isDirectory())
        {
            return false;
        }

        // 目录中存在文件或者子目录时, 先删除里面的内容
        File[] files = dir.listFiles();
        if (files != null && files.length > 0)
        {
            for (File file : files)
            {
                if (file.isFile() && !deleteFile(file))
                {
                    return false;
                }
                else if (file.isDirectory() && !deleteDir(file))
                {
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * 获取目录下所有文件
     *
     * @param dirPath     目录路径
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDir(String dirPath, boolean isRecursive)
    {
        return listFilesInDir(getFileByPath(dirPath), isRecursive);
    }


    /**
     * 获取目录下所有文件
     *
     * @param dir         目录
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDir(File dir, boolean isRecursive)
    {
        if (!isDir(dir))
        {
            return null;
        }

        if (isRecursive)
        {
            return listFilesInDir(dir);
        }

        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null && files.length > 0)
        {
            Collections.addAll(list, files);
        }

        return list;
    }


    /**
     * 获取目录下所有文件(包括子目录)
     *
     * @param dirPath 目录路径
     * @return 文件链表
     */
    public static List<File> listFilesInDir(String dirPath)
    {
        return listFilesInDir(getFileByPath(dirPath));
    }


    /**
     * 获取目录下所有文件(包括子目录)
     *
     * @param dir 目录
     * @return 文件链表
     */
    public static List<File> listFilesInDir(File dir)
    {
        if (!isDir(dir))
        {
            return null;
        }

        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null && files.length > 0)
        {
            for (File file : files)
            {
                list.add(file);
                if (file.isDirectory())
                {
                    list.addAll(listFilesInDir(file));
                }
            }
        }

        return list;
    }


    /**
     * 获取目录下所有后缀名为 suffix 的文件
     * <p>大小写忽略</p>
     *
     * @param dirPath     目录路径
     * @param suffix      后缀名
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(String dirPath, String suffix, boolean isRecursive)
    {
        return listFilesInDirWithFilter(getFileByPath(dirPath), suffix, isRecursive);
    }


    /**
     * 获取目录下所有后缀名为 suffix 的文件
     * <p>大小写忽略</p>
     *
     * @param dir         目录
     * @param suffix      后缀名
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(File dir, String suffix, boolean isRecursive)
    {
        if (isRecursive)
        {
            return listFilesInDirWithFilter(dir, suffix);
        }

        if (dir == null || !isDir(dir))
        {
            return null;
        }

        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null && files.length > 0)
        {
            for (File file : files)
            {
                if (file.getName().toUpperCase().endsWith(suffix.toUpperCase()))
                {
                    list.add(file);
                }
            }
        }

        return list;
    }


    /**
     * 获取目录下所有后缀名为 suffix 的文件(包括子目录)
     * <p>大小写忽略</p>
     *
     * @param dirPath 目录路径
     * @param suffix  后缀名
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(String dirPath, String suffix)
    {
        return listFilesInDirWithFilter(getFileByPath(dirPath), suffix);
    }


    /**
     * 获取目录下所有后缀名为 suffix 的文件(包括子目录)
     * <p>大小写忽略</p>
     *
     * @param dir    目录
     * @param suffix 后缀名
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(File dir, String suffix)
    {
        if (dir == null || !isDir(dir))
        {
            return null;
        }

        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null && files.length > 0)
        {
            for (File file : files)
            {
                if (file.getName().toUpperCase().endsWith(suffix.toUpperCase()))
                {
                    list.add(file);
                }
                if (file.isDirectory())
                {
                    list.addAll(listFilesInDirWithFilter(file, suffix));
                }
            }
        }

        return list;
    }


    /**
     * 获取目录下所有符合 filter 的文件
     *
     * @param dirPath     目录路径
     * @param filter      过滤器
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(String dirPath, FilenameFilter filter, boolean isRecursive)
    {
        return listFilesInDirWithFilter(getFileByPath(dirPath), filter, isRecursive);
    }


    /**
     * 获取目录下所有符合 filter 的文件
     *
     * @param dir         目录
     * @param filter      过滤器
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(File dir, FilenameFilter filter, boolean isRecursive)
    {
        if (isRecursive)
        {
            return listFilesInDirWithFilter(dir, filter);
        }

        if (dir == null || !isDir(dir))
        {
            return null;
        }

        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null && files.length > 0)
        {
            for (File file : files)
            {
                if (filter.accept(file.getParentFile(), file.getName()))
                {
                    list.add(file);
                }
            }
        }

        return list;
    }


    /**
     * 获取目录下所有符合 filter 的文件(包括子目录)
     *
     * @param dirPath 目录路径
     * @param filter  过滤器
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(String dirPath, FilenameFilter filter)
    {
        return listFilesInDirWithFilter(getFileByPath(dirPath), filter);
    }


    /**
     * 获取目录下所有符合 filter 的文件(包括子目录)
     *
     * @param dir    目录路径
     * @param filter 过滤器
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(File dir, FilenameFilter filter)
    {
        if (dir == null || !isDir(dir))
        {
            return null;
        }

        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null && files.length > 0)
        {
            for (File file : files)
            {
                if (filter.accept(file.getParentFile(), file.getName()))
                {
                    list.add(file);
                }
                if (file.isDirectory())
                {
                    list.addAll(listFilesInDirWithFilter(file, filter));
                }
            }
        }

        return list;
    }


    /**
     * 将输入流写入文件
     *
     * @param filePath 待写入的文件路径
     * @param is       输入流
     * @param append   是否追加在文件末尾
     * @return
     */
    public static boolean writeFileFromIS(String filePath, InputStream is, boolean append)
    {
        return writeFileFromIS(getFileByPath(filePath), is, append);
    }


    /**
     * 将输入流写入文件
     *
     * @param file   待写入的文件
     * @param is     输入流
     * @param append 是否追加在文件末尾
     * @return {@code true}: 写入成功<br>{@code false}: 写入失败
     */
    public static boolean writeFileFromIS(File file, InputStream is, boolean append)
    {
        if (file == null || is == null)
        {
            return false;
        }

        if (!createOrExistsFile(file))
        {
            return false;
        }

        OutputStream os = null;
        try
        {
            os = new BufferedOutputStream(new FileOutputStream(file, append));
            byte[] data = new byte[1024];
            int len;
            while ((len = is.read(data, 0, 1024)) != -1)
            {
                os.write(data, 0, len);
            }

            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            CloseUtils.closeIO(is, os);
        }
    }


    /**
     * 将字符串写入文件
     *
     * @param filePath 待写入的文件路径
     * @param content  写入内容
     * @param append   是否追加在文件末尾
     * @return {@code true}: 写入成功<br>{@code false}: 写入失败
     */
    public static boolean writeFileFromString(String filePath, String content, boolean append)
    {
        return writeFileFromString(getFileByPath(filePath), content, append);
    }


    /**
     * 将字符串写入文件
     *
     * @param file    待写入的文件
     * @param content 写入内容
     * @param append  是否追加在文件末尾
     * @return {@code true}: 写入成功<br>{@code false}: 写入失败
     */
    public static boolean writeFileFromString(File file, String content, boolean append)
    {
        if (file == null || content == null)
        {
            return false;
        }

        if (!createOrExistsFile(file))
        {
            return false;
        }

        BufferedWriter bw = null;
        try
        {
            bw = new BufferedWriter(new FileWriter(file, append));
            bw.write(content);
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            CloseUtils.closeIO(bw);
        }
    }


    /**
     * 指定编码按行读取文件到链表中
     *
     * @param filePath    文件路径
     * @param charsetName 编码格式
     * @return 文件行链表
     */
    public static List<String> readFile2List(String filePath, String charsetName)
    {
        return readFile2List(getFileByPath(filePath), charsetName);
    }


    /**
     * 指定编码按行读取文件到链表中
     *
     * @param file        文件
     * @param charsetName 编码格式
     * @return 文件行链表
     */
    public static List<String> readFile2List(File file, String charsetName)
    {
        return readFile2List(file, 0, 0x7FFFFFFF, charsetName);
    }


    /**
     * 指定编码按行读取文件到链表中
     *
     * @param filePath    文件路径
     * @param startLine   需要读取的开始行数(从0开始索引)
     * @param endLine     需要读取的结束行数(包括该行)
     * @param charsetName 编码格式
     * @return 包含特定行的链表
     */
    public static List<String> readFile2List(String filePath, int startLine, int endLine, String charsetName)
    {
        return readFile2List(getFileByPath(filePath), startLine, endLine, charsetName);
    }


    /**
     * 指定编码按行读取文件到链表中
     *
     * @param file        文件
     * @param startLine   需要读取的开始行数(从0开始索引)
     * @param endLine     需要读取的结束行数(包括该行)
     * @param charsetName 编码格式
     * @return 包含特定行的链表
     */
    public static List<String> readFile2List(File file, int startLine, int endLine, String charsetName)
    {
        if (file == null)
        {
            return null;
        }

        if (startLine > endLine)
        {
            return null;
        }

        BufferedReader reader = null;
        try
        {
            if (StringUtils.isSpace(charsetName))
            {
                reader = new BufferedReader(new FileReader(file));
            }
            else
            {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
            }

            List<String> list = new ArrayList<>();
            int currentLine = 0;
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (currentLine > endLine)
                {
                    break;
                }
                if (startLine <= currentLine && currentLine <= endLine)
                {
                    list.add(line);
                }

                currentLine++;
            }

            return list;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            CloseUtils.closeIO(reader);
        }
    }


    /**
     * 指定编码按行读取文件到字符串中
     *
     * @param filePath    文件路径
     * @param charsetName 编码格式
     * @return 字符串
     */
    public static String readFile2String(String filePath, String charsetName)
    {
        return readFile2String(getFileByPath(filePath), charsetName);
    }


    /**
     * 指定编码按行读取文件到字符串中
     *
     * @param file        文件
     * @param charsetName 编码格式
     * @return 字符串
     */
    public static String readFile2String(File file, String charsetName)
    {
        if (file == null)
        {
            return null;
        }

        BufferedReader reader = null;
        try
        {
            if (StringUtils.isSpace(charsetName))
            {
                reader = new BufferedReader(new FileReader(file));
            }
            else
            {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
            }

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line).append("\n");
            }

            // 去除最后的换行符
            return sb.delete(sb.length() - 1, sb.length()).toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            CloseUtils.closeIO(reader);
        }
    }


    /**
     * 读取文件到字符数组中
     *
     * @param filePath 文件路径
     * @return 字符数组
     */
    public static byte[] readFile2Bytes(String filePath)
    {
        return readFile2Bytes(getFileByPath(filePath));
    }


    /**
     * 读取文件到字符数组中
     *
     * @param file 文件
     * @return 字符数组
     */
    public static byte[] readFile2Bytes(File file)
    {
        //TODO
        throw new UnsupportedOperationException("not implement yet");
    }


    /**
     * 获取文件最后修改的毫秒时间戳
     *
     * @param filePath 文件路径
     * @return 文件最后修改的毫秒时间戳
     */
    public static long getFileLastModified(String filePath)
    {
        return getFileLastModified(getFileByPath(filePath));
    }


    /**
     * 获取文件最后修改的毫秒时间戳
     *
     * @param file 文件
     * @return 文件最后修改的毫秒时间戳
     */
    public static long getFileLastModified(File file)
    {
        return (file == null ? -1 : file.lastModified());
    }


    /**
     * 简单获取文件编码格式
     * <p>当无法识别编码时, 默认会返回GBK</p>
     *
     * @param filePath 文件路径
     * @return 文件编码
     */
    public static String getFileCharsetSimple(String filePath)
    {
        return getFileCharsetSimple(getFileByPath(filePath));
    }


    /**
     * 简单获取文件编码格式
     * <p>当无法识别编码时, 默认会返回GBK</p>
     *
     * @param file 文件
     * @return 文件编码
     */
    public static String getFileCharsetSimple(File file)
    {
        int p = 0;
        InputStream is = null;
        try
        {
            is = new BufferedInputStream(new FileInputStream(file));
            p = (is.read() << 8) + is.read();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            CloseUtils.closeIO(is);
        }

        switch (p)
        {
            case 0xefbb:
                return "UTF-8";
            case 0xfffe:
                return "Unicode";
            case 0xfeff:
                return "UTF-16BE";
            default:
                return "GBK";
        }
    }


    /**
     * 获取文件行数
     *
     * @param filePath 文件路径
     * @return 文件行数
     */
    public static int getFileLines(String filePath)
    {
        return getFileLines(getFileByPath(filePath));
    }


    /**
     * 获取文件行数
     *
     * @param file 文件
     * @return 文件行数
     */
    public static int getFileLines(File file)
    {
        int count = 1;
        InputStream is = null;
        try
        {
            is = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int readChars;
            while ((readChars = is.read(buffer, 0, 1024)) != -1)
            {
                for (int i = 0; i < readChars; i++)
                {
                    if (buffer[i] == '\n')
                    {
                        count++;
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            CloseUtils.closeIO(is);
        }

        return count;
    }


    /**
     * 获取目录大小
     *
     * @param dirPath 目录路径
     * @return 文件大小
     */
    public static String getDirSize(String dirPath)
    {
        return getDirSize(getFileByPath(dirPath));
    }


    /**
     * 获取目录大小
     *
     * @param dir 目录
     * @return 文件大小
     */
    public static String getDirSize(File dir)
    {
        //TODO
        throw new UnsupportedOperationException("not implement yet");
    }


    /**
     * 获取文件大小
     *
     * @param filePath 文件路径
     * @return 文件大小
     */
    public static String getFileSize(String filePath)
    {
        return getFileSize(getFileByPath(filePath));
    }


    /**
     * 获取文件大小
     *
     * @param file 文件
     * @return 文件大小
     */
    public static String getFileSize(File file)
    {
        //TODO
        throw new UnsupportedOperationException("not implement yet");
    }


    /**
     * 获取目录长度. 当目录不存在时, 返回-1
     *
     * @param dirPath 目录路径
     * @return 文件大小
     */
    public static long getDirLength(String dirPath)
    {
        return getDirLength(getFileByPath(dirPath));
    }


    /**
     * 获取目录长度. 当目录不存在时, 返回-1
     *
     * @param dir 目录
     * @return 文件大小
     */
    public static long getDirLength(File dir)
    {
        if (!isDir(dir))
        {
            return -1;
        }

        long len = 0;
        File[] files = dir.listFiles();
        if (files != null && files.length > 0)
        {
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    len += getDirLength(file);
                }
                else
                {
                    len += file.length();
                }
            }
        }

        return len;
    }


    /**
     * 获取文件长度. 当文件不存在时, 返回-1
     *
     * @param filePath 文件路径
     * @return 文件大小
     */
    public static long getFileLength(String filePath)
    {
        return getFileLength(getFileByPath(filePath));
    }


    /**
     * 获取文件长度. 当文件不存在时, 返回-1
     *
     * @param file 文件
     * @return 文件大小
     */
    public static long getFileLength(File file)
    {
        if (!isFile(file))
        {
            return -1;
        }

        return file.length();
    }


    /**
     * 获取文件的MD5校验码
     *
     * @param filePath 文件路径
     * @return 文件的MD5校验码
     */
    public static String getFileMD5ToString(String filePath)
    {
        return getFileMD5ToString(getFileByPath(filePath));
    }


    /**
     * 获取文件的MD5校验码
     *
     * @param file 文件
     * @return 文件的MD5校验码
     */
    public static String getFileMD5ToString(File file)
    {
        //TODO
        throw new UnsupportedOperationException("not implement yet");
    }


    /**
     * 获取文件的MD5校验码
     *
     * @param filePath 文件路径
     * @return 文件的MD5校验码
     */
    public static byte[] getFileMD5(String filePath)
    {
        return getFileMD5(getFileByPath(filePath));
    }


    /**
     * 获取文件的MD5校验码
     *
     * @param file 文件
     * @return 文件的MD5校验码
     */
    public static byte[] getFileMD5(File file)
    {
        if (file == null)
        {
            return null;
        }

        DigestInputStream dis = null;
        try
        {
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            dis = new DigestInputStream(fis, md);

            byte[] buffer = new byte[1024 * 256];
            while (dis.read(buffer) != -1)
            {
                ;
            }

            md = dis.getMessageDigest();
            return md.digest();
        }
        catch (NoSuchAlgorithmException | IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            CloseUtils.closeIO(dis);
        }

        return null;
    }


    /**
     * 获取全路径中的最长目录
     *
     * @param file 文件
     * @return 文件路径的最长目录
     */
    public static String getDirName(File file)
    {
        if (file == null)
        {
            return null;
        }

        return getDirName(file.getPath());
    }


    /**
     * 获取全路径中的最长目录
     *
     * @param filePath 文件路径
     * @return 文件路径的最长目录
     */
    public static String getDirName(String filePath)
    {
        if (StringUtils.isSpace(filePath))
        {
            return filePath;
        }

        int lastSep = filePath.lastIndexOf(File.separator);
        return lastSep == -1 ? "" : filePath.substring(0, lastSep + 1);
    }


    /**
     * 获取全路径中的文件名
     *
     * @param file 文件
     * @return 文件名
     */
    public static String getFileName(File file)
    {
        if (file == null)
        {
            return null;
        }

        return getFileName(file.getPath());
    }


    /**
     * 获取全路径中的文件名
     *
     * @param filePath 文件路径
     * @return 文件名
     */
    public static String getFileName(String filePath)
    {
        if (StringUtils.isSpace(filePath))
        {
            return filePath;
        }

        int lastSep = filePath.lastIndexOf(File.separator);
        return lastSep == -1 ? filePath : filePath.substring(lastSep + 1);
    }


    /**
     * 获取全路径中的不带拓展名的文件名
     *
     * @param file 文件
     * @return 不带拓展名的文件名
     */
    public static String getFileNameWithoutExtension(File file)
    {
        if (file == null)
        {
            return null;
        }

        return getFileNameWithoutExtension(file.getPath());
    }


    /**
     * 获取全路径中的不带拓展名的文件名
     *
     * @param filePath 文件路径
     * @return 不带拓展名的文件名
     */
    public static String getFileNameWithoutExtension(String filePath)
    {
        if (StringUtils.isSpace(filePath))
        {
            return filePath;
        }

        int lastPoi = filePath.lastIndexOf('.');
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastSep == -1)
        {
            return lastPoi == -1 ? filePath : filePath.substring(0, lastPoi);
        }
        else if (lastPoi == -1 || lastSep > lastPoi)
        {
            return filePath.substring(lastSep + 1);
        }
        else
        {
            return filePath.substring(lastSep + 1, lastPoi);
        }
    }


    /**
     * 获取全路径中的文件拓展名
     *
     * @param file 文件
     * @return 文件拓展名
     */
    public static String getFileExtension(File file)
    {
        if (file == null)
        {
            return null;
        }

        return getFileExtension(file.getPath());
    }


    /**
     * 获取全路径中的文件拓展名
     *
     * @param filePath 文件路径
     * @return 文件拓展名
     */
    public static String getFileExtension(String filePath)
    {
        if (StringUtils.isSpace(filePath))
        {
            return filePath;
        }

        int lastPoi = filePath.lastIndexOf('.');
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastPoi == -1 || lastSep >= lastPoi)
        {
            return "";
        }

        return filePath.substring(lastPoi + 1);
    }
}
