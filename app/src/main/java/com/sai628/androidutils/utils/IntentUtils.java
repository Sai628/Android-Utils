package com.sai628.androidutils.utils;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;

import java.io.File;


/**
 * @author Sai
 * @ClassName: IntentUtils
 * @Description: Intent工具类
 * @date 14/02/2017 15:09
 */
public class IntentUtils
{
    private IntentUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * 获取安装App的意图(支持6.0)
     *
     * @param filePath 文件路径
     * @return intent
     */
    public static Intent getInstallAppIntent(String filePath)
    {
        return getInstallAppIntent(FileUtils.getFileByPath(filePath));
    }


    /**
     * 获取安装App的意图(支持6.0)
     *
     * @param file 文件
     * @return intent
     */
    public static Intent getInstallAppIntent(File file)
    {
        if (file == null)
        {
            return null;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        String type;
        if (Build.VERSION.SDK_INT < 23)
        {
            type = "application/vnd.android.package-archive";
        }
        else
        {
            String extension = FileUtils.getFileExtension(file);
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }

        if (Build.VERSION.SDK_INT >= 24)
        {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(Utils.getContext(), "com.your.package.fileProvider", file);
            intent.setDataAndType(contentUri, type);
        }

        intent.setDataAndType(Uri.fromFile(file), type);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }


    /**
     * 获取卸载App的Intent
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getUninstallAppIntent(String packageName)
    {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }


    /**
     * 获取打开App的Intent
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getLaunchAppIntent(String packageName)
    {
        return Utils.getContext().getPackageManager().getLaunchIntentForPackage(packageName);
    }


    /**
     * 获取App具体设置的Intent
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getAppDetailsSettingsIntent(String packageName)
    {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + packageName));

        return intent;
    }


    /**
     * 获取分享文本的Intent
     *
     * @param content 分享文本
     * @return intent
     */
    public static Intent getShareTextIntent(String content)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }


    /**
     * 获取分享图片的Intent
     *
     * @param content   分享文本
     * @param imagePath 图片文件路径
     * @return intent
     */
    public static Intent getShareImageIntent(String content, String imagePath)
    {
        return getShareImageIntent(content, FileUtils.getFileByPath(imagePath));
    }


    /**
     * 获取分享图片的Intent
     *
     * @param content   分享文本
     * @param imageFile 图片文件
     * @return intent
     */
    public static Intent getShareImageIntent(String content, File imageFile)
    {
        if (FileUtils.isFileExists(imageFile))
        {
            return null;
        }

        return getShareImageIntent(content, Uri.fromFile(imageFile));
    }


    /**
     * 获取分享图片的Intent
     *
     * @param content 分享文本
     * @param uri     图片uri
     * @return intent
     */
    public static Intent getShareImageIntent(String content, Uri uri)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }


    /**
     * 获取其它应用组件的Intent
     *
     * @param packageName 包名
     * @param className   activity 的全路径类名(包含所在的包名)
     * @return intent
     */
    public static Intent getComponentIntent(String packageName, String className)
    {
        return getComponentIntent(packageName, className, null);
    }


    /**
     * 获取其它应用组件的Intent
     *
     * @param packageName 包名
     * @param className   activity 的全路径类名(包含所在的包名)
     * @param bundle
     * @return intent
     */
    public static Intent getComponentIntent(String packageName, String className, Bundle bundle)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (bundle != null)
        {
            intent.putExtras(bundle);
        }
        ComponentName componentName = new ComponentName(packageName, className);
        intent.setComponent(componentName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }


    /**
     * 获取关机的Intent
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.SHUTDOWN"/>}</p>
     *
     * @return intent
     */
    public static Intent getShutdownIntent()
    {
        Intent intent = new Intent(Intent.ACTION_SHUTDOWN);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }


    /**
     * 获取返回系统主界面的Intent
     *
     * @return intent
     */
    public static Intent getHomeCategoryIntent()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }


    /**
     * 获取打开URL的Intent
     *
     * @param url 待打开的URL
     * @return intent
     */
    public static Intent getOpenURLIntent(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent.putExtra(Browser.EXTRA_APPLICATION_ID, Utils.getContext().getPackageName());

        return intent;
    }


    /**
     * 获取跳转至拨号界面的Intent
     *
     * @param phoneNumber 电话号码
     * @return intent
     */
    public static Intent getDialIntent(String phoneNumber)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }


    /**
     * 获取拨打电话的Intent
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.CALL_PHONE"/>}</p>
     *
     * @param phoneNumber 电话号码
     * @return intent
     */
    public static Intent getCallIntent(String phoneNumber)
    {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }


    /**
     * 获取跳转至发送短信界面的Intent
     *
     * @param phoneNumber 接收号码
     * @param content     短信内容
     * @return intent
     */
    public static Intent getSendSmsIntent(String phoneNumber, String content)
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        intent.putExtra("sms_body", content);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }


    /**
     * 获取跳转至发送邮件界面的Intent
     *
     * @param address 邮件地址
     * @param subject 邮件主题
     * @param text    邮件正文内容
     * @return intent
     */
    public static Intent getSendEmailIntent(String address, String subject, String text)
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + address));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }


    /**
     * 获取拍照的Intent
     *
     * @param outputUri 拍照图片保存路径的uri
     * @return intent
     */
    public static Intent getCaptureIntent(Uri outputUri)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }


    /**
     * 获取选择系统图库中图片的Intent
     *
     * @return intent
     */
    public static Intent getPickImageIntent()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        return intent;
    }


    /**
     * 获取选择系统媒体库中视频的Intent
     *
     * @return intent
     */
    public static Intent getPickVideoIntent()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        return intent;
    }


    /**
     * 获取裁剪图片的Intent
     *
     * @param inputUri  输入图片文件Uri
     * @param outputUri 输出图片文件Uri
     * @param aspectX   X方向上的比例
     * @param aspectY   Y方向上的比例
     * @param outputX   裁剪区的宽
     * @param outputY   裁剪区的高
     * @return intent
     */
    public static Intent getCropImageIntent(Uri inputUri, Uri outputUri, int aspectX, int aspectY, int outputX, int outputY)
    {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(inputUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);

        return intent;
    }
}
