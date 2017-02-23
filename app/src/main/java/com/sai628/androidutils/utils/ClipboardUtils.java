package com.sai628.androidutils.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;


/**
 * @author Sai
 * @ClassName: ClipboardUtils
 * @Description: 剪贴板工具类
 * @date 20/02/2017 21:00
 */
public class ClipboardUtils
{
    private ClipboardUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * 复制文本到剪贴板
     *
     * @param text 待复制的文本
     */
    public static void copyText(CharSequence text)
    {
        ClipboardManager manager = (ClipboardManager) Utils.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(ClipData.newPlainText("text", text));
    }


    /**
     * 获取剪贴板的文本
     *
     * @return {@code CharSequence} 剪贴板上的文本
     */
    public static CharSequence getText()
    {
        ClipData.Item item = getClipboardItem();
        return (item != null ? item.coerceToText(Utils.getContext()) : null);
    }


    /**
     * 复制 Uri 到剪贴板
     *
     * @param uri 待复制的 uri
     */
    public static void copyUri(Uri uri)
    {
        ClipboardManager manager = (ClipboardManager) Utils.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(ClipData.newUri(Utils.getContext().getContentResolver(), "uri", uri));
    }


    /**
     * 获取剪贴板的 Uri
     *
     * @return {@code Uri} 剪贴板上的 uri
     */
    public static Uri getUri()
    {
        ClipData.Item item = getClipboardItem();
        return (item != null ? item.getUri() : null);
    }


    /**
     * 复制 Intent 到剪贴板
     *
     * @param intent 待复制的 Intent
     */
    public static void copyIntent(Intent intent)
    {
        ClipboardManager manager = (ClipboardManager) Utils.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(ClipData.newIntent("intent", intent));
    }


    /**
     * 获取剪贴板的 Intent
     *
     * @return {@code Intent} 剪贴板上的 Intent
     */
    public static Intent getIntent()
    {
        ClipData.Item item = getClipboardItem();
        return (item != null ? item.getIntent() : null);
    }


    private static ClipData.Item getClipboardItem()
    {
        ClipboardManager manager = (ClipboardManager) Utils.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = manager.getPrimaryClip();
        if (clipData != null && clipData.getItemCount() > 0)
        {
            return clipData.getItemAt(0);
        }

        return null;
    }
}
